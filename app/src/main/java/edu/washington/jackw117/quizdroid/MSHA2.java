package edu.washington.jackw117.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MSHA2 extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msha2);

        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("score");
        String input = bundle.getString("input");
        String correct = bundle.getString("correct");

        tv1 = (TextView) findViewById(R.id.textView8);
        tv2 = (TextView) findViewById(R.id.textView9);
        tv3 = (TextView) findViewById(R.id.textView10);
        next = (Button) findViewById(R.id.button5);

        tv1.setText("You have " + score + "  out of " + getResources().getString(R.string.mshtotal) + " correct");
        tv2.setText("Correct answer: " + correct);
        tv3.setText("Your answer: " + input);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(MSHA2.this, MainActivity.class);
                startActivity(newActivity);
            }
        });
    }
}
