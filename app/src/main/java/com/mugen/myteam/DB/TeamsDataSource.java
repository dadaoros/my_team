package com.mugen.myteam.DB;

import android.provider.BaseColumns;

/**
 * Created by ORTEGON on 27/05/2015.
 */
public class TeamsDataSource {


    public static final String TEAMS_TABLENAME="championships_team";
    public static final String MATCHES_TABLENAME="championships_match";
    public static final String VERSIONS_TABLENAME="Versions";

    public static final String INT_TYPE="integer";
    public static final String STRING_TYPE="text";
    public static final String NUMERIC_TYPE="NUMERIC";
    public static final String INNER_JOIN=" INNER JOIN ";
    public static final String OPEN="(";
    public static final String CLOSE=");";

    public static abstract class Versions implements BaseColumns{
        public static final String UPDATE="update_id";
    }

    public static abstract class Teams{
        public static final String _ID="id";
        public static final String NAME ="name";
        public static final String TEAM_IMAGE_URI="logo";
        public static final String TEAMCITY="city_id";
        public static final String TEAMSTADIUM="stadium";

    }
    public static abstract class Match implements BaseColumns{
        public static final String VTEAM ="visitor_team_id";
        public static final String LTEAM ="local_team_id";
        public static final String LGOALS="local_goals";
        public static final String VGOALS="visitor_goals";
        public static final String MATCH_DATE_TIME="match_date";
        public static final String PLAYED="is_played";
        public static final String MATCH_STADIUM="match_stadium";
    }
    public static final String CREATE_TABLE_VERSIONS="CREATE TABLE "+VERSIONS_TABLENAME+OPEN+
            Versions._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            Versions.UPDATE+" "+NUMERIC_TYPE+ CLOSE;
    public static final String CREATE_TABLE_TEAMS="CREATE TABLE "+TEAMS_TABLENAME+OPEN+
            Teams._ID + " INTEGER PRIMARY KEY," +
            Teams.NAME +" "+STRING_TYPE+" not null,"+
            Teams.TEAMCITY+" "+STRING_TYPE+" not null,"+
            Teams.TEAM_IMAGE_URI+" "+STRING_TYPE+" not null,"+
            Teams.TEAMSTADIUM+" "+STRING_TYPE+" not null)";
    public static final String CREATE_TABLE_MATCHES="CREATE TABLE "+MATCHES_TABLENAME+OPEN+
            Match._ID+" "+ INT_TYPE+" "+" primary key autoincrement,"+
            Match.LTEAM +" "+INT_TYPE+" not null,"+
            Match.VTEAM +" "+INT_TYPE+" not null,"+
            Match.LGOALS+" "+INT_TYPE+" not null,"+
            Match.VGOALS+" "+INT_TYPE+" not null,"+
            Match.MATCH_DATE_TIME+" "+NUMERIC_TYPE+" not null,"+
            Match.PLAYED+" "+NUMERIC_TYPE+" not null,"+
            Match.MATCH_STADIUM+" "+STRING_TYPE+CLOSE;

}
