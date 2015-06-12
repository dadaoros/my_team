package com.mugen.myteam.DB;

/**
 * Created by ORTEGON on 27/05/2015.
 */
public class TeamsDataSource {
    public static final String TEAMS_TABLENAME="Teams";
    public static final String MATCHES_TABLENAME="Matches";

    public static final String INT_TYPE="integer";
    public static final String STRING_TYPE="text";
    public static class ColumnTeams{
        public static final String TEAMID="team_id";
        public static final String TEAMNAME="team_name";
        public static final String TEAMCITY="team_city";
        public static final String TEAMSTADIUM="team_stadium";
    }
    public static final String CREATE_TABLE_TEAMS="CREATE TABLE "+TEAMS_TABLENAME+"("+
            ColumnTeams.TEAMID+" "+ INT_TYPE+" "+" primary key autoincrement,"+
            ColumnTeams.TEAMNAME+" "+STRING_TYPE+" not null,"+
            ColumnTeams.TEAMCITY+" "+STRING_TYPE+" not null,"+
            ColumnTeams.TEAMSTADIUM+" "+STRING_TYPE+" not null)";
}
