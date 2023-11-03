package com.example.logika.gameActivities.basiQuiz;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.logika.gameActivities.basiQuiz.BQQuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class BQQuizDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "GeneralQuestions.db";
    public static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public BQQuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        BQQuestion q1 = new BQQuestion("Easy: A is correct",
                "A", "B", "C", "D", 1, "Easy");
        addQuestion(q1);
        BQQuestion q2 = new BQQuestion("Easy: C is correct",
                "A", "B", "C", "D", 3, "Easy");
        addQuestion(q2);
        BQQuestion q3 = new BQQuestion("Easy: B is correct",
                "A", "B", "C", "D", 2, "Easy");
        addQuestion(q3);
        BQQuestion q4 = new BQQuestion("Medium: C is correct",
                "A", "B", "C", "D", 3, "Medium");
        addQuestion(q4);
        BQQuestion q5 = new BQQuestion("Medium: D is correct",
                "A", "B", "C", "D", 4, "Medium");
        addQuestion(q5);
        BQQuestion q6 = new BQQuestion("Medium: A is correct",
                "A", "B", "C", "D", 1, "Medium");
        addQuestion(q6);
        BQQuestion q7 = new BQQuestion("Medium: A is correct",
                "A", "B", "C", "D", 1, "Medium");
        addQuestion(q7);
        BQQuestion q8 = new BQQuestion("Hard: C is correct",
                "A", "B", "C", "D", 3, "Hard");
        addQuestion(q8);
        BQQuestion q9 = new BQQuestion("Hard: B is correct",
                "A", "B", "C", "D", 2, "Hard");
        addQuestion(q9);
        BQQuestion q10 = new BQQuestion("Hard: A is correct",
                "A", "B", "C", "D", 1, "Hard");
        addQuestion(q10);

    }

    private void addQuestion(BQQuestion question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    @SuppressLint("Range")
    public List<BQQuestion> getAllQuestions() {
        List<BQQuestion> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()){
            do {
                BQQuestion question = new BQQuestion();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    @SuppressLint("Range")
    public List<BQQuestion> getQuestionsWithDifficulty(String difficulty) {
        List<BQQuestion> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String [] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME +
                " WHERE " + QuestionsTable.COLUMN_DIFFICULTY + " = ?", selectionArgs);

        if (c.moveToFirst()){
            do {
                BQQuestion question = new BQQuestion();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    @SuppressLint("Range")
    public List<BQQuestion> getQuestionsWithDifficultyAndCount(String difficulty, int count) {
        List<BQQuestion> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String [] selectionArgs = new String[]{difficulty, String.valueOf(count)};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME +
                " WHERE " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ORDER BY RANDOM() LIMIT ?", selectionArgs);

        if (c.moveToFirst()){
            do {
                BQQuestion question = new BQQuestion();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
