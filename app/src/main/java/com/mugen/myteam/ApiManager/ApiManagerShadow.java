package com.mugen.myteam.ApiManager;

import android.util.Log;
import android.view.View;

import com.mugen.myteam.LoaderActivity;
import com.mugen.myteam.R;

import org.apache.http.Header;
//Your code: &I^$%TKIUGAGITHUB-b0044e08
/**
 * Created by ORTEGON on 17/06/2015.
 */
public class ApiManagerShadow extends ApiManager {

    public static final String TEAMSRESPONSESHADOW="[{\"id\":3,\"name\":\"Atlético Nacional\"},{\"id\":2,\"name\":\"Independiente Santa Fe\"},{\"id\":1,\"name\":\"Millonarios\"},{\"id\":4,\"name\":\"Atlético Junior\"},{\"id\":5,\"name\":\" América de Cali\"},{\"id\":6,\"name\":\"Deportivo Cali\"}]";

    public static final String DBSHADOW="[\n" +
            "    {\n" +
            "        version:1437683140,\n" +
            "        data:\"BEGIN TRANSACTION;\n" +
            "CREATE TABLE championships_championship (\n" +
            "  id int(11) NOT NULL ,\n" +
            "  name varchar(59) NOT NULL,\n" +
            "  logo varchar(200) NOT NULL,\n" +
            "  year int(11) NOT NULL,\n" +
            "  PRIMARY KEY (id)\n" +
            ");\n" +
            "INSERT INTO championships_championship VALUES (42,'Apertura Colombia','http://thumb.resfu.com/media/img/flags/44x27/co.png',2015);\n" +
            "INSERT INTO championships_championship VALUES (50,'Clausura Colombia','http://thumb.resfu.com/media/img/flags/44x27/co.png',2015);\n" +
            "INSERT INTO championships_championship VALUES (597,'Apertura Colombia - Play Offs','http://thumb.resfu.com/media/img/flags/44x27/co.png',2015);\n" +
            "INSERT INTO championships_championship VALUES (846,'Copa Colombia','http://thumb.resfu.com/media/img/flags/44x27/co.png',2015);\n" +
            "INSERT INTO championships_championship VALUES (1135,'Primera B Colombia - Playoff Clausura','http://thumb.resfu.com/media/img/flags/44x27/co.png',2015);\n" +
            "INSERT INTO championships_championship VALUES (1489,'Superliga de Colombia','http://thumb.resfu.com/media/img/flags/44x27/co.png',2015);\n" +
            "INSERT INTO championships_championship VALUES (1490,'Cuadrangulares Ascenso - Colombia','http://thumb.resfu.com/media/img/flags/44x27/co.png',2015);\n" +
            "INSERT INTO championships_championship VALUES (1630,'Primera B Colombia','http://thumb.resfu.com/media/img/flags/44x27/co.png',2015);\n" +
            "CREATE TABLE championships_championship_team (\n" +
            "  id int(11) NOT NULL ,\n" +
            "  championship_id int(11) NOT NULL,\n" +
            "  team_id int(11) NOT NULL,\n" +
            "  c_group int(11) NOT NULL,\n" +
            "  PRIMARY KEY (id)\n" +
            "  CONSTRAINT bae7af006f6dcff77ef20c81548d9998 FOREIGN KEY (championship_id) REFERENCES championships_championship (id),\n" +
            "  CONSTRAINT championships__team_id_6e6e7f684c347268_fk_championships_team_id FOREIGN KEY (team_id) REFERENCES championships_team (id)\n" +
            ");\n" +
            "INSERT INTO championships_championship_team VALUES (1,42,5,1);\n" +
            "INSERT INTO championships_championship_team VALUES (2,42,3,1);\n" +
            "INSERT INTO championships_championship_team VALUES (3,42,4,1);\n" +
            "CREATE TABLE championships_city (\n" +
            "  id int(11) NOT NULL ,\n" +
            "  name varchar(59) NOT NULL,\n" +
            "  PRIMARY KEY (id)\n" +
            ");\n" +
            "INSERT INTO championships_city VALUES (2,'Bogota');\n" +
            "INSERT INTO championships_city VALUES (5,'cartagena');\n" +
            "INSERT INTO championships_city VALUES (8,'ciudad de prueba');\n" +
            "INSERT INTO championships_city VALUES (9,'ciudad de prueba');\n" +
            "INSERT INTO championships_city VALUES (10,'Barrancabermeja');\n" +
            "CREATE TABLE championships_match (\n" +
            "  id int(11) NOT NULL ,\n" +
            "  match_date datetime NOT NULL,\n" +
            "  date_number int(11) NOT NULL,\n" +
            "  local_team_id int(11) NOT NULL,\n" +
            "  stadium_id int(11) NOT NULL,\n" +
            "  visitor_team_id int(11) NOT NULL,\n" +
            "  local_goals int(11) NOT NULL,\n" +
            "  visitor_goals int(11) NOT NULL,\n" +
            "  championship_id int(11) NOT NULL,\n" +
            "  played tinyint(1) NOT NULL,\n" +
            "  PRIMARY KEY (id)\n" +
            "  CONSTRAINT champion_local_team_id_2b6196acd4159104_fk_championships_team_id FOREIGN KEY (local_team_id) REFERENCES championships_team (id),\n" +
            "  CONSTRAINT champion_stadium_id_5330b18ac038417b_fk_championships_stadium_id FOREIGN KEY (stadium_id) REFERENCES championships_stadium (id),\n" +
            "  CONSTRAINT champi_visitor_team_id_566ee70f5adef619_fk_championships_team_id FOREIGN KEY (visitor_team_id) REFERENCES championships_team (id),\n" +
            "  CONSTRAINT D42d8d33d927f7036ba780b6a9992a88 FOREIGN KEY (championship_id) REFERENCES championships_championship (id)\n" +
            ");\n" +
            "INSERT INTO championships_match VALUES (2,'2015-07-23 21:43:06',1,3,1,5,3,3,42,1);\n" +
            "INSERT INTO championships_match VALUES (3,'2015-08-07 22:09:06',2,3,1,4,1,2,42,1);\n" +
            "CREATE TABLE championships_stadium (\n" +
            "  id int(11) NOT NULL ,\n" +
            "  name varchar(59) NOT NULL,\n" +
            "  capacity int(11) NOT NULL,\n" +
            "  city_id int(11) NOT NULL,\n" +
            "  PRIMARY KEY (id)\n" +
            "  CONSTRAINT championships__city_id_3f51b64b94ffb128_fk_championships_city_id FOREIGN KEY (city_id) REFERENCES championships_city (id)\n" +
            ");\n" +
            "INSERT INTO championships_stadium VALUES (1,'Campin',2500,2);\n" +
            "CREATE TABLE championships_team (\n" +
            "  id int(11) NOT NULL ,\n" +
            "  name varchar(59) NOT NULL,\n" +
            "  logo varchar(8) NOT NULL,\n" +
            "  city_id int(11) NOT NULL,\n" +
            "  short_name varchar(59) NOT NULL,\n" +
            "  PRIMARY KEY (id)\n" +
            "  CONSTRAINT championships__city_id_1dffc6e4e23b31d3_fk_championships_city_id FOREIGN KEY (city_id) REFERENCES championships_city (id)\n" +
            ");\n" +
            "INSERT INTO championships_team VALUES (3,'Millonarios F. C','f1.jpg',2,'shortName');\n" +
            "INSERT INTO championships_team VALUES (4,'Santa fe','f2.jpg',2,'shortName');\n" +
            "INSERT INTO championships_team VALUES (5,'Alianza Petrolera','f1.jpg',10,'shortName');\n" +
            "CREATE INDEX championships_match_championships_match_aadf6262 ON championships_match (local_team_id);\n" +
            "CREATE INDEX championships_match_championships_match_5c1e67db ON championships_match (stadium_id);\n" +
            "CREATE INDEX championships_match_championships_match_0fc7cc15 ON championships_match (visitor_team_id);\n" +
            "CREATE INDEX championships_match_championships_match_1db20e2f ON championships_match (championship_id);\n" +
            "CREATE INDEX championships_championship_team_championships_championship_championship_id_5ddce2d3e999df95_uniq ON championships_championship_team (championship_id,team_id);\n" +
            "CREATE INDEX championships_championship_team_championships_championship_team_f6a7ca40 ON championships_championship_team (team_id);\n" +
            "CREATE INDEX championships_stadium_championships__city_id_3f51b64b94ffb128_fk_championships_city_id ON championships_stadium (city_id);\n" +
            "CREATE INDEX championships_team_championships__city_id_1dffc6e4e23b31d3_fk_championships_city_id ON championships_team (city_id);\n" +
            "END TRANSACTION;\n" +
            "\"\n" +
            "    }\n" +
            "]";
    public static final String UPDATES_SHADOW="[{\"version\":2,\"sql\":\"Segundo SQL\"},{\"version\":3,\"sql\":\"Tercer SQL :)\"}]";
    LoaderActivity loaderActivity;
    public ApiManagerShadow(LoaderActivity loaderActivity) {
        this.loaderActivity=loaderActivity;
    }

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
                //a= ((String)loaderActivity.getResources().getText(R.string.dump)).getBytes();
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
