package com.mugen.myteam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.mugen.myteam.DB.AlmacenSQLite;
import com.mugen.myteam.Models.CalendarItem;
import com.mugen.myteam.Models.Team;
import com.mugen.myteam.Models.TeamRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.mugen.myteam.DB.TeamsDataSource.*;

/**
 * Created by ORTEGON on 13/06/2015.
 */
public class DataBaseManager {

    public List getTeams(Context context){
        SQLiteDatabase db = AlmacenSQLite.getAlmacenInstance(context).getReadableDatabase();

        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                Teams.NAME,
                Teams.TEAM_IMAGE_URI,
                Teams.NAME,
                Teams.NAME,
        };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                Teams.NAME + " DESC";
        Cursor c = null;
        List teams=new ArrayList();
        try {
            c=db.query(
                    TEAMS_TABLENAME,  // The table to query
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

            int nameColumn = c.getColumnIndex(Teams.NAME);

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
                values.put(Teams._ID, ((Team)list.get(i)).getID());
                values.put(Teams.NAME, ((Team)list.get(i)).getName());
                values.put(Teams.TEAM_IMAGE_URI, "");
                values.put(Teams.TEAMCITY, "");

                // Insert the new row, returning the primary key value of the new row
                long newRowId;
                try {
                    newRowId = db.insertOrThrow(
                            TEAMS_TABLENAME,
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

        boolean isInit=DatabaseUtils.queryNumEntries(db, VERSIONS_TABLENAME) > 0;

        return isInit;
    }
    public String getLastUpdate(Context ctx){
        String lastUpdate=null;
        SQLiteDatabase db =AlmacenSQLite.getAlmacenInstance(ctx).getReadableDatabase();
        String[] projection = {
                Versions.UPDATE
        };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                Versions.UPDATE + " DESC";
        Cursor c = null;
        try {
            c=db.query(
                    VERSIONS_TABLENAME,  // The table to query
                    projection,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    sortOrder                                 // The sort order
            );

            int updateIDColumn = c.getColumnIndex(Versions.UPDATE);

            if (!c.moveToFirst()){
                Log.d("cursor","vacio");
            }else{
                lastUpdate=c.getString(updateIDColumn);
            }

            c.close();
        }catch (SQLiteException e){
            Log.d("ErrorSQL", e.getMessage());
        }
        db.close();
        return lastUpdate;
    }
    public List getTeamCalendar(Context context, int campeonato){
        List rows=new ArrayList();
        String my_team_id="3";
        String filtraCampeonato="";
        if(campeonato!=0)
            filtraCampeonato="AND championship_id="+campeonato;
        //cuando campeonato es cero muestra el calendario de todos los torneos

        String sql="SELECT championship_id,match_date,date_number,chp.name,localt.logo,visitor.logo,localt.name,"+Match.LGOALS+",visitor.name,"+Match.VGOALS+" FROM "+ TEAMS_TABLENAME+" AS localt INNER JOIN "+MATCHES_TABLENAME+" AS matcht ON (localt.id="+Match.LTEAM +") INNER JOIN "+TEAMS_TABLENAME+" AS visitor ON  (visitor.id="+Match.VTEAM +") INNER JOIN championships_championship AS chp ON (chp.id=championship_id)WHERE ("+Match.VTEAM +"="+my_team_id+" OR "+Match.LTEAM +"="+my_team_id+") "+filtraCampeonato ;
        SQLiteDatabase db = AlmacenSQLite.getAlmacenInstance(context).getReadableDatabase();
        Cursor c = null;
        try{
            c=db.rawQuery(sql,null);
        }catch (SQLiteException e){
            Log.d("SQLERROR", e.toString());
        }
        if(c!=null) {
            String[] names=c.getColumnNames();
            int columnaEquipoLocal = 6;
            int columnaEquipoVisitante = 8;
            //se asignan quemadas en codigo dado que tienen el mismo nombre en el query
            int columnaNumeroFecha=c.getColumnIndex("date_number");
            int columnaGolesLocal = c.getColumnIndex(Match.LGOALS);
            int columnaGolesVisitante = c.getColumnIndex(Match.VGOALS);
            int columnaHoraFecha = c.getColumnIndex("match_date");
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!c.moveToFirst()) {
                Log.d("cursor", "vacio");
            }

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                Date date = null;
                try {
                    date=formatoDeFecha.parse(c.getString(columnaHoraFecha));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                rows.add(new CalendarItem(c.getString(columnaEquipoLocal),c.getString(columnaEquipoVisitante),date,c.getString(columnaNumeroFecha),c.getString(columnaGolesLocal),c.getString(columnaGolesVisitante),false));
            }
            c.close();
        }
        db.close();
        return rows;
    }

    public List getTeamRows(Context context, int championship) {
        List rows=new ArrayList();

        SQLiteDatabase db = AlmacenSQLite.getAlmacenInstance(context).getReadableDatabase();
        String sql0="SELECT team_id FROM championships_championship_team WHERE championship_id="+String.valueOf(championship)+" AND c_group=1";
        Cursor c0=null;
        int[] ids=null;
        try {
            c0=db.rawQuery(sql0, null);
        } catch (SQLiteException e) {
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
        String sql="SELECT * FROM championships_championship_team "+INNER_JOIN+MATCHES_TABLENAME+" ON (team_id="+Match.LTEAM+" OR team_id="+Match.VTEAM+")"+INNER_JOIN+TEAMS_TABLENAME+" ON("+TEAMS_TABLENAME+".id=team_id) WHERE championships_championship_team.championship_id="+String.valueOf(championship)+" AND "+MATCHES_TABLENAME+".championship_id="+String.valueOf(championship)+" AND championships_championship_team.c_group=1";
        Cursor c = null;

        try {
            c=db.rawQuery(sql, null);
        } catch (SQLException e) {
            Log.d("SQLERROR", e.toString());
        }
        if(c!=null && ids!=null) {
            for (int i = 0; i < ids.length; i++) {
                int columnaNombreEquipo = c.getColumnIndex(Teams.NAME);
                int columnaIdEquipo = c.getColumnIndex("team_id");
                int columnaEquipoLocal = c.getColumnIndex(Match.LTEAM);
                int columnaGolesLocal = c.getColumnIndex(Match.LGOALS);
                int columnaGolesVisitante = c.getColumnIndex(Match.VGOALS);

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
