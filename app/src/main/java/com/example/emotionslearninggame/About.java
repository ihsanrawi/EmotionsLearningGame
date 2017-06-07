package com.example.emotionslearninggame;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class About extends AppCompatActivity {

    TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        about = (TextView)findViewById(R.id.aboutTV);
        about.setClickable(true);
        about.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "Some of the artworks are taken from <a href = 'https://www.vecteezy.com'>Vecteezy.com</a>";

        if (Build.VERSION.SDK_INT >= 24) {
            about.setText(Html.fromHtml(text, 1)); // for 24 api and more
        } else {
            about.setText(Html.fromHtml(text)); // or for older api
        }

    }
}
