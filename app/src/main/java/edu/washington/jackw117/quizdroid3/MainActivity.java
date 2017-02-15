package edu.washington.jackw117.quizdroid3;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listview);
        final Button pref = (Button) findViewById(R.id.pref);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String questionURL = bundle.getString(Preferences.URL);
            int minutes = bundle.getInt(Preferences.MINUTES);
        }

        pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent preferencesIntent = new Intent(MainActivity.this, Preferences.class);
                startActivity(preferencesIntent);
            }
        });
        List<Topic> topicList = quiz.getTopics();
        String[] topics = new String[topicList.size()];
        String[] shortDesc = new String[topicList.size()];
        int [] prgmImages={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        for (int i = 0; i < topicList.size(); i++) {
            topics[i] = topicList.get(i).getTitle();
            shortDesc[i] = topicList.get(i).getShortDescription();
        }
        lv.setAdapter(new CustomAdapter(this, topics,prgmImages, shortDesc));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:  Intent newActivity0 = new Intent(MainActivity.this, QuizLaunch.class);
                        newActivity0.putExtra(MESSAGE, 0);
                        startActivity(newActivity0);
                        break;
                    case 1:  Intent newActivity1 = new Intent(MainActivity.this, QuizLaunch.class);
                        newActivity1.putExtra(MESSAGE, 1);
                        startActivity(newActivity1);
                        break;
                    case 2:  Intent newActivity2 = new Intent(MainActivity.this, QuizLaunch.class);
                        newActivity2.putExtra(MESSAGE, 2);
                        startActivity(newActivity2);
                        break;
                }
            }
        });
    }
}
