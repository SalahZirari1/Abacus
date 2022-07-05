package com.example.projectm;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Hashtable;

public class databaseClass extends SQLiteOpenHelper {
    public databaseClass(@Nullable Context context)
    {
        super(context,"ScoresDataBase", null,1);
        //context.deleteDatabase("ScoresDataBase");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table MainScoresTable (id integer primary key autoincrement,score text,game text,name text,difficulty text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists MainScoresTable");
        onCreate(db);
    }



    public void AddScore(String score,String game,String name,String difficulty){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put("score",score);
        values.put("game",game);
        values.put("name",name);
        values.put("difficulty",difficulty);

        db.insert("MainScoresTable",null,values);
    }

    public ArrayList<Hashtable<String, String>> getScoreInfos(){
        ArrayList<Hashtable<String, String>> l = new ArrayList<Hashtable<String, String>>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("select * from MainScoresTable",null);

        while (c.moveToNext()){
            Hashtable<String ,String > elt = new Hashtable<String,String >();
            elt.put("score",c.getString(1));
            elt.put("game",c.getString(2));
            elt.put("name",c.getString(3));
            elt.put("difficulty",c.getString(4));
            l.add(elt);
        }

        return l;
    }

    public String getHighestScore(){
        String topScore="";
        int n1=0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("select  score from MainScoresTable ",null);

        while (c.moveToNext()){

            topScore= c.getString(c.getColumnIndexOrThrow("score"));
            if (Integer.parseInt(topScore)>n1)
            {
                n1=Integer.parseInt(topScore);
            }
        }

        return String.valueOf(n1);
    }
    /*public ArrayList<Hashtable<String, String>> getNames(){
        ArrayList<Hashtable<String, String>> l = new ArrayList<Hashtable<String, String>>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("select * from MainScoresTable",null);

        while (c.moveToNext()){
            Hashtable<String ,String > elt = new Hashtable<String,String >();
            elt.put("score",c.getString(1));
            elt.put("game",c.getString(2));
            elt.put("name",c.getString(3));
            elt.put("difficulty",c.getString(4));
            l.add(elt);
        }

        return l;
    }*/
}
