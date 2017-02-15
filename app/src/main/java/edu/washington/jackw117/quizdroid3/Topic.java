package edu.washington.jackw117.quizdroid3;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Jack on 2/12/2017.
 */

public class Topic {
    private String title;
    private String shortDescription;
    private List<Quiz> quizzes;

    public Topic(String title, String shortDesc, ArrayList<Quiz> quizzes) {
        this.title = title;
        this.shortDescription = shortDesc;
        this.quizzes = quizzes;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setTitle(String input) {
        title = input;
    }

    public void setShortDescription(String input) {
        shortDescription = input;
    }

    public void setQuizzes(List<Quiz> input) {
        quizzes = input;
    }
}
