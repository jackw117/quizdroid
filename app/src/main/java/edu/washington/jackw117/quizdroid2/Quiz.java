package edu.washington.jackw117.quizdroid2;

import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Quiz extends AppCompatActivity {

    public static final String TITLE = "title";
    public static final String TOTAL = "total";
    public static final String DESC = "desc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Bundle bundle = getIntent().getExtras();
        String fragment = bundle.getString(MainActivity.MESSAGE);
        String tag = bundle.getString(MainActivity.TAG);
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        Fragment frg = new Overview();
        if (fragment.equals(getResources().getString(R.string.mintent))) {
            frg.setArguments(putHelp(getResources().getString(R.string.mtitle),
                    getResources().getString(R.string.mtotal), getResources().getString(R.string.mdesc), tag));
        } else if (fragment.equals(getResources().getString(R.string.pintent))) {
            frg.setArguments(putHelp(getResources().getString(R.string.ptitle),
                    getResources().getString(R.string.ptotal), getResources().getString(R.string.pdesc), tag));
        } else if (fragment.equals(getResources().getString(R.string.mshintent))) {
            frg.setArguments(putHelp(getResources().getString(R.string.mshtitle),
                    getResources().getString(R.string.mshtotal), getResources().getString(R.string.mshdesc), tag));
        }
        tx.replace(R.id.fragment, frg);
        tx.commit();

    }

    private Bundle putHelp(String title, String total, String desc, String tag) {
        Bundle b = new Bundle();
        b.putString(TITLE, title);
        b.putString(TOTAL, total);
        b.putString(DESC, desc);
        b.putString(MainActivity.TAG, tag);
        return b;
    }
}
