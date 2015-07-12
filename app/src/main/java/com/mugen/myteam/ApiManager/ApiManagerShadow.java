package com.mugen.myteam.ApiManager;

import android.util.Log;

import org.apache.http.Header;

/**
 * Created by ORTEGON on 17/06/2015.
 */
public class ApiManagerShadow extends ApiManager {

    public static final String TEAMSRESPONSESHADOW="[{\"id\":3,\"name\":\"Atlético Nacional\"},{\"id\":2,\"name\":\"Independiente Santa Fe\"},{\"id\":1,\"name\":\"Millonarios\"},{\"id\":4,\"name\":\"Atlético Junior\"},{\"id\":5,\"name\":\" América de Cali\"},{\"id\":6,\"name\":\"Deportivo Cali\"}]";

        public static final String DBSHADOW="BEGIN TRANSACTION;\n" +
            "CREATE TABLE \"championships_championship\" (\n" +
            "  \"id\" int(11) NOT NULL ,\n" +
            "  \"name\" varchar(59) NOT NULL,\n" +
            "  \"logo\" varchar(8) NOT NULL,\n" +
            "  \"year\" date NOT NULL,\n" +
            "  PRIMARY KEY (\"id\")\n" +
            ");\n" +
            "CREATE TABLE \"championships_championship_team\" (\n" +
            "  \"id\" int(11) NOT NULL ,\n" +
            "  \"championship_id\" int(11) NOT NULL,\n" +
            "  \"team_id\" int(11) NOT NULL,\n" +
            "  PRIMARY KEY (\"id\")\n" +
            "  CONSTRAINT \"championships__team_id_6e6e7f684c347268_fk_championships_team_id\" FOREIGN KEY (\"team_id\") REFERENCES \"championships_team\" (\"id\"),\n" +
            "  CONSTRAINT \"bae7af006f6dcff77ef20c81548d9998\" FOREIGN KEY (\"championship_id\") REFERENCES \"championships_championship\" (\"id\")\n" +
            ");\n" +
            "CREATE TABLE \"championships_city\" (\n" +
            "  \"id\" int(11) NOT NULL ,\n" +
            "  \"name\" varchar(59) NOT NULL,\n" +
            "  PRIMARY KEY (\"id\")\n" +
            ");\n" +
            "CREATE TABLE \"championships_match\" (\n" +
            "  \"id\" int(11) NOT NULL ,\n" +
            "  \"date\" datetime NOT NULL,\n" +
            "  \"date_number\" int(11) NOT NULL,\n" +
            "  \"local_team_id\" int(11) NOT NULL,\n" +
            "  \"stadium_id\" int(11) NOT NULL,\n" +
            "  \"visitor_team_id\" int(11) NOT NULL,\n" +
            "  PRIMARY KEY (\"id\")\n" +
            "  CONSTRAINT \"champi_visitor_team_id_566ee70f5adef619_fk_championships_team_id\" FOREIGN KEY (\"visitor_team_id\") REFERENCES \"championships_team\" (\"id\"),\n" +
            "  CONSTRAINT \"champion_local_team_id_2b6196acd4159104_fk_championships_team_id\" FOREIGN KEY (\"local_team_id\") REFERENCES \"championships_team\" (\"id\"),\n" +
            "  CONSTRAINT \"champion_stadium_id_5330b18ac038417b_fk_championships_stadium_id\" FOREIGN KEY (\"stadium_id\") REFERENCES \"championships_stadium\" (\"id\")\n" +
            ");\n" +
            "CREATE TABLE \"championships_services\" (\n" +
            "  \"id\" int(11) NOT NULL ,\n" +
            "  \"url\" varchar(20) NOT NULL,\n" +
            "  \"description\" varchar(40) NOT NULL,\n" +
            "  \"has_detail\" tinyint(1) NOT NULL,\n" +
            "  PRIMARY KEY (\"id\")\n" +
            ");\n" +
            "CREATE TABLE \"championships_stadium\" (\n" +
            "  \"id\" int(11) NOT NULL ,\n" +
            "  \"name\" varchar(59) NOT NULL,\n" +
            "  \"capacity\" int(11) NOT NULL,\n" +
            "  \"city_id\" int(11) NOT NULL,\n" +
            "  PRIMARY KEY (\"id\")\n" +
            "  CONSTRAINT \"championships__city_id_3f51b64b94ffb128_fk_championships_city_id\" FOREIGN KEY (\"city_id\") REFERENCES \"championships_city\" (\"id\")\n" +
            ");\n" +
            "CREATE TABLE \"championships_team\" (\n" +
            "  \"id\" int(11) NOT NULL ,\n" +
            "  \"name\" varchar(59) NOT NULL,\n" +
            "  \"logo\" varchar(8) NOT NULL,\n" +
            "  \"city_id\" int(11) NOT NULL,\n" +
            "  PRIMARY KEY (\"id\")\n" +
            "  CONSTRAINT \"championships__city_id_1dffc6e4e23b31d3_fk_championships_city_id\" FOREIGN KEY (\"city_id\") REFERENCES \"championships_city\" (\"id\")\n" +
            ");\n" +
            "CREATE INDEX \"championships_match_championships_match_aadf6262\" ON \"championships_match\" (\"local_team_id\");\n" +
            "CREATE INDEX \"championships_match_championships_match_5c1e67db\" ON \"championships_match\" (\"stadium_id\");\n" +
            "CREATE INDEX \"championships_match_championships_match_0fc7cc15\" ON \"championships_match\" (\"visitor_team_id\");\n" +
            "CREATE INDEX \"championships_championship_team_championships_championship_championship_id_5ddce2d3e999df95_uniq\" ON \"championships_championship_team\" (\"championship_id\",\"team_id\");\n" +
            "CREATE INDEX \"championships_championship_team_championships_championship_team_f6a7ca40\" ON \"championships_championship_team\" (\"team_id\");\n" +
            "CREATE INDEX \"championships_stadium_championships__city_id_3f51b64b94ffb128_fk_championships_city_id\" ON \"championships_stadium\" (\"city_id\");\n" +
            "CREATE INDEX \"championships_team_championships__city_id_1dffc6e4e23b31d3_fk_championships_city_id\" ON \"championships_team\" (\"city_id\");\n" +
            "END TRANSACTION;";

