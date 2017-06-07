package com.example.emotionslearninggame.Level5;

import com.example.emotionslearninggame.R;

public class Questions5 {
    private String questions[] = {
            "(1) Bob fell from his bicycle. He was ________.",
            "(2) Alice got a candy. How would you describe Alice's emotion?",
            "(3) Mary was scolded by her mother for breaking her favourite vase. How would Mary feel?",
            "(4) A lizard fall to Annie's lap. She is ________.",
            "(5) Jack's parents plan a surprise birthday party for him. How would Jack feel at the moment?",
            "(6) Alia saw a cockcroach flying into her bedroom. She would feel ___________?",
            "(7) The school bully wants to beat you. How would you feel?",
            "(8) You won a medal in the school sports day. You would feel very ___________?"
    };

    /*Raw files for narration of questions*/
    private int qNarrate[] = {
            R.raw.l5_1,
            R.raw.l5_2,
            R.raw.l5_3,
            R.raw.l5_4,
            R.raw.l5_5,
            R.raw.l5_6,
            R.raw.l5_7,
            R.raw.l5_8,
    };

    private int buttonBackground[][] = {
            {R.drawable.fear_emoji, R.drawable.sad_emoji, R.drawable.surprise_emoji, R.drawable.joy_emoji}, 		//1
            {R.drawable.angry_emoji, R.drawable.surprise_emoji, R.drawable.disgust_emoji, R.drawable.joy_emoji},	//2
            {R.drawable.surprise_emoji, R.drawable.disgust_emoji, R.drawable.sad_emoji, R.drawable.fear_emoji},		//3
            {R.drawable.disgust_emoji, R.drawable.angry_emoji, R.drawable.surprise_emoji, R.drawable.joy_emoji},	//4
            {R.drawable.surprise_emoji, R.drawable.angry_emoji, R.drawable.joy_emoji, R.drawable.fear_emoji},		//5	
            {R.drawable.sad_emoji, R.drawable.fear_emoji, R.drawable.joy_emoji, R.drawable.disgust_emoji},			//6
            {R.drawable.angry_emoji, R.drawable.fear_emoji, R.drawable.joy_emoji, R.drawable.surprise_emoji},		//7
            {R.drawable.sad_emoji, R.drawable.fear_emoji, R.drawable.disgust_emoji, R.drawable.joy_emoji},			//8
    };

    private String answer[][] = {
            {"fear", "sad", "surprise", "joy"}, 		//1
            {"angry", "surprise", "disgust", "joy"},	//2
            {"surprise", "disgust", "sad", "fear"},		//3
            {"disgust", "angry", "surprise", "joy"},	//4
            {"surprise", "angry", "joy", "fear"},		//5
            {"sad", "fear", "joy", "disgust"},			//6
            {"angry", "fear", "joy", "surprise"},		//7
            {"sad", "fear", "disgust", "joy"}			//8
    };


    private String correctAnswer[] = {
            "sad",
            "joy",
            "sad",
            "disgust",
            "surprise",
            "disgust",
            "fear",
            "joy"
    };

    public String getQuestion(int i) {
        String question = questions[i];
        return question;
    }

    public String getOption1(int i) {
        String answer1 = answer[i][0];
        return answer1;
    }

    public String getOption2(int i) {
        String answer2 = answer[i][1];
        return answer2;
    }

    public String getOption3(int i) {
        String answer3 = answer[i][2];
        return answer3;
    }

    public String getOption4(int i) {
        String answer3 = answer[i][3];
        return answer3;
    }

    public int getBackground1(int i) {
        int background1 = buttonBackground[i][0];
        return background1;
    }

    public int getBackground2(int i) {
        int background2 = buttonBackground[i][1];
        return background2;
    }

    public int getBackground3(int i) {
        int background3 = buttonBackground[i][2];
        return background3;
    }

    public int getBackground4(int i) {
        int background4 = buttonBackground[i][3];
        return background4;
    }

    public String getCorrectAnswer(int i) {
        String correctAswr = correctAnswer[i];
        return correctAswr;
    }

    public int getQNarrate(int i) {
        int narration = qNarrate[i];
        return narration;
    }
}
