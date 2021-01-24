package com.example.signin.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
/*
    Manipulate with database
 */

public class AccountDB extends SQLiteOpenHelper {
    public AccountDB(Context context) {
        super(context, "Userdata.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create Table user_table (name TEXT primary key, email TEXT, password TEXT)";
        db.execSQL(query);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "drop Table if exists user_table";
        db.execSQL(query);
        db.close();
    }

    /**
     * Add new user to data base
     * @param name String
     * @param email String
     * @param password String
     * @return true if Successfully add new user
     */
    public boolean addUser(String name, String email, String password){
        //TODO: deal with duplicate PK
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        //check the duplicate of username and email
        String query = "Select * from user_table where name='"+name+"'";
        String query2 = "Select * from user_table where email='"+email+"'";
        Cursor cursor = db.rawQuery(query, null);
        Cursor cursor2 = db.rawQuery(query2,null);
        if(cursor.getCount()>0 || cursor2.getCount()>0) return false;
        cursor.close();
        cursor2.close();
        long result=db.insert("user_table", null, contentValues);
        db.close();
        if(result==-1){
            Log.e("Add new User", "Insert new user failed");
            return false;
        }else{
            Log.d("Add new User", "Insert new user success");
            return true;
        }
    }

    /**
     * Get the user Records
     * @param name String
     * @return user Cursor
     */
    public Cursor getUser(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from user_table where name='"+name+"'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    /**
     * update Information
     * @param name
     * @param new_password
     */
    public void updateUser(String name, String new_password){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "update user_table set password ='" +new_password+"' where name='"+name+"'";
        Log.d("updata datbase", "updateName: query: " + query);
        db.execSQL(query);
        db.close();
    }


}
