package com.example.logika.gameActivities.TorF.databaseClasses;

public class TFQuestion {

    public static final String DIFFICULTY_EASY = "Easy";
    public static final String DIFFICULTY_MEDIUM = "Medium";
    public static final String DIFFICULTY_HARD = "Hard";

    public static final String TOPIC_A = "Topic A";
    public static final String TOPIC_B = "Topic B";
    public static final String TOPIC_C = "Topic C";

    private String Question;
    private String option1;
    private String option2;
    private int answerNr;
    private int isBonusQuestion;
    private String difficulty;
    private String topic;


    public TFQuestion(){}

    public int getIsBonusQuestion() {
        return isBonusQuestion;
    }

    public void setIsBonusQuestion(int isBonusQuestion) {
        this.isBonusQuestion = isBonusQuestion;
    }

    public TFQuestion(String question, String option1, String option2,
                      int answerNr, int isBonusQuestion, String difficulty, String topic) {
        Question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.answerNr = answerNr;
        this.isBonusQuestion = isBonusQuestion;
        this.difficulty = difficulty;
        this.topic = topic;
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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
