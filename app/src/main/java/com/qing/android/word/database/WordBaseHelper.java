package com.qing.android.word.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.qing.android.word.database.WordDbSchema.WordTable;

/**
 * Created by qing on 2017/5/22.
 */

public class WordBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "wordBase.db";

    public WordBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        DbManager dbHold = new DbManager(context);
        dbHold.copyDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*

        db.execSQL("create table " + WordTable.NAME + "(" +
        " _id integer primary key autoincrement, " +
                        WordTable.Cols.UUID + ", " +
                        WordTable.Cols.SPELL + ", " +
                        WordTable.Cols.MEAN + ")"
        );

        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
