package edu.washington.jackw117.quizdroid2;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Overview extends Fragment {

    public static final String QUESTION = "question";
    public static final String ANSWER_1 = "a1";
    public static final String ANSWER_2 = "a2";
    public static final String ANSWER_3 = "a3";
    public static final String ANSWER_4 = "a4";
    public static final String CORRECT = "correct";
    public static final String QTOTAL = "qtotal";

    public Overview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final String title = getArguments().getString(Quiz.TITLE);
        final String total = getArguments().getString(Quiz.TOTAL);
        final String desc = getArguments().getString(Quiz.DESC);
        final String tag = getArguments().getString(MainActivity.TAG);

        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);

        TextView tv1 = (TextView) rootView.findViewById(R.id.title);
        tv1.setText(title);
        TextView tv2 = (TextView) rootView.findViewById(R.id.total);
        String newTotal = "Questions: " + total;
        tv2.setText(newTotal);
        TextView tv3 = (TextView) rootView.findViewById(R.id.desc);
        tv3.setText(desc);

        final Button begin = (Button) rootView.findViewById(R.id.begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction tx = getFragmentManager().beginTransaction();
                Fragment frg = new Question();
                if (title.equals(getResources().getString(R.string.mtitle))) {
                    frg.setArguments(putHelp(getResources().getString(R.string.mq1), getResources().getString(R.string.mq1a1),
                            getResources().getString(R.string.mq1a2), getResources().getString(R.string.mq1a3),
                            getResources().getString(R.string.mq1a4), getResources().getString(R.string.mq1a1),
                            getResources().getString(R.string.mtotal), tag));

                } else if (title.equals(getResources().getString(R.string.ptitle))) {
                    frg.setArguments(putHelp(getResources().getString(R.string.pq1), getResources().getString(R.string.pq1a1),
                            getResources().getString(R.string.pq1a2), getResources().getString(R.string.pq1a3),
                            getResources().getString(R.string.pq1a4), getResources().getString(R.string.pq1a4),
                            getResources().getString(R.string.ptotal), tag));
                } else if (title.equals(getResources().getString(R.string.mshtitle))) {
                    frg.setArguments(putHelp(getResources().getString(R.string.mshq1), getResources().getString(R.string.mshq1a1),
                            getResources().getString(R.string.mshq1a2), getResources().getString(R.string.mshq1a3),
                            getResources().getString(R.string.mshq1a4), getResources().getString(R.string.mshq1a1),
                            getResources().getString(R.string.mshtotal), tag));
                }
                tx.replace(R.id.fragment, frg);
                tx.commit();
            }
        });
        return rootView;
    }

    private Bundle putHelp(String question, String a1, String a2, String a3, String a4, String correct, String qtotal, String tag) {
        Bundle b = new Bundle();
        b.putString(QUESTION, question);
        b.putString(ANSWER_1, a1);
        b.putString(ANSWER_2, a2);
        b.putString(ANSWER_3, a3);
        b.putString(ANSWER_4, a4);
        b.putString(CORRECT, correct);
        b.putString(QTOTAL, qtotal);
        b.putString(MainActivity.TAG, tag);
        return b;
    }
}
