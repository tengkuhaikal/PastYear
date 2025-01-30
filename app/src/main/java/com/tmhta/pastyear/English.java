package com.tmhta.pastyear;

import android.widget.ImageView;

public class English {
    String questions;
    String[] options;
    int answerIndex;
    int colorID;

    public English(String questions, String[] options, int answerIndex, int colorID) {
        this.questions = questions;
        this.options = options;
        this.answerIndex = answerIndex;
        this.colorID = colorID;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }
}
