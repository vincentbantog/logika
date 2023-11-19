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
        TFQuestion q1 = new TFQuestion("Boolean algebra is a mathematical structure commonly used in digital circuit design",
                "T", "F", 1, 0, "Easy", null, 0);
        addQuestion(q1);
        TFQuestion q2 = new TFQuestion("Analog electronics have completely replaced digital electronics in aviation",
                "T", "F", 2, 0, "Easy", null, 0);
        addQuestion(q2);
        TFQuestion q3 = new TFQuestion("Binary code is the foundation of digital information representation",
                "T", "F", 2, 0, "Easy", null, 0);
        addQuestion(q3);
        TFQuestion q4 = new TFQuestion("'Binary' is a method of encoding information using the digits 0 and 1, with each digit representing a distinct value in the digital system",
                "T", "F", 1, 0, "Easy", null, 0);
        addQuestion(q4);
        TFQuestion q5 = new TFQuestion("In digital electronics, binary is not the most often used number system for representing data and instructions",
                "T", "F", 2, 0, "Easy", null, 0);
        addQuestion(q5);
        TFQuestion q6 = new TFQuestion("In digital electronics, logic gates modify and process binary data",
                "T", "F", 1, 0, "Easy", null, 0);
        addQuestion(q6);
        TFQuestion q7 = new TFQuestion("In binary representation, '1' typically represents a high voltage level",
                "T", "F", 1, 0, "Easy", null, 0);
        addQuestion(q7);
        TFQuestion q8 = new TFQuestion("A NOT gate inverts the input signal",
                "T", "F", 1, 0, "Easy", null, 0);
        addQuestion(q8);
        TFQuestion q9 = new TFQuestion("In binary representation, '1' typically represents a low voltage level",
                "T", "F", 2, 0, "Easy", null, 0);
        addQuestion(q9);
        TFQuestion q10 = new TFQuestion("A logic gate performs arithmetic calculations in digital electronics",
                "T", "F", 2, 0, "Easy", null, 0);
        addQuestion(q10);
        TFQuestion q11 = new TFQuestion("In digital arithmetic, division can be performed by repeated subtraction",
                "T", "F", 1, 0, "Medium", null, 0);
        addQuestion(q11);
        TFQuestion q12 = new TFQuestion("Division by zero is not possible in digital arithmetic",
                "T", "F", 1, 0, "Medium", null, 0);
        addQuestion(q12);
        TFQuestion q13 = new TFQuestion("In binary arithmetic, the result of multiplying an odd number by 2 is always an even number",
                "T", "F", 1, 0, "Medium", null, 0);
        addQuestion(q13);
        TFQuestion q14 = new TFQuestion("In binary arithmetic, division by zero results in an undefined value",
                "T", "F", 1, 0, "Medium", null, 0);
        addQuestion(q14);
        TFQuestion q15 = new TFQuestion("The primary advantage of digital signals over analog signals in communication is reduced signal quality",
                "T", "F", 2, 0, "Medium", null, 0);
        addQuestion(q15);
        TFQuestion q16 = new TFQuestion("In binary addition: 110 + 001 = 0111",
                "T", "F", 1, 0, "Medium", null, 0);
        addQuestion(q16);
        TFQuestion q17 = new TFQuestion("In binary subtraction: 110 - 001 = 0100",
                "T", "F", 2, 1, "Medium", null, 0);
        addQuestion(q17);
        TFQuestion q18 = new TFQuestion("In this Boolean Algebra presented: A * A = A",
                "T", "F", 1, 0, "Medium", null, 0);
        addQuestion(q18);
        TFQuestion q19 = new TFQuestion("According to the Distributive Law, this Boolean Algebra statement is correct. 'A(B+C) equals AB+BC'",
                "T", "F", 2, 0, "Medium", null, 0);
        addQuestion(q19);
        TFQuestion q20 = new TFQuestion("In this Boolean Algebra presented: A + A' = 1",
                "T", "F", 1, 0, "Medium", null, 0);
        addQuestion(q20);
        TFQuestion q21 = new TFQuestion("The truth table provided is associated with the logic gate circuit",
                "T", "F", 2, 0, "Hard", null, 1);
        addQuestion(q21);
        TFQuestion q22 = new TFQuestion("This truth table connected to the provided LED circuit",
                "T", "F", 1, 0, "Hard", null, 2);
        addQuestion(q22);
        TFQuestion q23 = new TFQuestion("The LED output lights up when both the binary input of 1 is applied to the AND gate",
                "T", "F", 1, 0, "Hard", null, 3);
        addQuestion(q23);
        TFQuestion q24 = new TFQuestion("The LED output light when one binary input of 0 is applied to the OR gate",
                "T", "F", 2, 0, "Hard", null, 4);
        addQuestion(q24);
        TFQuestion q25 = new TFQuestion("When both of the binary input is 1 to an AND gate, the output of this LED circuit will result at binary output of 1",
                "T", "F", 1, 0, "Hard", null, 5);
        addQuestion(q25);
        TFQuestion q26 = new TFQuestion("Boolean Algebra is not applied in the control systems of an aircraft",
                "T", "F", 2, 0, "Hard", null, 0);
        addQuestion(q26);
        TFQuestion q27 = new TFQuestion("This equation for the Boolean Algebra correct. (AB)' + AC = 1 if A = 0, B = 0, and C = 0",
                "T", "F", 1, 0, "Hard", null, 0);
        addQuestion(q27);
        TFQuestion q28 = new TFQuestion("This equation for the Boolean Algebra correct. A' + (AB)' = 1 if A = 1, B = 1",
                "T", "F", 2, 0, "Hard", null, 0);
        addQuestion(q28);
        TFQuestion q29 = new TFQuestion("This equation for the Boolean Algebra correct. BC + AC = 1 if A = 0, B = 1, C = 1",
                "T", "F", 1, 1, "Hard", null, 0);
        addQuestion(q29);
        TFQuestion q30 = new TFQuestion("This equation for the Boolean Algebra correct. AC + BC = B if A = 1, B = 0, C =1",
                "T", "F", 2, 0, "Hard", null, 0);
        addQuestion(q30);


        TFQuestion q31 = new TFQuestion("Hard: T is Correct", "T", "F", 1, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q31);


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
