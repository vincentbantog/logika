package com.example.logika.HomeFragment.gameActivities.TorF.databaseClasses;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


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


        TFQuestion q31 = new TFQuestion("Byte is the most basic unit of digital data in digital electronics",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q31);
        TFQuestion q32 = new TFQuestion("A Flip-Flop is responsible for storing data in digital circuits",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q32);
        TFQuestion q33 = new TFQuestion("The number system used in digital electronics to represent data and instructions is called the Decimal Number System",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q33);
        TFQuestion q34 = new TFQuestion("The value 1 in binary represents a High voltage level",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q34);
        TFQuestion q35 = new TFQuestion("A Decoder converts binary data to a single output",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q35);
        TFQuestion q36 = new TFQuestion("The Primary function of a multiplexer (MUX) is to perform logic operation",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q36);
        TFQuestion q37 = new TFQuestion("In digital electronics, the term ‘LSB’ stands for Logical Signal Bit",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q37);
        TFQuestion q38 = new TFQuestion("The primary function of a demultiplexer (DEMUX) in digital circuits is to route one input to multiple outputs",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q38);
        TFQuestion q39 = new TFQuestion("A binary decoder takes multiple inputs and selects one output based on the input value ",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q39);
        TFQuestion q40 = new TFQuestion("Clock frequency refers to the number of logic gates in a circuit",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_A, 0);
        addQuestion(q40);
        TFQuestion q41 = new TFQuestion("Algebraic logic in aircraft operations does not provide redundant backup systems for critical functions, activating in case of failure", "T", "F", 2, 0, null, TFQuestion.TOPIC_B, 0);
        addQuestion(q41);
        TFQuestion q42 = new TFQuestion("A logic gate with multiple outputs and multiple inputs can have different numbers of each",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_B, 0);
        addQuestion(q42);
        TFQuestion q43 = new TFQuestion("A logic gate with multiple inputs and multiple outputs can have the same numbers of each",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_B, 0);
        addQuestion(q43);
        TFQuestion q44 = new TFQuestion("A byte consists of 4 bits",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_B, 0);
        addQuestion(q44);
        TFQuestion q45 = new TFQuestion("Binary subtraction with a borrow requires the minuend to be smaller than the subtrahend",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_B, 0);
        addQuestion(q45);
        TFQuestion q46 = new TFQuestion("In binary addition, the sum of '0101' and '1101' is '10110' in binary",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_B, 0);
        addQuestion(q46);
        TFQuestion q47 = new TFQuestion("In digital arithmetic, the relationship between bits and bytes is such that a byte consists of 6 bits, making it a fundamental unit of digital storage and data representation",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_B, 0);
        addQuestion(q47);
        TFQuestion q48 = new TFQuestion("In binary division, the dividend is divided by the divisor to obtain the quotient and remainder",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_B, 0);
        addQuestion(q48);
        TFQuestion q49 = new TFQuestion("Binary subtraction with a borrow requires the minuend to be larger than the subtrahend.",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_B, 0);
        addQuestion(q49);
        TFQuestion q50 = new TFQuestion("The primary function of a counter is to divide binary numbers",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_B, 0);
        addQuestion(q50);
        TFQuestion q51 = new TFQuestion("In Boolean Algebra the binary XOR operation returns '0' when both inputs are the same (both '0' or both '1')",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_C, 0);
        addQuestion(q51);
        TFQuestion q52 = new TFQuestion("Binary is the most common number system used in digital arithmetic",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_C, 0);
        addQuestion(q52);
        TFQuestion q53 = new TFQuestion("In binary multiplication, multiplying any number by 0 results in 1",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_C, 0);
        addQuestion(q53);
        TFQuestion q54 = new TFQuestion("When you have two switches, A and B, and both are turned on (1), the result when you connect them in an 'AND' configuration is 1",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_C, 0);
        addQuestion(q54);
        TFQuestion q55 = new TFQuestion("In Boolean algebra, the identity element for 'AND' is '0' and for 'OR' is '1'",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_C, 0);
        addQuestion(q55);
        TFQuestion q56 = new TFQuestion("The XOR gate outputs '1' when an odd number of inputs are '1'",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_C, 0);
        addQuestion(q56);
        TFQuestion q57 = new TFQuestion("The MSB stands for Most Significant Bit in binary numbers",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_C, 0);
        addQuestion(q57);
        TFQuestion q58 = new TFQuestion("Boolean algebra is a branch of mathematics that deals with the manipulation of binary numbers",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_C, 0);
        addQuestion(q58);
        TFQuestion q59 = new TFQuestion("In Boolean algebra, 'AND' is represented by the multiplication operator and 'OR' is represented by division",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_C, 0);
        addQuestion(q59);
        TFQuestion q60= new TFQuestion("The Boolean expression 'A XOR B' represents the XOR (exclusive OR) operation",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_C, 0);
        addQuestion(q60);
        TFQuestion q61= new TFQuestion("A digital signal can have an infinite number of values between '0' and '1'",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_D, 0);
        addQuestion(q61);
        TFQuestion q62= new TFQuestion("The term 'XNOR' stands for Exclusive-NOR and produces a '0' output when all inputs are different",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_D, 0);
        addQuestion(q62);
        TFQuestion q63= new TFQuestion("The term 'XOR' stands for Exclusive-OR and produces a '1' output when all inputs are different",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_D, 0);
        addQuestion(q63);
        TFQuestion q64= new TFQuestion("The NAND gate outputs '0' only when all of its inputs are '1'",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_D, 0);
        addQuestion(q64);
        TFQuestion q65= new TFQuestion("The binary OR operation returns '0' if all the input bits are '0'",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_D, 0);
        addQuestion(q65);
        TFQuestion q66= new TFQuestion("The NOR gate outputs '1' only when all of its inputs are '1'",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_D, 0);
        addQuestion(q66);
        TFQuestion q67= new TFQuestion("A 4-input AND gate will output '1' if at least three of its inputs are '0'",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_D, 0);
        addQuestion(q67);
        TFQuestion q68= new TFQuestion("An OR gate followed by a NOT gate is equivalent to an AND gate",
                "T", "F", 2, 0, null, TFQuestion.TOPIC_D, 0);
        addQuestion(q68);
        TFQuestion q69= new TFQuestion("An OR gate followed by a NOT gate is equivalent to a NOR gate",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_D, 0);
        addQuestion(q69);
        TFQuestion q70= new TFQuestion("The NOT gate is also known as a Buffer or an Inverter",
                "T", "F", 1, 0, null, TFQuestion.TOPIC_D, 0);
        addQuestion(q70);


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
