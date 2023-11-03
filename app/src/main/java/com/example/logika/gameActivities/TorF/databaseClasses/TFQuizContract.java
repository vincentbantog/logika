package com.example.logika.gameActivities.TorF.databaseClasses;

import android.provider.BaseColumns;

public class TFQuizContract {

    private TFQuizContract () {}

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
        public static final String COLUMN_IS_BONUS_QUESTION = "is_bonus_question";
        public static final String COLUMN_DIFFICULTY = "difficulty";
    }
}
