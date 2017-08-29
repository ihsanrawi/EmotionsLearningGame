package com.example.emotionslearninggame.Database;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.emotionslearninggame.MainActivity;
import com.example.emotionslearninggame.R;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private SQLiteDatabaseHandler db;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        result = (TextView) findViewById(R.id.result);

        db = new SQLiteDatabaseHandler(this);

        //  List all Players
        List<CapturedEmotion> capturedEmotions = db.allCapturedEmotions();

        //  Display Results
        StringBuilder builder = new StringBuilder();

        for (CapturedEmotion capturedEmotion : capturedEmotions) {
            builder.append(capturedEmotion).append("\n");
        }

        result.setMovementMethod(new ScrollingMovementMethod());
        result.setText(builder.toString());
    }

}
