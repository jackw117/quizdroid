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
    private QuizApp quiz = QuizApp.getInstance();

    public Question() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final int score = getArguments().getInt(SCORE);
        final int current = getArguments().getInt(CURRENT);
        final int index = getArguments().getInt(MainActivity.MESSAGE);

        final View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        Topic currentTopic = quiz.getTopics().get(index);
        final Quiz currentQuiz = currentTopic.getQuizzes().get(current);

        TextView tvQuestion = (TextView) rootView.findViewById(R.id.question);
        RadioButton r1 = (RadioButton) rootView.findViewById(R.id.answer1);
        RadioButton r2 = (RadioButton) rootView.findViewById(R.id.answer2);
        RadioButton r3 = (RadioButton) rootView.findViewById(R.id.answer3);
        RadioButton r4 = (RadioButton) rootView.findViewById(R.id.answer4);
        final RadioGroup rg = (RadioGroup) rootView.findViewById(R.id.radioGroup);
        final Button submit = (Button) rootView.findViewById(R.id.submit);

        tvQuestion.setText(currentQuiz.getQuestion());
        r1.setText(currentQuiz.getAnswers().get(0));
        r2.setText(currentQuiz.getAnswers().get(1));
        r3.setText(currentQuiz.getAnswers().get(2));
        r4.setText(currentQuiz.getAnswers().get(3));



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checkedRadioButton = (RadioButton)rg.findViewById(rg.getCheckedRadioButtonId());
                if (checkedRadioButton != null) {
                    FragmentTransaction tx = getFragmentManager().beginTransaction();
                    Fragment frg = new Answer();
                    Bundle b = new Bundle();
                    int newScore = score;
                    if (checkedRadioButton.getText().equals(currentQuiz.getAnswers().get(currentQuiz.getCorrect() - 1))) {
                        newScore++;
                    }
                    b.putInt(SCORE, newScore);
                    b.putString(INPUT, checkedRadioButton.getText().toString());
                    b.putInt(CURRENT, current);
                    b.putInt(MainActivity.MESSAGE, index);
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
