package com.example.logika.HomeFragment.gameActivities.logiQuiz;

public class Question {
    public static final String DIFFICULTY_MEDIUM = "Medium";
    public static final String DIFFICULTY_HARD = "Hard";

    private int circuitFragment;
    private String option1;
    private String option2;
    private String option3;
    private int answerNr;
    private String difficulty;

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public static String[] getAllDifficultyLevels() {
        return new String[]{
                DIFFICULTY_MEDIUM,
                DIFFICULTY_HARD
        };
    }

    public Question() {}

    public Question(int circuitFragment, String option1,
                    String option2, String option3, int answerNr, String difficulty) {
        this.circuitFragment = circuitFragment;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNr = answerNr;
        this.difficulty = difficulty;
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
