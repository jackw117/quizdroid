package edu.washington.jackw117.quizdroid3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    public static final String MESSAGE = "index";
    public QuizApp quiz = QuizApp.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        lv = (ListView) findViewById(R.id.listview);
        List<Topic> topicList = quiz.getTopics();
        String[] topics = new String[topicList.size()];
        String[] shortDesc = new String[topicList.size()];
        int [] prgmImages={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        for (int i = 0; i < topicList.size(); i++) {
            topics[i] = topicList.get(i).getTitle();
            shortDesc[i] = topicList.get(i).getShortDescription();
        }
        lv.setAdapter(new CustomAdapter(this, topics,prgmImages, shortDesc));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:  Intent newActivity0 = new Intent(MainActivity.this, QuizLaunch.class);
                        newActivity0.putExtra(MESSAGE, 0);
                        startActivity(newActivity0);
                        break;
                    case 1:  Intent newActivity1 = new Intent(MainActivity.this, QuizLaunch.class);
                        newActivity1.putExtra(MESSAGE, 1);
                        startActivity(newActivity1);
                        break;
                    case 2:  Intent newActivity2 = new Intent(MainActivity.this, QuizLaunch.class);
                        newActivity2.putExtra(MESSAGE, 2);
                        startActivity(newActivity2);
                        break;
                }
            }
        });
    }
}
