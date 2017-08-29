package com.example.emotionslearninggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emotionslearninggame.Level4.Level4Activity;
import com.example.emotionslearninggame.Level6.Level6;


public class AgeGroup extends MainActivity implements View.OnClickListener {

    Button ageBelow7, ageAbove7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_group);

        ageBelow7 = (Button) findViewById(R.id.below7);
        ageAbove7 = (Button) findViewById(R.id.above7);

        ageBelow7.setOnClickListener(this);
        ageAbove7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.below7):
                Intent level4 = new Intent(this, Level4Activity.class);
                startActivity(level4);
                break;

            case (R.id.above7):
                Intent level6 = new Intent(this, Level6.class);
                startActivity(level6);
        }
    }
}
