package com.omrobbie.cataloguemovieuiux.provider;

import android.provider.BaseColumns;

/**
 * Created by omrobbie on 09/11/2017.
 */

public class FavoriteColumns implements BaseColumns {
    public static String TABLE_NAME = "favorite_movie";

    public static String COLUMN_ID = "_id";
    public static String COLUMN_TITLE = "title";
    public static String COLUMN_BACKDROP = "backdrop";
    public static String COLUMN_POSTER = "poster";
    public static String COLUMN_RELEASE_DATE = "release_date";
    public static String COLUMN_VOTE = "vote";
    public static String COLUMN_OVERVIEW = "overview";
    public static String COLUMN_GENRES = "genres";
    public static String COLUMN_POSTER_BELONGS = "poster_belongs";
    public static String COLUMN_BUDGET = "budget";
    public static String COLUMN_REVENUE = "revenue";
    public static String COLUMN_COMPANIES = "companies";
    public static String COLUMN_COUNTRIES = "countries";
}