    public static final String DBDATASHADOW="BEGIN;\n"+
            "COPY championships_championship (id, name, logo, year) FROM stdin;\n"+
            "\\.\n"+
            "COPY championships_city (id, name) FROM stdin;\n"+
            "1\tBogotá\n"+
            "2\tMedellín\n"+
            "3\tBarranquilla\n"+
            "4\tCali\n"+
            "5\tNeiva\n"+
            "\\.\n"+
            "COPY championships_team (id, name, logo, founding, stadium, country, president, website, city_id) FROM stdin;\n"+
            "3\tAtlético Nacional\tf3.jpg\t1947-03-07\tAtanasio Girardot\tCO\tVíctor Marulanda\twww.atlnacional.com.co\t2\n"+
            "2\tIndependiente Santa Fe\tf2.jpg\t1941-02-28\tNemesio Camacho\tCO\tCésar Pastrana\twww.independientesantafe.co\t1\n"+
            "1\tMillonarios\tf1.jpg\t1946-06-18\tNemesio Camacho\tCO\tFelipe Gaitán Tovar\twww.millonarios.com.co\t1\n"+
            "4\tAtlético Junior\tf4.jpg\t1924-08-07\tMetropolitano Roberto Meléndez\tCO\tAlejandro Arteta\tAlejandro Arteta\t3\n"+
            "5\t América de Cali\tf5.jpg\t1918-12-21\tPascual Guerrero\tCO\tÁlvaro Guerrero\twww.america.com.co\t4\n"+
            "6\tDeportivo Cali\tf6.jpg\t2015-06-06\t1912-01-01\tCO\tHebert Celín\twww.deporcali.com\t4\n"+
            "7\tAtletico Huia\tf7.jpg\t2015-06-27\testadio\tCO\tlkshjkshkj\thttp;//jhskjhkjs.com\t5\n"+
            "\\.\n"+
            "COPY championships_championship_team (id, championship_id, team_id) FROM stdin;\n"+
            "\\.\n"+
            "COPY championships_stadium (id, name, capacity, city_id) FROM stdin;\n"+
            "1\tEstadio Olímpico Pascual Guerrero\t45000\t4\n"+
            "2\tEstadio Nemesio Camacho El Campin\t45000\t1\n"+
            "3\tEstadio Atanasio Girardot\t45000\t2\n"+
            "4\tEstadio Metropolitano Roberto Meléndez\t50000\t3\n"+
            "\\.\n"+
            "COPY championships_match (id, date, date_number, local_team_id, stadium_id, visitor_team_id) FROM stdin;\n"+
            "1\t2015-06-07 19:00:00-05\t1\t3\t2\t2\n"+
            "\\.\n"+
            "COPY championships_services (id, url, description, has_detail) FROM stdin;\n"+
            "1\tteams/\tteams: equipos\tt\n"+
            "2\tteams/all/\tTeam: toda la tabla \tf\n"+
            "3\tmatches/\tMatch: partidos\tt\n"+
            "4\tmatches/all/\tMatch: toda la tabla\tf\n"+
            "5\tchampionships\tchampionships\tt\n"+
            "6\tchampionships/all/\tchampionships/all/\tf\n"+
            "7\tstadiums/all/\tstadiums/all/\tf\n"+
            "8\tcities/all/\tcities/all/\tf\n"+
            "\\.\n"+
            "END;\n"+
            "\n"+
            "\n";
    @Override
    public void execute(String URL) {
        if (URL == URL_TEAMS) {
            Header[] headers = {};
            byte[] a=TEAMSRESPONSESHADOW.getBytes();
            super.getHandler().onSuccess(0, headers, a);
            super.getHandler().onFinish();
        }
        if(URL== URL_DUMP){

            Header[] headers = {};
            byte[] a=DBSHADOW.getBytes();
            super.getHandler().onSuccess(0, headers, a);
            super.getHandler().onFinish();
        }
    }
}
