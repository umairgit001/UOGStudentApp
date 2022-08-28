package com.example.assign_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "Userdata.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(name TEXT primary key, contact TEXT, dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertData(String name, String roll, String faculty, String credits, String email, String phone, String program, String semester, String fee) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("roll", roll);
        contentValues.put("faculty", faculty);
        contentValues.put("credits", credits);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("program", program);
        contentValues.put("semester", semester);
        contentValues.put("fee", fee);


        long resultData = DB.insert("Userdetails", null, contentValues);
        if (resultData == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateData(String name, String roll, String faculty, String credits, String email, String phone, String program, String semester, String fee) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("roll", roll);
        contentValues.put("faculty", faculty);
        contentValues.put("credits", credits);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("program", program);
        contentValues.put("semester", semester);
        contentValues.put("fee", fee);

        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?",new String[]{name});
        if (cursor.getCount() > 0) {
            long resultData = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (resultData == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteData(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?",new String[]{name});
        if (cursor.getCount() > 0) {
            long resultData = DB.delete("Userdetails", "name=?", new String[]{name});
            if (resultData == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}
