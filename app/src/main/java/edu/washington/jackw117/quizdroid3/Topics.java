package edu.washington.jackw117.quizdroid3;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jack on 2/12/2017.
 */

public class Topics implements TopicRepository<Topic> {

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

    public void createQuiz(File file) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            reader.beginArray();
            while (reader.hasNext()) {
                reader.beginObject();
                reader.skipValue();
                String topic = reader.nextString();
                reader.skipValue();
                String desc = reader.nextString();
                reader.skipValue();
                reader.beginArray();
                //start of questions
                ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
                while (reader.peek() != JsonToken.END_ARRAY) {
                    ArrayList<String> answerList = new ArrayList<String>();
                    reader.beginObject();
                    reader.skipValue();
                    String question = reader.nextString();
                    reader.skipValue();
                    int correct = reader.nextInt();
                    reader.skipValue();
                    reader.beginArray();
                    answerList.add(reader.nextString());
                    answerList.add(reader.nextString());
                    answerList.add(reader.nextString());
                    answerList.add(reader.nextString());
                    reader.endArray();
                    reader.endObject();
                    quizzes.add(new Quiz(question, answerList, correct));
                }
                reader.endArray();
                reader.endObject();
                add(new Topic(topic, desc, quizzes));
            }
            reader.endArray();
            reader.close();
        } catch (IOException e) {
            Log.e("TOPICS", e.toString());
        }
    }
}
