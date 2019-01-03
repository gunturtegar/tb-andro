package com.guntur.aldy.cooking.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//helper untuk membuat database bernama findjob.db
public class UserHelper extends SQLiteOpenHelper {

    //menyimpan variable database name
    private static final String DATABASE_NAME = "cooking.db";
    //menyimpan variable untuk database version
    private static final int DATABASE_VERSION = 2;


    //constructor userhelper
    public UserHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //fungsi yang di jalankan pada saat pembuatan database
    @Override
    public void onCreate(SQLiteDatabase db) {
        //membuat table user
        String sql = "CREATE TABLE user (id integer primary key AUTOINCREMENT, nama text, email text, password text);";
        // mengeksekusi perintah sql
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table jika ada
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    //fungsi untuk menambahkan user
    public void addUser(String nama,String email, String password){
        //mengambil database dengan permision write
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "insert into user (nama,email,password) values ('"+nama+"','"+email+"','"+password+"')";
        db.execSQL(sql);
    }

    //fungsi untuk mengecek apa email dan password yang di inputkan
    public boolean autentikasi(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from user where email='"+email+"' AND password='"+password+"'";
        Cursor c = db.rawQuery(sql,null,null);
        int rowCount = c.getCount();
        if (rowCount >= 1){
            return true;
        }
        return false;
    }
}