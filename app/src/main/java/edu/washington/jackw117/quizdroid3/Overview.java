package edu.washington.jackw117.quizdroid3;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
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

    private QuizApp quiz = QuizApp.getInstance();

    public Overview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
        final int index = getArguments().getInt(MainActivity.MESSAGE);
        Topic currentTopic = quiz.getTopics().get(index);

        TextView tv1 = (TextView) rootView.findViewById(R.id.title);
        tv1.setText(currentTopic.getTitle());
        TextView tv2 = (TextView) rootView.findViewById(R.id.total);
        String newTotal = "Questions: " + currentTopic.getQuizzes().size();
        tv2.setText(newTotal);
        TextView tv3 = (TextView) rootView.findViewById(R.id.desc);
        tv3.setText(currentTopic.getLongDescription());

        final Button begin = (Button) rootView.findViewById(R.id.begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction tx = getFragmentManager().beginTransaction();
                android.app.Fragment frg = new Question();
                Bundle b = new Bundle();
                b.putInt(MainActivity.MESSAGE, index);
                frg.setArguments(b);
                tx.replace(R.id.fragment, frg);
                tx.commit();
            }
        });
        return rootView;
    }
}
