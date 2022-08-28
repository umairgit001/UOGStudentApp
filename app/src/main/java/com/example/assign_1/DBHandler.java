package com.example.assign_1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.contentpager.content.Query;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "StudentDetails.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "student";
    private static final String ID_COL = "id";
    private static final String ROLL_COL = "roll";
    private static final String NAME_COL = "name";
    private static final String PROGRAM_COL = "program";
    private static final String EMAIL_COL = "email";
    private static final String FACULTY_COL = "faculty";
    private static final String CREDITS_COL = "credits";
    private static final String PHONE_COL = "phone";
    private static final String SEMESTER_COL = "semester";
    private static final String FEE_COL = "fee";

    private Context context;


    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + ROLL_COL + " TEXT,"
                + FACULTY_COL + " TEXT,"
                + CREDITS_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PHONE_COL + " TEXT,"
                + FEE_COL + " TEXT,"
                + SEMESTER_COL + " TEXT,"
                + PROGRAM_COL + " TEXT)";
        db.execSQL(query);
    }

    @SuppressLint("Range")
    public ArrayList<PersonalDetails> readStudentData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor studentDetails = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<PersonalDetails> studentArrayList = new ArrayList<>();
        if (studentDetails.moveToFirst()) {
            do {
                studentArrayList.add(new PersonalDetails(studentDetails.getString(studentDetails.getColumnIndex(NAME_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(ROLL_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(FACULTY_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(CREDITS_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(EMAIL_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(PHONE_COL)),
                        studentDetails.getInt(studentDetails.getColumnIndex(FEE_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(PROGRAM_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(SEMESTER_COL))));

            } while (studentDetails.moveToNext());
        }
        else {
            Log.d("Tag","There is no data");
        }

        studentDetails.close();
        return studentArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String addStudent(String Name, String RollNo, String Faculty, String NoOfCredits, String Email, String Phone, String TotalFee, String Program, String Semester) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, Name);
        values.put(ROLL_COL, RollNo);
        values.put(FACULTY_COL, Faculty);
        values.put(CREDITS_COL, NoOfCredits);
        values.put(EMAIL_COL, Email);
        values.put(PHONE_COL, Phone);
        values.put(FEE_COL, TotalFee);
        values.put(PROGRAM_COL, Program);
        values.put(SEMESTER_COL, Semester);


        long result = db.insert(TABLE_NAME, null, values);
        if(result == -1)
            return "Failed";
        else
            return  "Successfully inserted";

    }

    void updateData(String name, String roll, String faculty, String credits, String email, String phone, String program, String semester, String fee) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name",name);
        contentValues.put("roll", roll);
        contentValues.put("faculty", faculty);
        contentValues.put("credits", credits);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("fee", fee);
        contentValues.put("program", program);
        contentValues.put("semester", semester);


        long result = DB.update(TABLE_NAME,contentValues,"roll=?", new String[]{roll});

        if(result==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();

        }

        else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        }



    }

    void deleteData(String roll){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"roll=?",new String[]{roll});
        if(result==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("Range")
    public ArrayList<PersonalDetails> getDataByName(String Name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor studentDetails = db.rawQuery("Select * from student where Name=?", new String[]{Name});
        ArrayList<PersonalDetails> studentArrayList = new ArrayList<>();

        if (studentDetails.moveToFirst()) {
            do {
                studentArrayList.add(new PersonalDetails(studentDetails.getString(studentDetails.getColumnIndex(NAME_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(ROLL_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(FACULTY_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(CREDITS_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(EMAIL_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(PHONE_COL)),
                        studentDetails.getInt(studentDetails.getColumnIndex(FEE_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(PROGRAM_COL)),
                        studentDetails.getString(studentDetails.getColumnIndex(SEMESTER_COL))));

            } while (studentDetails.moveToNext());

        }
        else {
            Log.d("Tag","There is no data");
        }

        studentDetails.close();
        return studentArrayList;
    }



}
