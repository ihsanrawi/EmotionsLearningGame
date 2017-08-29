package com.example.emotionslearninggame.Camera;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.affectiva.android.affdex.sdk.Frame;
import com.affectiva.android.affdex.sdk.detector.CameraDetector;
import com.affectiva.android.affdex.sdk.detector.Detector;
import com.affectiva.android.affdex.sdk.detector.Face;

import com.example.emotionslearninggame.MainActivity;
import com.example.emotionslearninggame.R;

import java.util.List;

public class EmotionRecognitionActivity extends MainActivity implements Detector.ImageListener, CameraDetector.CameraEventListener {
    final String LOG_TAG = "EmotionRecognition";

    TextView joyTV, sadTV, angerTV, surpriseTV, disgutstTV, fearTV;
    SurfaceView cameraPrev;
    RelativeLayout mainLayout;
    Toolbar toolbar;

    CameraDetector cameraDetector;

    int prevWidth = 0;
    int prevHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotionrecognitionactivity);

        initialise();
        loadToolbar();
        setSurfaceView();
        setEmotionDetector();
    }

    private void initialise() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        joyTV = (TextView) findViewById(R.id.joy_TextView);
        sadTV = (TextView) findViewById(R.id.sadness_TextView);
        angerTV = (TextView) findViewById(R.id.anger_TextView);
        surpriseTV = (TextView) findViewById(R.id.surprise_TextView);
        disgutstTV = (TextView) findViewById(R.id.disgust_TextView);
        fearTV = (TextView) findViewById(R.id.fear_TextView);

        mainLayout = (RelativeLayout) findViewById(R.id.relative_layout);
    }
    private void loadToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(EmotionRecognitionActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });
    }

    private void setSurfaceView() {
        cameraPrev = new SurfaceView(this) {
            @Override
            public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
                int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
                int width;
                int height;

                if (prevHeight == 0 || prevWidth == 0) { //If the SurfaceView height and width are 0
                    width = measureWidth;                //their height and width are set to the size of the screen
                    height = measureHeight;
                } else {
                    float viewAspectRatio = (float) measureWidth / measureHeight;
                    float cameraPrevAspectRatio = (float) prevWidth / prevHeight;

                    if (cameraPrevAspectRatio < viewAspectRatio) {
                        width = measureWidth;
                        height = (int) (measureWidth / cameraPrevAspectRatio);
                    } else {
                        width = (int) (measureHeight * cameraPrevAspectRatio);
                        height = measureHeight;
                    }
                }
                setMeasuredDimension(height, width);
            }
        };
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        cameraPrev.setLayoutParams(layoutParams);
        mainLayout.addView(cameraPrev, 0);
    }

    private void setEmotionDetector() {
        cameraDetector = new CameraDetector(this, CameraDetector.CameraType.CAMERA_FRONT, cameraPrev);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Set which emotions to be detected
                cameraDetector.setDetectJoy(true);
                cameraDetector.setDetectAnger(true);
                cameraDetector.setDetectSadness(true);
                cameraDetector.setDetectSurprise(true);
                cameraDetector.setDetectFear(true);
                cameraDetector.setDetectDisgust(true);
            }
        });
        cameraDetector.setImageListener(this);
        cameraDetector.setOnCameraEventListener(this);
        //Disable anonymous collection of data
        cameraDetector.disableAnalytics();
    }

    @Override
    public void onCameraSizeSelected(int width, int height, Frame.ROTATE rotate) {
        prevHeight = height;
        prevWidth = width;

        cameraPrev.requestLayout();
    }

    @Override
    public void onImageResults(List<Face> faces, Frame image, float v) {
        if (faces == null) {
            return; //frame was not processed
        }

        if (faces.size() != 0) {
            Face face = faces.get(0);
            joyTV.setText((String.format("JOY\n %.1f", face.emotions.getJoy())));
            angerTV.setText((String.format("ANGER\n %.1f", face.emotions.getAnger())));
            sadTV.setText((String.format("SAD\n %.1f", face.emotions.getSadness())));
            surpriseTV.setText((String.format("SURPRISE\n %.1f", face.emotions.getSurprise())));
            fearTV.setText((String.format("FEAR\n %.1f", face.emotions.getFear())));
            disgutstTV.setText((String.format("DISGUST\n %.1f", face.emotions.getDisgust())));
        }
    }

    private void startDetector() {

        if (!cameraDetector.isRunning()) {
            cameraDetector.start();
        }
    }

    private void stopDetector() {
        if (cameraDetector.isRunning()) {
            cameraDetector.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopDetector();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startDetector();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopDetector();
    }
}