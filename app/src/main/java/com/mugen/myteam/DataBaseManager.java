package com.mugen.myteam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.mugen.myteam.DB.AlmacenSQLite;
import com.mugen.myteam.DB.TeamsDataSource;
import com.mugen.myteam.Models.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ORTEGON on 13/06/2015.
 */
public class DataBaseManager {

    public List getTeams(Context context){
        SQLiteDatabase db = AlmacenSQLite.getAlmacenInstance(context).getReadableDatabase();

        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                TeamsDataSource.Teams.TEAMNAME,
                TeamsDataSource.Teams.TEAM_IMAGE_URI,
                TeamsDataSource.Teams.TEAMNAME,
                TeamsDataSource.Teams.TEAMNAME,
        };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                TeamsDataSource.Teams.TEAMNAME + " DESC";
        Cursor c = null;
        List teams=new ArrayList();
        try {
            c=db.query(
                    TeamsDataSource.TEAMS_TABLENAME,  // The table to query
                    projection,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    sortOrder                                 // The sort order
            );

            if (c.moveToFirst() == false){
                Log.d("cursor","vacio");
            }

            int nameColumn = c.getColumnIndex(TeamsDataSource.Teams.TEAMNAME);

            for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
                teams.add( c.getString(nameColumn) );
            }
        }catch (SQLiteException e){

        }

        return teams;
    }
    public boolean putTeams(List<Team> list){
        SQLiteDatabase db = AlmacenSQLite.getAlmacenInstance().getWritableDatabase();
        if (db!=null) {
            // Create a new map of values, where column names are the keys
            for(int i=0;i<list.size();i++) {
                ContentValues values = new ContentValues();
                values.put(TeamsDataSource.Teams._ID, ((Team)list.get(i)).getID());
                values.put(TeamsDataSource.Teams.TEAMNAME, ((Team)list.get(i)).getName());
                values.put(TeamsDataSource.Teams.TEAM_IMAGE_URI, "");
                values.put(TeamsDataSource.Teams.TEAMCITY, "");
                values.put(TeamsDataSource.Teams.TEAMSTADIUM, ((Team)list.get(i)).getName());

                // Insert the new row, returning the primary key value of the new row
                long newRowId;
                try {
                    newRowId = db.insertOrThrow(
                            TeamsDataSource.TEAMS_TABLENAME,
                            null,
                            values);
                }catch (SQLiteConstraintException e){
                    Log.w("Agrega Registro",e.getMessage());
                }
            }
            return true;
        }else
            return false;
    }
    public boolean createDatabase(){
        return true;
    }
}
