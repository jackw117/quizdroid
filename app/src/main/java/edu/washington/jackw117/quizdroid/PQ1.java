package edu.washington.jackw117.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PQ1 extends AppCompatActivity {

    Button submit;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioButton r4;
    RadioGroup rGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq1);


        r1 = (RadioButton) findViewById(R.id.radioButton22);
        r2 = (RadioButton) findViewById(R.id.radioButton23);
        r3 = (RadioButton) findViewById(R.id.radioButton24);
        r4 = (RadioButton) findViewById(R.id.radioButton25);
        submit = (Button) findViewById(R.id.button10);

        rGroup = (RadioGroup)findViewById(R.id.radioGroup9);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedRadioButton = (RadioButton)rGroup.findViewById(rGroup.getCheckedRadioButtonId());
                if (checkedRadioButton != null) {
                    Intent newActivity = new Intent(PQ1.this, PA1.class);
                    if (checkedRadioButton.getId() == r4.getId()) {
                        newActivity.putExtra("score", 1);
                    } else {
                        newActivity.putExtra("score", 0);
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
        Intent setIntent = new Intent(PQ1.this, MainActivity.class);
        startActivity(setIntent);
    }
}
