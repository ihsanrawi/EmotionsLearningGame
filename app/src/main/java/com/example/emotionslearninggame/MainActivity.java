package com.example.emotionslearninggame;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.emotionslearninggame.Camera.EmotionRecognitionActivity;
import com.example.emotionslearninggame.Database.SecondActivity;
import com.example.emotionslearninggame.Flashcard.GenderOptionActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton flashcard, play, recognition, highscore, youtube;
    Button about;

    private final static int CAMERA_PERMISSIONS_REQUEST_CODE = 0;
    private final static String[] CAMERA_PERMISSIONS_REQUEST = new String[]{Manifest.permission.CAMERA};
    private boolean handleCameraPermissionGrant;

    @SuppressWarnings("ConstantConditions")
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

        flashcard.setOnClickListener(this);
        play.setOnClickListener(this);
        recognition.setOnClickListener(this);
        about.setOnClickListener(this);
        highscore.setOnClickListener(this);
        youtube.setOnClickListener(this);

        // on Marshmallow+, we have to ask for the camera permission the first time
        if (!CameraHelper.checkPermission(this) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(CAMERA_PERMISSIONS_REQUEST, CAMERA_PERMISSIONS_REQUEST_CODE);
        }

        // hook up a click handler for the "2nd activity" button
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
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
                break;
            case (R.id.youtube_Btn) :
                Intent youtube = new Intent(this, YoutubeList.class);
                startActivity(youtube);
                break;
        }
    }



    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onResume() {
        super.onResume();
        if (handleCameraPermissionGrant) {
            // a response to our camera permission request was received
            if (CameraHelper.checkPermission(this)) {
                startService(new Intent(this, DetectorService.class));
            } else {
                ((TextView)findViewById(R.id.text)).setText(R.string.camera_permission_denied);
                findViewById(R.id.button).setVisibility(View.INVISIBLE);
            }
            handleCameraPermissionGrant = false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSIONS_REQUEST_CODE) {
            for (String permission : permissions) {
                if (permission.equals(Manifest.permission.CAMERA)) {
                    // next time through onResume, handle the grant result
                    handleCameraPermissionGrant = true;
                    break;
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ((DemoApplication)getApplication()).onActivityStarted();
    }
    @Override

    protected void onStop() {
        super.onStop();
        ((DemoApplication)getApplication()).onActivityStopped();
    }
}