package com.example.emotionslearninggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class YoutubeList extends MainActivity {
    private ListView listView;

    private int i;
    private Intent youtubePlayer;
    private Bundle b;

    private String[] videoTitle = new String[]{"Feelings and Emotion Chant", "The Feelings Song",
            "Feelings Song for Children by The Learning Station", "Feelings | Word Power | PINKFONG Songs for Children"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_list);

        listView = (ListView) findViewById(R.id.listview);
        //zEk48QQSPo4, UsISd1AMNYU, ca8SUuG8vdA, a1NIWCr0R-k

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, videoTitle);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        i = 1;
                        youtubePlayer = new Intent(YoutubeList.this, YoutubePlayer.class);
                        b = new Bundle();
                        b.putInt("video", i);
                        youtubePlayer.putExtras(b);
                        startActivity(youtubePlayer);
                        break;

                    case 1:
                        i = 2;
                        youtubePlayer = new Intent(YoutubeList.this, YoutubePlayer.class);
                        b = new Bundle();
                        b.putInt("video", i);
                        youtubePlayer.putExtras(b);
                        startActivity(youtubePlayer);
                        break;
                    case 2:
                        i = 3;
                        youtubePlayer = new Intent(YoutubeList.this, YoutubePlayer.class);
                        b = new Bundle();
                        b.putInt("video", i);
                        youtubePlayer.putExtras(b);
                        startActivity(youtubePlayer);
                        break;
                    case 3:
                        i = 4;
                        youtubePlayer = new Intent(YoutubeList.this, YoutubePlayer.class);
                        b = new Bundle();
                        b.putInt("video", i);
                        youtubePlayer.putExtras(b);
                        startActivity(youtubePlayer);
                        break;
                }
            }
        });
    }
}
