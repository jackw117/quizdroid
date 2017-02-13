package edu.washington.jackw117.quizdroid3;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuizLaunch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_launch);

        Bundle bundle = getIntent().getExtras();
        int index = bundle.getInt(MainActivity.MESSAGE);
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        Fragment frg = new Overview();
        Bundle b = new Bundle();
        b.putInt(MainActivity.MESSAGE, index);
        frg.setArguments(b);
        tx.replace(R.id.fragment, frg);
        tx.commit();
    }
}
