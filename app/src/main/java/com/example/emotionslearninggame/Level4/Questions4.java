package com.example.emotionslearninggame.Level4;

import com.example.emotionslearninggame.R;

public class Questions4 {
    private int mQuestions[] = {
            R.drawable.sad_emoji,
            R.drawable.joy_emoji,
            R.drawable.angry_emoji,
            R.drawable.surprise_emoji,
            R.drawable.disgust_emoji,
            R.drawable.fear_emoji
    };

    private String mAnswer[][] = {
            {"Angry","Sad","Disgust"},
            {"Joy","Surprise","Angry"},
            {"Surprise","Sad","Angry"},
            {"Sad","Fear","Surprise"},
            {"Disgust","Joy","Angry"},
            {"Fear","Surprise","Angry"}
    };

    private String mCorrectAnswer[] = {
            "Sad",
            "Joy",
            "Angry",
            "Surprise",
            "Disgust",
            "Fear"
    };

    /*getQuestions method will get the questionLibrary
     *in the mQuestions array to be used in the
     *question_TextView*/
    public int getQuestions(int i) {
        int questions = mQuestions[i];
        return questions;
    }

    /*Methods getAnswer1, getAnswer2 and getAnswer3
     *is the button choices which is user clickable
     *to select the correct answer*/
    public String getAnswer1(int i) {
        String  answers1 = mAnswer[i][0];
        return answers1;
    }

    public String  getAnswer2(int i) {
        String  answers2 = mAnswer[i][1];
        return answers2;
    }

    public String getAnswer3(int i) {
        String answers3 = mAnswer[i][2];
        return answers3;
    }

    public String getCorrectAnswer(int i) {
        String correctAnswers = mCorrectAnswer[i];
        return correctAnswers;
    }
}