package edu.washington.jackw117.quizdroid3;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2/12/2017.
 */

public class QuizApp extends android.app.Application {

    public static final String MESSAGE = "QuizApp";
    private static Topics repo = new Topics();

    private static QuizApp instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(MESSAGE, "QuizApp onCreate called");

        getQuiz();
    }

    public static QuizApp getInstance() {
        if (instance == null) {
            instance = new QuizApp();
        }
        return instance;
    }

    public List<Topic> getTopics() {
        return repo.query();
    }

    private void getQuiz() {
        repo.createQuiz();
    }
}
