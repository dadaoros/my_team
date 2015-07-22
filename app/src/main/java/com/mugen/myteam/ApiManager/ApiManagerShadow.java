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
    public static final String UPDATES_SHADOW="[{\"version\":2,\"sql\":\"Segundo SQL\"},{\"version\":3,\"sql\":\"Tercer SQL :)\"}]";

    @Override
    public void execute(String URL) {
        Header[] headers = {};
        byte[] a=null;
        switch (URL){
            case URL_TEAMS:
                a=TEAMSRESPONSESHADOW.getBytes();
                super.getHandler().onSuccess(0, headers, a);
                super.getHandler().onFinish();
                break;
            case URL_DUMP:
                a=DBSHADOW.getBytes();
                super.getHandler().onSuccess(0, headers, a);
                super.getHandler().onFinish();
                break;
            default:
                a=UPDATES_SHADOW.getBytes();
                super.getHandler().onSuccess(0, headers, a);
                super.getHandler().onFinish();
                break;

        }
    }
}
