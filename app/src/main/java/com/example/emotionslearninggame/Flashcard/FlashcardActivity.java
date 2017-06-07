package com.example.emotionslearninggame.Flashcard;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emotionslearninggame.MainActivity;
import com.example.emotionslearninggame.R;

public class FlashcardActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int MAX_CARD_COUNT = 6;
    int counter = 0, gender;

    ImageView flashcard;
    ImageButton prev, next, home, feel;
    TextView cardDesc;
    Toolbar toolbar;

    private int[] cardSource, cardSource1;
    private String[] cardText, sound;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        initialize();
        loadSource();
        showCard();
    }

    private void initialize() {
        //Get the ImageView, TextView and Buttons
        flashcard = (ImageView) findViewById(R.id.cardIV);
        prev = (ImageButton) findViewById(R.id.prevBtn);
        next = (ImageButton) findViewById(R.id.nextBtn);
        cardDesc = (TextView) findViewById(R.id.descTV);

        home = (ImageButton) findViewById(R.id.home_Btn);
        feel = (ImageButton) findViewById(R.id.howFeel_Btn);

        prev.setOnClickListener(this);
        next.setOnClickListener(this);
        flashcard.setOnClickListener(this);
        home.setOnClickListener(this);
        feel.setOnClickListener(this);

        Bundle getGender = getIntent().getExtras();
        int getgndr = getGender.getInt("gender");
        gender = getgndr;
    }

    private void loadSource() {
        //Load the description
        cardText = new String[]{
                "Joy",
                "Surprise",
                "Disgust",
                "Fear",
                "Angry",
                "Sad"
        };
        //Load the card drawable source
        cardSource = new int[]{         //Drawable for female flashcard
                R.drawable.joy_face,
                R.drawable.surprise_face,
                R.drawable.disgust_face,
                R.drawable.fear_face,
                R.drawable.angry_face,
                R.drawable.sad_face,
        };

        cardSource1 = new int[]{      //Drawable for male flashcard
                R.drawable.joy_face1,
                R.drawable.surprise_face1,
                R.drawable.disgust_face1,
                R.drawable.fear_face1,
                R.drawable.angry_face1,
                R.drawable.sad_face1
        };

        sound = new String [] {
                "joy.mp3",
                "surprise.mp3",
                "disgust.mp3",
                "fear.mp3",
                "angry.mp3",
                "sad.mp3"
        };
    }

    private void showCardDesc() {
        //If the flashcard is tapped twice
        if (cardDesc.getVisibility() == View.INVISIBLE) {
            playSound(sound[counter].toString());
            cardDesc.setVisibility(View.VISIBLE);
        } else {
            cardDesc.setVisibility(View.INVISIBLE);
        }
    }

    /*TODO: Play sound is using Assert instead of raw file*/
    private void playSound(String soundFile) {
        Boolean mpStatus;

        try {
            mpStatus = mp.isPlaying();
        } catch (Exception e) {
            mpStatus = false;
        }

        if (mpStatus == true) {
            mp.stop();
            mp.release();
        }else {
            try {
                mp = new MediaPlayer();
                AssetFileDescriptor afd = getAssets().openFd(soundFile);
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepare();
                mp.start();
            } catch (Exception e) {
                Log.i("Error playing sound", e.toString());
            }
        }
    }

    private void showCard() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (gender == 0) {
                    flashcard.setImageResource(cardSource[counter]);
                }else if (gender == 1){
                    flashcard.setImageResource(cardSource1[counter]);
                }
            }
        },150);
//        flashcard.setImageResource(cardSource[counter]);
        //Default view of cardDesc is invisible
        cardDesc.setVisibility(View.INVISIBLE);
        cardDesc.setText(cardText[counter]);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.prevBtn):
                counter--;
                //Make it loop to the last image
                if (counter == -1) {
                    counter = MAX_CARD_COUNT - 1;
                }
                showCard();
                break;

            case (R.id.nextBtn):
                counter++;
                //Make it loop to the first image
                if (counter == MAX_CARD_COUNT) {
                    counter = 0;
                }
                showCard();
                break;

            case (R.id.cardIV):
                //When the ImageView is tap, the cardDesc is visible
                showCardDesc();
                break;

            case (R.id.home_Btn):
                Intent mainActivity = new Intent(FlashcardActivity.this, MainActivity.class);
                startActivity(mainActivity);
                break;

            case (R.id.howFeel_Btn):
                Intent feelActivity = new Intent(FlashcardActivity.this, HowYouFeel.class);
                startActivity(feelActivity);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSource();
        showCard();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
