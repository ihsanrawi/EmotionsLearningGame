package com.example.emotionslearninggame.Database;


/**
 * Created by Wesley on 26/8/17.
 */

public class CapturedEmotion {

    private int id;
    private String type;
    private int score;
    private float anger;
    private float surprise;
    private float joy;
    private float sad;
    private float disgust;
    private float fear;


    public CapturedEmotion() {

    }

    public CapturedEmotion(String type, int score, float anger, float surprise, float joy, float sad, float disgust, float fear) {
        this.type = type;
        this.score = score;
        this.anger = anger;
        this.surprise = surprise;
        this.joy = joy;
        this.sad = sad;
        this.disgust = disgust;
        this.fear = fear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getAnger () {
        return anger;
    }

    public float getSurprise () {
        return surprise;
    }

    public float getJoy () {
        return joy;
    }

    public float getSad () {
        return sad;
    }

    public float getDisgust () {
        return disgust;
    }

    public float getFear () {
        return fear;
    }

    public void setAnger (float anger) {
        this.anger = anger;
    }

    public void setSurprise (float surprise) {
        this.surprise = surprise;
    }

    public void setJoy (float joy) {
        this.joy = joy;
    }

    public void setSad (float sad) {
        this.sad = sad;
    }

    public void setDisgust (float disgust) {
        this.disgust = disgust;
    }

    public void setFear (float fear) {
        this.fear = fear;
    }

    @Override
    public String toString() {
        String Anger = String.format(java.util.Locale.US,"%.1f", this.anger);
        String Surprise = String.format(java.util.Locale.US,"%.1f", this.surprise);
        String Joy = String.format(java.util.Locale.US,"%.1f", this.joy);
        String Sad = String.format(java.util.Locale.US,"%.1f", this.sad);
        String Disgust = String.format(java.util.Locale.US,"%.1f", this.disgust);
        String Fear = String.format(java.util.Locale.US,"%.1f", this.fear);
        return type + " -- Score:" + score + "\n" + "An:" + Anger + " Su:" + Surprise + " Jo:" + Joy + " Sa:" + Sad + " Di:" + Disgust + " Fe:" + Fear;
    }

}