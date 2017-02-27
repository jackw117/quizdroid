package edu.washington.jackw117.quizdroid3;

import android.Manifest;
import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    public static final String MESSAGE = "index";
    public QuizApp quiz = QuizApp.getInstance();
    private static DownloadManager dm = null;
    private static String questionURL = null;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Alarm alarmClass = new Alarm(this);
        final AlarmManager alarmManager = alarmClass.getAlarm();

        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        lv = (ListView) findViewById(R.id.listview);
        final Button pref = (Button) findViewById(R.id.pref);
        Bundle bundle = getIntent().getExtras();
        int minutes;
        if (isAirplaneModeOn(this)) {
            Toast.makeText(this, "Please turn off Airplane Mode", Toast.LENGTH_SHORT).show();
            Intent settingsIntent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
            startActivity(settingsIntent);
        } else if (!isNetworkAvailable()) {
            Toast.makeText(this, "Sorry, you need to have an internet connection to download the quiz", Toast.LENGTH_SHORT).show();
        }
        if (bundle != null) {
            questionURL = bundle.getString(Preferences.URL);
            minutes = bundle.getInt(Preferences.MINUTES) * 60000;
            Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + minutes, minutes, pendingIntent);
            Toast.makeText(this, "Downloading file from " + questionURL, Toast.LENGTH_SHORT).show();
            download();
        } else if (questionURL != null) {
            download();
        }

        pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent preferencesIntent = new Intent(MainActivity.this, Preferences.class);
                startActivity(preferencesIntent);
            }
        });
    }

    public void download() {
        if (isStoragePermissionGranted()) {
            dm.enqueue(new DownloadManager.Request(Uri.parse(questionURL))
                    .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                            DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                            "questions.json"));
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static boolean isAirplaneModeOn(Context context) {

        return Settings.Global.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

    }

    BroadcastReceiver onComplete = new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {
            DownloadManager.Query query = null;
            query = new DownloadManager.Query();
            Cursor c = null;
            if(query!=null) {
                query.setFilterByStatus(DownloadManager.STATUS_FAILED|DownloadManager.STATUS_PAUSED|DownloadManager.STATUS_SUCCESSFUL|DownloadManager.STATUS_RUNNING|DownloadManager.STATUS_PENDING);
            }
            c = dm.query(query);
            if(c.moveToFirst()) {
                int status =c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                if (status==DownloadManager.STATUS_FAILED) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Retry download?")
                            .setMessage("The download failed. Would you like to retry?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    download();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {
                    Toast.makeText(ctxt, "Download complete", Toast.LENGTH_SHORT).show();
                    quiz.getQuiz();
                    List<Topic> topicList = quiz.getTopics();
                    String[] topics = new String[topicList.size()];
                    String[] shortDesc = new String[topicList.size()];
                    int [] prgmImages= new int[topicList.size()];
                    for (int i = 0; i < topicList.size(); i++) {
                        topics[i] = topicList.get(i).getTitle();
                        shortDesc[i] = topicList.get(i).getShortDescription();
                        prgmImages[i] = R.mipmap.ic_launcher;
                    }
                    lv.setAdapter(new CustomAdapter(MainActivity.this, topics,prgmImages, shortDesc));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent newActivity = new Intent(MainActivity.this, QuizLaunch.class);
                            newActivity.putExtra(MESSAGE, position);
                            startActivity(newActivity);

                        }
                    });
                }
            }
            unregisterReceiver(onComplete);
        }
    };

    private boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    public static class AlarmReceiver extends BroadcastReceiver {
        public AlarmReceiver() {};

        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Checking file from " + questionURL, Toast.LENGTH_SHORT).show();
        }
    }
}
