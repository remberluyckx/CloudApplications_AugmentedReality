package com.example.rember.testapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rember on 29/11/2016.
 */

/*public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "MyQuestions";
    // Contacts table name
    private static final String TABLE_QUESTIONS = "questions";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_ANSWER1 = "answer1";
    private static final String KEY_ANSWER2 = "answer2";
    private static final String KEY_ANSWER3 = "answer3";
    private static final String KEY_ANSWER4 = "answer4";
    private static final String KEY_ANSWER5 = "answer5";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_QUESTION + " TEXT," + KEY_ANSWER1 + " TEXT,"
        + KEY_ANSWER2 + " TEXT" + KEY_ANSWER3 + " TEXT" + KEY_ANSWER4 + " TEXT" + KEY_ANSWER5 + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
    // Creating tables again
        onCreate(db);
    }

    // Adding new shop
    public void addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, question.getQuestion()); // Shop Name
        values.put(KEY_ANSWER1, question.getAnswer1()); // Shop Phone Number
        values.put(KEY_ANSWER2, question.getAnswer2()); // Shop Phone Number
        values.put(KEY_ANSWER3, question.getAnswer3()); // Shop Phone Number
        values.put(KEY_ANSWER4, question.getAnswer4()); // Shop Phone Number
        values.put(KEY_ANSWER5, question.getAnswer5()); // Shop Phone Number

// Inserting Row
        db.insert(TABLE_QUESTIONS, null, values);
        db.close(); // Closing database connection
    }
    // Getting one shop
    public Question getQuestion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QUESTIONS, new String[]{KEY_ID,
                KEY_QUESTION, KEY_ANSWER1, KEY_ANSWER2, KEY_ANSWER3, KEY_ANSWER4, KEY_ANSWER5}, KEY_ID + "=?",
        new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Question question = new Question(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
// return shop
        return question;
    }
    // Getting All Shops
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<Question>();
    // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(Integer.parseInt(cursor.getString(0)));
                question.setQuestion(cursor.getString(1));
                question.setAnswer1(cursor.getString(2));
                question.setAnswer2(cursor.getString(3));
                question.setAnswer3(cursor.getString(4));
                question.setAnswer4(cursor.getString(5));
                question.setAnswer5(cursor.getString(6));
// Adding contact to list
                questionList.add(question);
            } while (cursor.moveToNext());
        }

// return contact list
        return questionList;
    }
    // Getting shops Count
    public int getQuestionsCount() {
        String countQuery = "SELECT * FROM " + TABLE_QUESTIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

// return count
        return count;
    }
    // Deleting a shop
    public void deleteQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUESTIONS, KEY_ID + " = ?",
        new String[] { String.valueOf(question.getId()) });
        db.close();
    }
}*/
