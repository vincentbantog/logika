package com.example.logika.HomeFragment.gameActivities.logiQuiz;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;


    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionTable.TABLE_NAME + " ( " +
                QuizContract.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionTable.COLUMN_QUESTION + " INTEGER, " +
                QuizContract.QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuizContract.QuestionTable.COLUMN_DIFFICULTY + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question(1,
                "A", "B", "Both A and B", 3, Question.DIFFICULTY_MEDIUM);
        addQuestion(q1);
        Question q2 = new Question(2,
                "AND Gate", "OR Gate", "NOR Gate", 1, Question.DIFFICULTY_MEDIUM);
        addQuestion(q2);
        Question q3 = new Question(3,
                "AND Gate", "OR Gate", "NOR Gate", 2, Question.DIFFICULTY_MEDIUM);
        addQuestion(q3);
        Question q4 = new Question(4,
                "AND Gate", "OR Gate", "NOR Gate", 2, Question.DIFFICULTY_MEDIUM);
        addQuestion(q4);
        Question q5 = new Question(5,
                "1", "0", "2", 2, Question.DIFFICULTY_MEDIUM);
        addQuestion(q5);
        Question q6 = new Question(6,
                "A", "B", "Both A and B", 3, Question.DIFFICULTY_HARD);
        addQuestion(q6);
        Question q7 = new Question(7,
                "AND Gate", "OR Gate", "NOR Gate", 2, Question.DIFFICULTY_HARD);
        addQuestion(q7);
        Question q8 = new Question(8,
                "1", "0", "2", 1,  Question.DIFFICULTY_HARD);
        addQuestion(q8);
        Question q9 = new Question(9,
                "1", "0", "2", 2, Question.DIFFICULTY_HARD);
        addQuestion(q9);
        Question q10 = new Question(10,
                "A and B", "A,B and C", "B, C, and D", 3, Question.DIFFICULTY_HARD);
        addQuestion(q10);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionTable.COLUMN_QUESTION, question.getCircuitFragment());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuizContract.QuestionTable.COLUMN_DIFFICULTY, question.getDifficulty());
        db.insert(QuizContract.QuestionTable.TABLE_NAME, null, cv);

    }

    @SuppressLint("Range")
    public List<Question> getAllQuestion() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setCircuitFragment(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    @SuppressLint("Range")
    public List<Question> getQuestionsWithDifficulty(String difficulty) {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionTable.TABLE_NAME +
                " WHERE " + QuizContract.QuestionTable.COLUMN_DIFFICULTY + " = ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setCircuitFragment(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
