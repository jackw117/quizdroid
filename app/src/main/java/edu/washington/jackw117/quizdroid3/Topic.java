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
    private String longDescription;
    private List<Quiz> quizzes;

    public Topic(String title, String shortDesc, String longDesc, ArrayList<Quiz> quizzes) {
        this.title = title;
        this.shortDescription = shortDesc;
        this.longDescription = longDesc;
        this.quizzes = quizzes;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
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

    public void setLongDescription(String input) {
        longDescription = input;
    }

    public void setQuizzes(List<Quiz> input) {
        quizzes = input;
    }
}
