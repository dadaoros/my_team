package com.mugen.myteam.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ORTEGON on 27/05/2015.
 */
public class AlmacenSQLite extends SQLiteOpenHelper {
    private static AlmacenSQLite almacen;
    private static final int DB_VERSION=1;
    private static final String DB_NAME="My_Team";
    private AlmacenSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TeamsDataSource.CREATE_TABLE_TEAMS);
        db.execSQL(TeamsDataSource.CREATE_TABLE_MATCHES);
        db.execSQL(TeamsDataSource.CREATE_TABLE_UPDATES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static AlmacenSQLite getAlmacenInstance(Context c) {
        if(almacen==null)
            almacen=new AlmacenSQLite(c);
        return almacen;
    }
    public static AlmacenSQLite getAlmacenInstance() {
        return almacen;
    }
    public void setContext(Context c){

    }
}
