package com.mugen.myteam.ApiManager;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by ORTEGON on 17/06/2015.
 */
public class ApiManager{
    public AsyncHttpResponseHandler getHandler() {
        return handler;
    }

    private AsyncHttpResponseHandler handler;
    public static final String IP="http://192.168.0.8/";
    public static final int PORT=8000;

    public static final String URL_TEAMS = "http://192.168.0.8/teams/";
    public static final String URL_DUMP="http://192.168.0.8/database/data/";
    public static final String URL_UPDATES=IP+"version/";
    public void setHandler(AsyncHttpResponseHandler handler) {
        this.handler=handler;
    }

    public void execute(String URL) {
        AsyncHttpClient client=new AsyncHttpClient(PORT);
        client.get(URL, handler);

    }
}