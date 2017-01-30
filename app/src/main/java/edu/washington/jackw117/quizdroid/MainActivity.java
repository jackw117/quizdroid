package edu.washington.jackw117.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listview);
        String[] values = new String[] { "Math", "Physics", "Marvel Super Heroes" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ListView listView = (ListView) view;
                String itemValue = (String) lv.getItemAtPosition(position);
                switch (position)
                {
                    case 0:  Intent newActivity0 = new Intent(MainActivity.this, Math.class);
                        startActivity(newActivity0);
                        break;
                    case 1:  Intent newActivity1 = new Intent(MainActivity.this, Physics.class);
                        startActivity(newActivity1);
                        break;
                    case 2:  Intent newActivity2 = new Intent(MainActivity.this, MarvelSuperHeroes.class);
                        startActivity(newActivity2);
                        break;
                }
            }
        });
    }
}
