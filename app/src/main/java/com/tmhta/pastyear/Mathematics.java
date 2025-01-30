package com.tmhta.pastyear;

public class Mathematics {
    String questions;
    String[] options;
    int answerIndex;

    public Mathematics(String questions, String[] options, int answerIndex) {
        this.questions = questions;
        this.options = options;
        this.answerIndex = answerIndex;
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
}
