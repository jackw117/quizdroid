package edu.washington.jackw117.quizdroid3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Preferences extends AppCompatActivity {

    public static final String URL = "URL";
    public static final String MINUTES = "minutes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        Button done = (Button) findViewById(R.id.done);
        final EditText url = (EditText) findViewById(R.id.questionURL);
        final EditText minutes = (EditText) findViewById(R.id.minutes);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(Preferences.this, MainActivity.class);
                Bundle b = new Bundle();
                b.putString(URL, url.getText().toString());
                b.putInt(MINUTES, Integer.parseInt(minutes.getText().toString()));
                mainIntent.putExtras(b);
                startActivity(mainIntent);
            }
        });
    }
}
