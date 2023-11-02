package com.example.logika.gameActivities.TorF.databaseClasses;

public class TFQuestion {
    private String Question;
    private String option1;
    private String option2;
    private int answerNr;
    private int isBonusQuestion;

    public TFQuestion(){}

    public int getIsBonusQuestion() {
        return isBonusQuestion;
    }

    public void setIsBonusQuestion(int isBonusQuestion) {
        this.isBonusQuestion = isBonusQuestion;
    }

    public TFQuestion(String question, String option1, String option2, int answerNr, int isBonusQuestion) {
        Question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.answerNr = answerNr;
        this.isBonusQuestion = isBonusQuestion;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }
}
