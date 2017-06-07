package com.example.emotionslearninggame.Level7;

import com.example.emotionslearninggame.R;

public class Questions7 {
    private int situations[] = {
            R.drawable.sad_situation,
            R.drawable.joy_situation,
            R.drawable.fear_situation,
            R.drawable.surprise_situation,
            R.drawable.angry_situation
    };

    private String optAnswer[][] = {
            {"Sad", "Surprise", "Disgust"},
            {"Fear", "Angry", "Joy"},
            {"Fear", "Disgust", "Surprise"},
            {"Sad", "Surprise", "joy"},
            {"Disgust", "Angry", "Joy"}

            /*{"Sedih", "Terkejut", "Jijik"},
            {"Terkejut", "Marah", "Gembira"},
            {"Takut", "Jijik", "Terkejut"},
            {"Sedih", "Terkejut", "Gembira"},
            {"Jijik", "Marah", "Gembira"}*/
    };

    private String corrAnswer[] = {
            "Sad",
            "Joy",
            "Fear",
            "Surprise",
            "Angry"
            /*"Sedih",
            "Gembira",
            "Takut",
            "Terkejut",
            "Marah"*/
    };

    public int getSituation(int i) {
        int situation = situations[i];
        return situation;
    }

    public String getOption1(int i) {
        String option1 = optAnswer[i][0];
        return option1;
    }

    public String getOption2(int i) {
        String option2 = optAnswer[i][1];
        return option2;
    }

    public String getOption3(int i) {
        String option3 = optAnswer[i][2];
        return option3;
    }

    public String getAnswer(int i) {
        String correctAnswer = corrAnswer[i];
        return correctAnswer;
    }
}
