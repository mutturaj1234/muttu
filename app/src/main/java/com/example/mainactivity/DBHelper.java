package com.example.mainactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME= "Login.db";
    public DBHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MYDB) {
        MYDB.execSQL("create table users(username TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MYDB, int i, int i1) {
MYDB.execSQL("drop table if exists users");
    }
    public Boolean insertData (String username,String password){
        SQLiteDatabase MYDB = this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long results=MYDB.insert("users",null,contentValues);
        if(results==-1)return false;
        else
            return true;
    }
    public Boolean Checkusername(String username){
        SQLiteDatabase MYDB=this.getReadableDatabase();
        Cursor cursor = MYDB.rawQuery("select * from users where username = ?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username,String password){
        SQLiteDatabase MYDB=this.getReadableDatabase();
        Cursor cursor=MYDB.rawQuery("select *from users where username = ? and password = ? ", new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
}
