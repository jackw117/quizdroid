package edu.washington.jackw117.quizdroid3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jack on 2/12/2017.
 */

public class Topics implements TopicRepository<Topic> {
    private String[] topics = new String[] {
            "Math", "Physics", "Marvel Super Heroes"
    };
    private String[] shortDesc = new String[] {
            "Quiz about math", "Quiz about physics", "Quiz about super heroes"
    };
    private String[] longDesc = new String[] {
            "This topic allows you to answer math questions ranging from simple arithmetic, algebra, and calculus.",
            "Answer physics questions related to kinematics, electromagnetism, and waves.",
            "Answer questions about Marvel super heroes."
    };
    private String[][] questions = new String[][] {
            new String[] {"1 + 1 = ?", "0 / 0 = ?"},
            new String[] {"What floats in water?", "What is the average airspeed velocity of an unladen swallow?"},
            new String[] {"Is Batman Marvel?", "Is Luke Skywalker Marvel?"}
    };
    private String[][] answers = new String[][] {
            new String[] {"1", "2", "3", "4",
                "1", "2", "what", "the end of the universe"},
            new String[] {"bread", "apples", "very small rocks", "a duck",
                "I don't know that", "What do you mean?", "African or European?", "blue"},
            new String[] {"yes", "somewhat", "I don't know", "no",
                "who is Luke", "yes", "I think so", "definitely"}
    };
    private int[][] correct = new int[][] {
            new int[] {1, 3},
            new int[] {4, 4},
            new int[] {1, 2}
    };

    private List<Topic> topicList;

    public void add(Topic item) {
        if (topicList == null) {
            topicList = new ArrayList<Topic>();
        }
        topicList.add(item);
    }

    public List<Topic> query() {
        return topicList;
    }

    public void createQuiz() {
        for (int i = 0; i < topics.length; i++) {
            List<Quiz> quizList = new ArrayList<Quiz>();
            for (int j = 0; j < questions[i].length; j++) {
                List<String> currentAnswers = new ArrayList<String>();
                for (int k = 0; k < 4; k++) {
                    currentAnswers.add(answers[i][k + (4 * j)]);
                }
                quizList.add(new Quiz(questions[i][j], (ArrayList<String>) currentAnswers, correct[i][j]));
            }
            add(new Topic(topics[i], shortDesc[i], longDesc[i], (ArrayList<Quiz>) quizList));
        }
    }
}
