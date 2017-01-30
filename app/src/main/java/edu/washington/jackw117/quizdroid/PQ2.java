package edu.washington.jackw117.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PQ2 extends AppCompatActivity {

    Button submit;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioButton r4;
    RadioGroup rGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq2);

        Bundle bundle = getIntent().getExtras();
        final int score = bundle.getInt("score");

        r1 = (RadioButton) findViewById(R.id.radioButton26);
        r2 = (RadioButton) findViewById(R.id.radioButton27);
        r3 = (RadioButton) findViewById(R.id.radioButton28);
        r4 = (RadioButton) findViewById(R.id.radioButton29);
        submit = (Button) findViewById(R.id.button12);

        rGroup = (RadioGroup)findViewById(R.id.radioGroup12);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedRadioButton = (RadioButton)rGroup.findViewById(rGroup.getCheckedRadioButtonId());
                if (checkedRadioButton != null) {
                    Intent newActivity = new Intent(PQ2.this, PA2.class);
                    if (checkedRadioButton.getId() == r4.getId()) {
                        int newScore = score + 1;
                        newActivity.putExtra("score", newScore);
                    } else {
                        newActivity.putExtra("score", score);
                    }
                    newActivity.putExtra("input", checkedRadioButton.getText());
                    newActivity.putExtra("correct", r4.getText());
                    startActivity(newActivity);
                }
            }
        });

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submit.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(PQ2.this, PQ1.class);
        startActivity(setIntent);
    }
}
