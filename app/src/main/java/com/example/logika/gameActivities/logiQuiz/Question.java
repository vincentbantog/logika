package com.example.logika.gameActivities.logiQuiz;

public class Question {
    private int circuitFragment;
    private String option1;
    private String option2;
    private String option3;
    private int answerNr;

    public Question() {}

    public Question(int circuitFragment, String option1, String option2, String option3, int answerNr) {
        this.circuitFragment = circuitFragment;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNr = answerNr;
    }

    public int getCircuitFragment() {
        return circuitFragment;
    }

    public void setCircuitFragment(int circuitFragment) {
        this.circuitFragment = circuitFragment;
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

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }
}
