package edu.washington.jackw117.quizdroid2;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
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
public class Answer extends Fragment {


    public Answer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final int score = getArguments().getInt(Question.SCORE);
        final String input = getArguments().getString(Question.INPUT);
        final int current = getArguments().getInt(Question.CURRENT);
        final String qtotal = getArguments().getString(Overview.QTOTAL);
        final String correct = getArguments().getString(Overview.CORRECT);
        final String tag = getArguments().getString(MainActivity.TAG);

        View rootView = inflater.inflate(R.layout.fragment_answer, container, false);

        TextView tvAmount = (TextView) rootView.findViewById(R.id.amount);
        TextView tvCorrect = (TextView) rootView.findViewById(R.id.correct);
        TextView tvInput = (TextView) rootView.findViewById(R.id.input);
        Button next = (Button) rootView.findViewById(R.id.next);

        tvAmount.setText("You have " + score + " out of " + qtotal + " correct");
        tvCorrect.setText("Correct answer: " + correct);
        tvInput.setText("Your answer: " + input);
        boolean check = false;
        String currentCheck = "" + current;
        if (currentCheck.equals(qtotal)) {
            check = true;
            next.setText("FINISH");
        } else {
            next.setText("NEXT");
        }

        final boolean end = check;

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (end) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                } else {
                    FragmentTransaction tx = getFragmentManager().beginTransaction();
                    Fragment frg = new Question();
                    int newCurrent = current;
                    newCurrent++;
                    String newQuestion = tag + newCurrent;
                    String newA1 = newQuestion + "a1";
                    String newA2 = newQuestion + "a2";
                    String newA3 = newQuestion + "a3";
                    String newA4 = newQuestion + "a4";
                    String newCorrect = newQuestion + "correct";
                    if (tag.equals("mq")) {
                        frg.setArguments(putHelp(getResources().getString(getStringIdentifier(getActivity(), newQuestion)), getResources().getString(getStringIdentifier(getActivity(), newA1)),
                                getResources().getString(getStringIdentifier(getActivity(), newA2)), getResources().getString(getStringIdentifier(getActivity(), newA3)),
                                getResources().getString(getStringIdentifier(getActivity(), newA4)), getResources().getString(getStringIdentifier(getActivity(), newCorrect)),
                                getResources().getString(R.string.mtotal), tag, newCurrent, score));
                    } else if (tag.equals("pq")) {
                        frg.setArguments(putHelp(getResources().getString(getStringIdentifier(getActivity(), newQuestion)), getResources().getString(getStringIdentifier(getActivity(), newA1)),
                                getResources().getString(getStringIdentifier(getActivity(), newA2)), getResources().getString(getStringIdentifier(getActivity(), newA3)),
                                getResources().getString(getStringIdentifier(getActivity(), newA4)), getResources().getString(getStringIdentifier(getActivity(), newCorrect)),
                                getResources().getString(R.string.ptotal), tag, newCurrent, score));
                    } else if (tag.equals("mshq")) {
                        frg.setArguments(putHelp(getResources().getString(getStringIdentifier(getActivity(), newQuestion)), getResources().getString(getStringIdentifier(getActivity(), newA1)),
                                getResources().getString(getStringIdentifier(getActivity(), newA2)), getResources().getString(getStringIdentifier(getActivity(), newA3)),
                                getResources().getString(getStringIdentifier(getActivity(), newA4)), getResources().getString(getStringIdentifier(getActivity(), newCorrect)),
                                getResources().getString(R.string.mshtotal), tag, newCurrent, score));
                    }
                    tx.replace(R.id.fragment, frg);
                    tx.commit();
                }
            }
        });

        return rootView;
    }

    public static int getStringIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }

    private Bundle putHelp(String question, String a1, String a2, String a3, String a4, String correct, String qtotal, String tag, int current, int score) {
        Bundle b = new Bundle();
        b.putString(Overview.QUESTION, question);
        b.putString(Overview.ANSWER_1, a1);
        b.putString(Overview.ANSWER_2, a2);
        b.putString(Overview.ANSWER_3, a3);
        b.putString(Overview.ANSWER_4, a4);
        b.putString(Overview.CORRECT, correct);
        b.putString(Overview.QTOTAL, qtotal);
        b.putString(MainActivity.TAG, tag);
        b.putInt(Question.CURRENT, current);
        b.putInt(Question.SCORE, score);
        return b;
    }
}
