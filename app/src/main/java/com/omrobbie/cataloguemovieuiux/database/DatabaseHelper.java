/*
 * Created by omrobbie.
 * Copyright (c) 2018. All rights reserved.
 * Last modified 11/11/17 3:14 PM.
 */

package com.omrobbie.cataloguemovieuiux.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.omrobbie.cataloguemovieuiux.provider.FavoriteColumns;

/**
 * Created by omrobbie on 09/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "catalogue_movie";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_MOVIE = "create table " + FavoriteColumns.TABLE_NAME + " (" +
                FavoriteColumns.COLUMN_ID + " integer primary key autoincrement, " +
                FavoriteColumns.COLUMN_TITLE + " text not null, " +
                FavoriteColumns.COLUMN_BACKDROP + " text not null, " +
                FavoriteColumns.COLUMN_POSTER + " text not null, " +
                FavoriteColumns.COLUMN_RELEASE_DATE + " text not null, " +
                FavoriteColumns.COLUMN_VOTE + " text not null, " +
                FavoriteColumns.COLUMN_OVERVIEW + " text not null " +
                ");";

        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE MOVIE IF EXISTS " + FavoriteColumns.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
