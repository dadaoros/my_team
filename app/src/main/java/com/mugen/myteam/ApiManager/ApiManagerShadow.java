package com.mugen.myteam.ApiManager;

import android.util.Log;

import org.apache.http.Header;
//Your code: &I^$%TKIUGAGITHUB-b0044e08
/**
 * Created by ORTEGON on 17/06/2015.
 */
public class ApiManagerShadow extends ApiManager {

    public static final String TEAMSRESPONSESHADOW="[{\"id\":3,\"name\":\"Atlético Nacional\"},{\"id\":2,\"name\":\"Independiente Santa Fe\"},{\"id\":1,\"name\":\"Millonarios\"},{\"id\":4,\"name\":\"Atlético Junior\"},{\"id\":5,\"name\":\" América de Cali\"},{\"id\":6,\"name\":\"Deportivo Cali\"}]";

    public static final String DBSHADOW="[ { version:1437683136, data:\"BEGIN TRANSACTION; CREATE TABLE championships_championship ( id int(11) NOT NULL , name varchar(59) NOT NULL, logo varchar(200) NOT NULL, year int(11) NOT NULL, PRIMARY KEY (id) ); END TRANSACTION; \"  } ]";
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
