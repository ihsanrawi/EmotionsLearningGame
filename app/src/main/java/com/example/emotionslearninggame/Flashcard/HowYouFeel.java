package com.example.emotionslearninggame.Flashcard;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emotionslearninggame.MainActivity;
import com.example.emotionslearninggame.R;

public class HowYouFeel extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ImageView bigEmoji;
    private ImageButton joy, sad, surprise, fear, anger, disgust;
    private TextView synonymWord, emotionWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_you_feel);

        initialize();
    }

    private void initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        joy = (ImageButton) findViewById(R.id.joyBtn);
        sad = (ImageButton) findViewById(R.id.sadBtn);
        surprise = (ImageButton) findViewById(R.id.surpriseBtn);
        fear = (ImageButton) findViewById(R.id.fearBtn);
        disgust = (ImageButton) findViewById(R.id.disgustBtn);
        anger = (ImageButton) findViewById(R.id.angry_Btn);

        bigEmoji = (ImageView) findViewById(R.id.bigEmotionIV);

        synonymWord = (TextView)findViewById(R.id.emotionTv);
        emotionWord = (TextView)findViewById(R.id.bigEmotionTv);

        joy.setOnClickListener(this);
        sad.setOnClickListener(this);
        surprise.setOnClickListener(this);
        fear.setOnClickListener(this);
        disgust.setOnClickListener(this);
        anger.setOnClickListener(this);

        bigEmoji.setVisibility(View.INVISIBLE);

        loadToolbar();
    }

    private void loadToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(HowYouFeel.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });
    }

    @Override
    public void onClick(View v) {
        bigEmoji.setVisibility(View.VISIBLE);
        switch (v.getId()){
            case (R.id.joyBtn) :
                emotionWord.setText("JOY");
                synonymWord.setText("happy");
                bigEmoji.setImageResource(R.drawable.joy_emoji);
                break;
            case (R.id.sadBtn) :
                emotionWord.setText("SAD");
                synonymWord.setText("sad");
                bigEmoji.setImageResource(R.drawable.sad_emoji);
                break;
            case (R.id.surpriseBtn) :
                emotionWord.setText("SURPRISE");
                synonymWord.setText("shocked");
                bigEmoji.setImageResource(R.drawable.surprise_emoji);
                break;
            case (R.id.fearBtn) :
                emotionWord.setText("FEAR");
                synonymWord.setText("scared");
                bigEmoji.setImageResource(R.drawable.fear_emoji);
                break;
            case (R.id.disgustBtn) :
                emotionWord.setText("DISGUST");
                synonymWord.setText("disgust");
                bigEmoji.setImageResource(R.drawable.disgust_emoji);
                break;
            case (R.id.angry_Btn) :
                emotionWord.setText("ANGER");
                synonymWord.setText("mad");
                bigEmoji.setImageResource(R.drawable.angry_emoji);
                break;
        }
    }
}
