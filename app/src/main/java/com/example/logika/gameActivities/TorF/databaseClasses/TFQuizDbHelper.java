package com.example.logika.gameActivities.TorF.databaseClasses;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.logika.gameActivities.TorF.databaseClasses.TFQuestion;
import com.example.logika.gameActivities.TorF.databaseClasses.TFQuizContract;

import java.util.ArrayList;
import java.util.List;

public class TFQuizDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TrueOrFalseQuiz.db";
    public static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public TFQuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                TFQuizContract.QuestionsTable.TABLE_NAME + " ( " +
                TFQuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TFQuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                TFQuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                TFQuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                TFQuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                TFQuizContract.QuestionsTable.COLUMN_IS_BONUS_QUESTION + " INTEGER, " +
                TFQuizContract.QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                TFQuizContract.QuestionsTable.COLUMN_TOPIC + " TEXT, " +
                TFQuizContract.QuestionsTable.COLUMN_IMAGE_FRAGMENT_IDENTIFIER + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TFQuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        TFQuestion q1 = new TFQuestion("Easy: T is Correct", "T", "F", 1, 0, "Easy", null, 0);
        addQuestion(q1);
        TFQuestion q2 = new TFQuestion("Easy: F is Correct", "T", "F", 2, 0, "Easy", null, 0);
        addQuestion(q2);
        TFQuestion q3 = new TFQuestion("Easy: F is Correct", "T", "F", 2, 0, "Easy", null, 0);
        addQuestion(q3);
        TFQuestion q4 = new TFQuestion("Medium: T is Correct", "T", "F", 1, 0, "Medium", null, 0);
        addQuestion(q4);
        TFQuestion q5 = new TFQuestion("Medium: T is Correct", "T", "F", 1, 1, "Medium", null, 0);
        addQuestion(q5);
        TFQuestion q6 = new TFQuestion("Medium: F is Correct", "T", "F", 2, 0, "Medium", null, 1);
        addQuestion(q6);
        TFQuestion q7 = new TFQuestion("Medium: F is Correct", "T", "F", 2, 0, "Medium", null, 2);
        addQuestion(q7);
        TFQuestion q8 = new TFQuestion("Hard: F is Correct", "T", "F", 2, 0, "Hard", null, 3);
        addQuestion(q8);
        TFQuestion q9 = new TFQuestion("Hard: T is Correct", "T", "F", 1, 0, "Hard", null, 4);
        addQuestion(q9);
        TFQuestion q10 = new TFQuestion("Hard: T is Correct", "T", "F", 1, 0, "Hard", null, 5);
        addQuestion(q10);


        TFQuestion q11 = new TFQuestion("Hard: T is Correct", "T", "F", 1, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q11);
        TFQuestion q12 = new TFQuestion("Hard: T is Correct", "T", "F", 1, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q12);
        TFQuestion q13 = new TFQuestion("Hard: T is Correct", "T", "F", 1, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q13);
        TFQuestion q14 = new TFQuestion("Hard: T is Correct", "T", "F", 1, 0, null, TFQuestion.TOPIC_B, 0);
        addQuestion(q14);
        TFQuestion q15 = new TFQuestion("Hard: T is Correct", "T", "F", 1, 0, null, TFQuestion.TOPIC_B, 0);
        addQuestion(q15);
        TFQuestion q16 = new TFQuestion("Hard: T is Correct", "T", "F", 1, 0, null, TFQuestion.TOPIC_B, 0);
        addQuestion(q16);
        TFQuestion q17 = new TFQuestion("Hard: T is Correct", "T", "F", 1, 0, null, TFQuestion.TOPIC_C, 0);
        addQuestion(q17);
        TFQuestion q18 = new TFQuestion("Hard: T is Correct", "T", "F", 1, 0, null, TFQuestion.TOPIC_C, 0);
        addQuestion(q18);
        TFQuestion q19 = new TFQuestion("Hard: T is Correct", "T", "F", 1, 0, null, TFQuestion.TOPIC_C, 0);
        addQuestion(q19);

    }

    private void addQuestion(TFQuestion question) {
        ContentValues cv = new ContentValues();
        cv.put(TFQuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(TFQuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(TFQuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(TFQuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(TFQuizContract.QuestionsTable.COLUMN_IS_BONUS_QUESTION, question.getIsBonusQuestion());
        cv.put(TFQuizContract.QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(TFQuizContract.QuestionsTable.COLUMN_TOPIC, question.getTopic());
        cv.put(TFQuizContract.QuestionsTable.COLUMN_IMAGE_FRAGMENT_IDENTIFIER, question.getImageFragmentIdentifier());
        db.insert(TFQuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    @SuppressLint("Range")
    public List<TFQuestion> getAllQuestions() {
        List<TFQuestion> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TFQuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()){
            do {
                TFQuestion question = new TFQuestion();
                question.setQuestion(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setIsBonusQuestion(c.getInt(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_IS_BONUS_QUESTION)));
                question.setDifficulty(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_DIFFICULTY)));
                question.setTopic(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_TOPIC)));
                question.setImageFragmentIdentifier(c.getInt(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_IMAGE_FRAGMENT_IDENTIFIER)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    @SuppressLint("Range")
    public List<TFQuestion> getQuestionsWithDifficultyAndCount(String difficulty, int count) {
        List<TFQuestion> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{difficulty, String.valueOf(count)};
        Cursor c = db.rawQuery("SELECT * FROM " + TFQuizContract.QuestionsTable.TABLE_NAME +
                " WHERE " + TFQuizContract.QuestionsTable.COLUMN_DIFFICULTY + " = ? ORDER BY RANDOM() LIMIT ?", selectionArgs);

        if (c.moveToFirst()){
            do {
                TFQuestion question = new TFQuestion();
                question.setQuestion(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setIsBonusQuestion(c.getInt(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_IS_BONUS_QUESTION)));
                question.setDifficulty(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_DIFFICULTY)));
                question.setTopic(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_TOPIC)));
                question.setImageFragmentIdentifier(c.getInt(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_IMAGE_FRAGMENT_IDENTIFIER)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    @SuppressLint("Range")
    public List<TFQuestion> getQuestionsWithTopic() {
        List<TFQuestion> questionList = new ArrayList<>();
        db = getReadableDatabase();


        Cursor c = db.rawQuery("SELECT * FROM " + TFQuizContract.QuestionsTable.TABLE_NAME +
                " WHERE " + TFQuizContract.QuestionsTable.COLUMN_TOPIC + " IS NOT NULL" +
                " ORDER BY RANDOM()", null);

        if (c.moveToFirst()){
            do {
                TFQuestion question = new TFQuestion();
                question.setQuestion(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setIsBonusQuestion(c.getInt(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_IS_BONUS_QUESTION)));
                question.setDifficulty(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_DIFFICULTY)));
                question.setTopic(c.getString(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_TOPIC)));
                question.setImageFragmentIdentifier(c.getInt(c.getColumnIndex(TFQuizContract.QuestionsTable.COLUMN_IMAGE_FRAGMENT_IDENTIFIER)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
