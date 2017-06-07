package com.example.emotionslearninggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.emotionslearninggame.Camera.EmotionRecognitionActivity;
import com.example.emotionslearninggame.Flashcard.GenderOptionActivity;
import com.example.emotionslearninggame.Flashcard.HowYouFeel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton flashcard, play, recognition, highscore, youtube;
    Button about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcard = (ImageButton) findViewById(R.id.flashcard);
        play = (ImageButton) findViewById(R.id.play_game);
        recognition = (ImageButton)findViewById(R.id.emotion_camera);
        about = (Button)findViewById(R.id.about_Btn);
        highscore = (ImageButton)findViewById(R.id.highscore_Btn);
        youtube = (ImageButton)findViewById(R.id.youtube_Btn);

//        final TextView welcomeText = (TextView)findViewById(R.id.textView);
//        welcomeText.setAnimation(new RotateAnimation(0, 360, 50, 30));

        flashcard.setOnClickListener(this);
        play.setOnClickListener(this);
        recognition.setOnClickListener(this);
        about.setOnClickListener(this);
        highscore.setOnClickListener(this);
        youtube.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.flashcard) :
                Intent flashcardIntent = new Intent(this, GenderOptionActivity.class);
                startActivity(flashcardIntent);
                break;

            case (R.id.play_game) :
                Intent game = new Intent(this, AgeGroup.class);
                startActivity(game);
                break;

            case (R.id.emotion_camera) :
                Intent emotionRecognitionIntent = new Intent(this, EmotionRecognitionActivity.class);
                startActivity(emotionRecognitionIntent);
                break;
            case (R.id.about_Btn) :
                Intent aboutIntent = new Intent(this, About.class);
                startActivity(aboutIntent);
                break;
            case (R.id.highscore_Btn) :
                Intent scoreIntent = new Intent(this, HighScore.class);
                startActivity(scoreIntent);

            case (R.id.youtube_Btn) :
                Intent youtube = new Intent(this, YoutubeList.class);
                startActivity(youtube);
        }
    }
}