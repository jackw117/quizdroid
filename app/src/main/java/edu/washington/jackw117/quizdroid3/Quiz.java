package edu.washington.jackw117.quizdroid3;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2/12/2017.
 */

public class Quiz {
    private String question;
    private List<String> answers;
    private int correct;

    public Quiz(String question, ArrayList<String> answers, int correct) {
        this.question = question;
        this.answers = answers;
        this.correct = correct;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrect() {
        return correct;
    }

    public void setQuestion(String input) {
        question = input;
    }

    public void setCorrect(int input) {
        correct = input;
    }

    public void setAnswers(List<String> input) {
        if (input.size() == 4) {
            answers = input;
        } else {
            Log.e("QUIZ", "There needs to be 4 answers");
        }
    }
}
