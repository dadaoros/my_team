package com.mugen.myteam.ApiManager;

import com.mugen.myteam.ApiManager.ApiHandler;
import com.mugen.myteam.ApiManager.ApiManager;
import com.mugen.myteam.ApiManager.DownloadTeamsHandler;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 * Created by ORTEGON on 17/06/2015.
 */
public class ApiManagerShadow extends ApiManager{

    public static final String TEAMSRESPONSESHADOW="[{\"id\":3,\"name\":\"Atlético Nacional\"},{\"id\":2,\"name\":\"Independiente Santa Fe\"},{\"id\":1,\"name\":\"Millonarios\"},{\"id\":4,\"name\":\"Atlético Junior\"},{\"id\":5,\"name\":\" América de Cali\"},{\"id\":6,\"name\":\"Deportivo Cali\"}]";


    @Override
    public void execute(String URL) {
        if (URL == URLTEAMS) {
            Header[] headers = {};
            byte[] a=TEAMSRESPONSESHADOW.getBytes();
            super.getHandler().onSuccess(0, headers, a);
            super.getHandler().onFinish();
        }
    }
}
