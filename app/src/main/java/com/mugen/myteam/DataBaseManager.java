package com.mugen.myteam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.mugen.myteam.DB.AlmacenSQLite;
import com.mugen.myteam.DB.TeamsDataSource;
import com.mugen.myteam.Models.Team;
import com.mugen.myteam.Models.TeamRow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

            if (!c.moveToFirst()){
                Log.d("cursor","vacio");
            }

            int nameColumn = c.getColumnIndex(TeamsDataSource.Teams.TEAMNAME);

            for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
                teams.add( c.getString(nameColumn) );
            }

            c.close();
        }catch (SQLiteException e){

        }
        db.close();

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
            db.close();
            return true;
        }else
            return false;
    }
    public boolean isInitialized(Context ctx){
        SQLiteDatabase db =AlmacenSQLite.getAlmacenInstance(ctx).getReadableDatabase();

        boolean isInit=DatabaseUtils.queryNumEntries(db, TeamsDataSource.VERSIONS_TABLENAME) > 0;

        return isInit;
    }
    public List getTeamCalendar(Context context){
        String sql="SELECT * FROM championships_championship_team INNER JOIN championships_match ON (team_id=local_team_id OR team_id=visitor_team_id) WHERE team_id=1 AND championship_id=2";
        return null;
    }

    public List getTeamRows(Context context) {
        List rows=new ArrayList();

        SQLiteDatabase db = AlmacenSQLite.getAlmacenInstance(context).getReadableDatabase();
        String sql0="SELECT team_id FROM championships_championship_team WHERE championship_id=42 AND c_group=1";
        Cursor c0=null;
        int[] ids=null;
        try {
            c0=db.rawQuery(sql0, null);
        } catch (SQLException e) {
            Log.d("SQLERROR", e.toString());
        }
        if(c0!=null) {
            ids=new int[c0.getCount()];
            if (!c0.moveToFirst()) {
                Log.d("cursor", "vacio");
            }
            int i=0;
            for (c0.moveToFirst(); !c0.isAfterLast(); c0.moveToNext()) {
                ids[i]=c0.getInt(0);
                i++;
            }
            c0.close();
        }
        String sql="SELECT * FROM championships_championship_team INNER JOIN championships_match ON (team_id=local_team_id OR team_id=visitor_team_id) INNER JOIN championships_team ON(championships_team.id=team_id) WHERE championships_championship_team.championship_id=42 AND championships_match.championship_id=42 AND championships_championship_team.c_group=1";
        Cursor c = null;

        try {
            c=db.rawQuery(sql, null);
        } catch (SQLException e) {
            Log.d("SQLERROR", e.toString());
        }
        if(c!=null && ids!=null) {
            for (int i = 0; i < ids.length; i++) {
                int columnaNombreEquipo = c.getColumnIndex("name");
                int columnaIdEquipo = c.getColumnIndex("team_id");
                int columnaEquipoLocal = c.getColumnIndex("local_team_id");
                int columnaGolesLocal = c.getColumnIndex("local_goals");
                int columnaGolesVisitante = c.getColumnIndex("visitor_goals");

                int golesFavorTotal = 0, golesContraTotal = 0, puntosTotal = 0, partidosJugados = 0, partidosEmpatados = 0, partidosGanados = 0, partidosPerdidos = 0;
                String nombreEquipo = "";
                if (!c.moveToFirst()) {
                    Log.d("cursor", "vacio");
                }
                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

                    int idEquipoActual = c.getInt(columnaIdEquipo);
                    if (c.getInt(columnaIdEquipo) == ids[i]) {
                        nombreEquipo = c.getString(columnaNombreEquipo);
                        partidosJugados++;
                        int equipoLocalId = c.getInt(columnaEquipoLocal);
                        if (idEquipoActual == equipoLocalId) {
                            int golesF = c.getInt(columnaGolesLocal);
                            int golesC = c.getInt(columnaGolesVisitante);
                            if (golesF > golesC) {
                                puntosTotal += 3;
                                partidosGanados++;
                            } else if (golesF == golesC) {
                                puntosTotal++;
                                partidosEmpatados++;
                            } else partidosPerdidos++;
                            golesContraTotal += golesC;
                            golesFavorTotal += golesF;
                        } else {
                            int golesC = c.getInt(columnaGolesLocal);
                            int golesF = c.getInt(columnaGolesVisitante);
                            if (golesF > golesC) {
                                puntosTotal += 3;
                                partidosGanados++;
                            } else if (golesF == golesC) {
                                puntosTotal++;
                                partidosEmpatados++;
                            } else partidosPerdidos++;
                            golesContraTotal += golesC;
                            golesFavorTotal += golesF;
                        }
                    }

                }
                rows.add(new TeamRow(nombreEquipo, partidosJugados, partidosGanados, partidosPerdidos, partidosEmpatados, golesFavorTotal, golesContraTotal, golesFavorTotal - golesContraTotal, puntosTotal));

            }

            c.close();
        }
        db.close();

        Collections.sort(rows,Collections.reverseOrder());
        return rows;
    }


}
