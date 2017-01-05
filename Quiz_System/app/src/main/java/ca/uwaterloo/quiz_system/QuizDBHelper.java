package ca.uwaterloo.quiz_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;

/**
 * Created by Wisdom H Jiang on 2016-10-20.
 */

public class QuizDBHelper extends SQLiteOpenHelper{
    //LogCat tag
    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbQuizTaker";
    //Table Names
    private static final String TABLE_QUIZTAKER = "quiztaker";
    private static final String TABLE_QUIZ = "quizOptionTaker";
    //Column Name for table quizTaker
    private static final String KEY_USERID = "userId";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_TIME = "time";
    private static final String KEY_QUIZTAKEN = "quizTaken";
    //private static final String KEY_THENTH = "theNth";
    //private static final String KEY_CORRECTANSWER = "correctAnswer";
    //Column Name for table quizOptionAnswer
    private static final String KEY_QUIZID = "quizId";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_OPTIONA = "optionA";
    private static final String KEY_OPTIONB = "optionB";
    private static final String KEY_OPTIONC = "optionC";
    private static final String KEY_OPTIOND = "optionD";
    private static final String KEY_ANSWER = "answer";
    //quizTaker table create statement
    private static final String CREATE_TABLE_QUIZTAKER = "CREATE TABLE " + TABLE_QUIZTAKER
            + "( " + KEY_USERID + " TEXT PRIMARY KEY, " + KEY_PASSWORD + " TEXT, " + KEY_TIME + " INTEGER, "
            + KEY_QUIZTAKEN + " TEXT" + ")";
    private static final String CREATE_TABLE_QUIZ = "CREATE TABLE " + TABLE_QUIZ
            + "( " + KEY_QUIZID + " INTEGER PRIMARY KEY, " + KEY_QUESTION + " TEXT, "
            + KEY_OPTIONA + " TEXT, " + KEY_OPTIONB + " TEXT, " + KEY_OPTIONC + " TEXT, "
            + KEY_OPTIOND + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_QUIZTAKEN + " TEXT" + ")";

    public QuizDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUIZTAKER);
        db.execSQL(CREATE_TABLE_QUIZ);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZTAKER);
        onCreate(db);
    }

    public long createQuizTakerUser(QuizTakerUser qtUser){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERID,qtUser.getUserID());
        values.put(KEY_PASSWORD,qtUser.getPassword());
        values.put(KEY_TIME,qtUser.getTime());
        //values.put(KEY_QUIZTAKEN,qtUser.getQuizTaken());
        values.put(KEY_QUIZTAKEN,0);
        //values.put(KEY_THENTH,0);
        //insert row
        long qtId = db.insert(TABLE_QUIZTAKER, null, values);
        return qtId;
    }

    public long createQuiz(QuizOptionAnswer qoa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(KEY_QUIZID, "null");
        values.put(KEY_QUESTION,qoa.getQuestion());
        values.put(KEY_OPTIONA,qoa.getOptionA());
        values.put(KEY_OPTIONB,qoa.getOptionB());
        values.put(KEY_OPTIONC,qoa.getOptionC());
        values.put(KEY_OPTIOND,qoa.getOptionD());
        values.put(KEY_ANSWER,qoa.getAnswer());
        //insert row
        long qId1 = db.insert(TABLE_QUIZ, null, values);
        return qId1;
    }

    /*public int setQuestionAnswerToZero(QuizTakerUser quizTakerUser){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUIZTAKEN,0);
        values.put(KEY_THENTH,0);
        return db.update(TABLE_QUIZTAKER, values, KEY_USERID + " = ?", new String[]{quizTakerUser.getUserID()});
    }*/

    public QuizTakerUser getQuizTakerUser(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_QUIZTAKER + " WHERE "
                + KEY_USERID + " = '" + userId + "'";
        Log.e(LOG, selectQuery);
        Cursor c= db.rawQuery(selectQuery, null);
        if (c.getCount() == 0) {
            return null;
        }
        c.moveToFirst();
        QuizTakerUser qtu = new QuizTakerUser();
        qtu.setUserID(c.getString(c.getColumnIndex(KEY_USERID)));
        //qtu.setCorrectAnswer(c.getInt(c.getColumnIndex(KEY_CORRECTANSWER)));
        qtu.setPassword(c.getString(c.getColumnIndex(KEY_PASSWORD)));
        qtu.setQuizTaken(c.getString(c.getColumnIndex(KEY_QUIZTAKEN)));
        qtu.setTime(c.getInt(c.getColumnIndex(KEY_TIME)));
        //qtu.setTheNth(c.getInt(c.getColumnIndex(KEY_THENTH)));
        c.close();
        return qtu;
    }

    public int getQuizCount(){
        String countQuery = "SELECT  * FROM " + TABLE_QUIZ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public int getUserCount(){
        String countQuery = "SELECT  * FROM " + TABLE_QUIZTAKER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public int getUserCountForUser(String userId){
        String countQuery = "SELECT  * FROM " + TABLE_QUIZTAKER + " WHERE "
                + KEY_USERID + " = '" + userId + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }


    public QuizOptionAnswer getQuizOptionAnswer(int quizId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_QUIZ + " WHERE "
                + KEY_QUIZID + " = " + quizId;
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c == null) {
            return null;
        }
        c.moveToFirst();
        QuizOptionAnswer qoa = new QuizOptionAnswer();
        qoa.setQuestion(c.getString(c.getColumnIndex(KEY_QUESTION)));
        qoa.setOptionA(c.getString(c.getColumnIndex(KEY_OPTIONA)));
        qoa.setOptionB(c.getString(c.getColumnIndex(KEY_OPTIONB)));
        qoa.setOptionC(c.getString(c.getColumnIndex(KEY_OPTIONC)));
        qoa.setOptionD(c.getString(c.getColumnIndex(KEY_OPTIOND)));
        qoa.setAnswer(c.getString(c.getColumnIndex(KEY_ANSWER)));
        c.close();
        return qoa;
    }

    public int updateQuizTaker(String userId, HashSet<Integer> setQuizTaken){
        StringBuilder sb = new StringBuilder();
        for (Integer sqt : setQuizTaken) {
            sb.append(sqt);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUIZTAKEN, sb.toString());

        // updating row
        return db.update(TABLE_QUIZTAKER, values, KEY_USERID + " = ?",
                new String[] { userId });
    }
}
