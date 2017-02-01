package edu.washington.jackw117.quizdroid2;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Question extends Fragment {

    public static final String INPUT = "input";
    public static final String SCORE = "score";
    public static final String CURRENT = "current";

    public Question() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final String question = getArguments().getString(Overview.QUESTION);
        final String a1 = getArguments().getString(Overview.ANSWER_1);
        final String a2 = getArguments().getString(Overview.ANSWER_2);
        final String a3 = getArguments().getString(Overview.ANSWER_3);
        final String a4 = getArguments().getString(Overview.ANSWER_4);
        final String correct = getArguments().getString(Overview.CORRECT);
        final int score = getArguments().getInt(SCORE);
        int current = getArguments().getInt(CURRENT);
        final String qtotal = getArguments().getString(Overview.QTOTAL);
        final String tag = getArguments().getString(MainActivity.TAG);

        final View rootView = inflater.inflate(R.layout.fragment_question, container, false);

        TextView tvQuestion = (TextView) rootView.findViewById(R.id.question);
        RadioButton r1 = (RadioButton) rootView.findViewById(R.id.answer1);
        RadioButton r2 = (RadioButton) rootView.findViewById(R.id.answer2);
        RadioButton r3 = (RadioButton) rootView.findViewById(R.id.answer3);
        RadioButton r4 = (RadioButton) rootView.findViewById(R.id.answer4);
        final RadioGroup rg = (RadioGroup) rootView.findViewById(R.id.radioGroup);
        final Button submit = (Button) rootView.findViewById(R.id.submit);

        if (current == 0) {
            current++;
        }

        final int newCurrent = current;

        tvQuestion.setText(question);
        r1.setText(a1);
        r2.setText(a2);
        r3.setText(a3);
        r4.setText(a4);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedRadioButton = (RadioButton)rg.findViewById(rg.getCheckedRadioButtonId());
                if (checkedRadioButton != null) {
                    FragmentTransaction tx = getFragmentManager().beginTransaction();
                    Fragment frg = new Answer();
                    Bundle b = new Bundle();
                    int newScore = score;
                    if (checkedRadioButton.getText().equals(correct)) {
                        newScore++;
                    }
                    b.putInt(SCORE, newScore);
                    b.putString(INPUT, checkedRadioButton.getText().toString());
                    b.putInt(CURRENT, newCurrent);
                    b.putString(Overview.QTOTAL, qtotal);
                    b.putString(Overview.CORRECT, correct);
                    b.putString(MainActivity.TAG, tag);
                    frg.setArguments(b);
                    tx.replace(R.id.fragment, frg);
                    tx.commit();
                }
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submit.setVisibility(View.VISIBLE);
            }
        });

        return rootView;
    }
}
