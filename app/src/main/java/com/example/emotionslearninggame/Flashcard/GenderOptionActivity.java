package com.example.emotionslearninggame.Flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.emotionslearninggame.R;

public class GenderOptionActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton male, female;
    private int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_option);

        male = (ImageButton)findViewById(R.id.male_Btn);
        female = (ImageButton)findViewById(R.id.female_Btn);

        male.setOnClickListener(this);
        female.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.female_Btn) :
                gender = 0;
                Intent flashcard = new Intent(GenderOptionActivity.this, FlashcardActivity.class);
                Bundle b = new Bundle();
                b.putInt("gender", gender);
                flashcard.putExtras(b);
                startActivity(flashcard);
                finish();
                break;

            case (R.id.male_Btn) :
                gender = 1;

                Intent flash = new Intent(this,FlashcardActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("gender", gender);
                flash.putExtras(bundle);
                startActivity(flash);
                finish();
                break;
        }

    }
}