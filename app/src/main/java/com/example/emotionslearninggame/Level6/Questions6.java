package com.example.emotionslearninggame.Level6;

import com.example.emotionslearninggame.R;

public class Questions6 {
    private int srcQuestions[] = {
            R.drawable.angry_face,
//            R.drawable.angry_face1,
//            R.drawable.joy_face,
            R.drawable.joy_face1,
            R.drawable.disgust_face,
//            R.drawable.disgust_face1,
//            R.drawable.fear_face,
            R.drawable.fear_face1,
//            R.drawable.surprise_face,
            R.drawable.surprise_face1,
            R.drawable.sad_face
//            R.drawable.sad_face1
    };

    private String optionsAnswer[][] = {
            {"joy", "angry", "sad"},
//            {"joy", "angry", "sad"},
//            {"disgust", "surprise", "joy"},
            {"disgust", "surprise", "joy"},
            {"sad", "surprise", "disgust"},
//            {"sad", "surprise", "disgust"},
//            {"fear", "sad", "surprise"},
            {"fear", "sad", "surprise"},
            {"sad", "joy", "surprise"},
//            {"sad", "joy", "surprise"},
//            {"disgust", "sad", "angry"},
            {"disgust", "sad", "angry"}

            /*{"Gembira", "Marah", "Sedih"},
            {"Jijik", "Terkejut", "Gembira"},
            {"Sedih", "Terkejut", "Jijik"},
            {"Takut", "Sedih", "Terkejut"},
            {"Sedih", "Gembira", "Terkejut"},
            {"Jijik", "Sedih", "Marah"}*/
    };

    private String correctAnswer[] = {
            "angry",
//            "angry",
//            "joy",
            "joy",
            "disgust",
//            "disgust",
//            "fear",
            "fear",
            "surprise",
//            "surprise",
//            "sad",
            "sad"

            /*"Marah",
            "Gembira",
            "Jijik",
            "Takut",
            "Terkejut",
            "Sedih"*/
    };

    private int answerSound[] = {
            R.raw.incorrect,
            R.raw.correct
    };

    public int getQuestions(int i) {
        int questions = srcQuestions[i];
        return questions;
    }

    public String getOptions1(int i) {
        String answer1 = optionsAnswer[i][0];
        return answer1;
    }

    public String getOptions2(int i) {
        String answer2 = optionsAnswer[i][1];
        return answer2;
    }

    public String getOptions3(int i) {
        String answer3 = optionsAnswer[i][2];
        return answer3;
    }

    public String getCorrectAnswer(int i) {
        String crrtAnswer = correctAnswer[i];
        return crrtAnswer;
    }

    public int getanswerSFX(int i) {
        int sfx = answerSound[i];
        return sfx;
    }
}
