package edu.washington.jackw117.quizdroid3;


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

    private QuizApp quiz = QuizApp.getInstance();

    public Answer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final int score = getArguments().getInt(Question.SCORE);
        final String input = getArguments().getString(Question.INPUT);
        int current = getArguments().getInt(Question.CURRENT);
        final int index = getArguments().getInt(MainActivity.MESSAGE);
        Topic currentTopic = quiz.getTopics().get(index);
        final Quiz currentQuiz = currentTopic.getQuizzes().get(current);

        View rootView = inflater.inflate(R.layout.fragment_answer, container, false);

        TextView tvAmount = (TextView) rootView.findViewById(R.id.amount);
        TextView tvCorrect = (TextView) rootView.findViewById(R.id.correct);
        TextView tvInput = (TextView) rootView.findViewById(R.id.input);
        Button next = (Button) rootView.findViewById(R.id.next);

        current++;
        final int newCurrent = current;

        tvAmount.setText("You have " + score + " out of " + currentTopic.getQuizzes().size() + " correct");
        tvCorrect.setText("Correct answer: " + currentQuiz.getAnswers().get(currentQuiz.getCorrect() - 1));
        tvInput.setText("Your answer: " + input);
        boolean check = false;
        if (current == currentTopic.getQuizzes().size()) {
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
                    Bundle b = new Bundle();
                    b.putInt(Question.SCORE, score);
                    b.putInt(Question.CURRENT, newCurrent);
                    b.putInt(MainActivity.MESSAGE, index);
                    frg.setArguments(b);
                    tx.replace(R.id.fragment, frg);
                    tx.commit();
                }
            }
        });
        return rootView;
    }
}
