package com.mugen.myteam.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ORTEGON on 27/05/2015.
 */
public class AlmacenSQLite extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;
    private static final String DB_NAME="My_Team";
    public AlmacenSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TeamsDataSource.CREATE_TABLE_TEAMS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
