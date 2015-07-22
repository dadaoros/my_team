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
    public static final String URL_TEAMS = "http://192.168.0.8/teams/";
    public static final String URL_DUMPDATA="http://192.168.0.15:8000/database/data/";
    public static final String URL_DUMP="http://192.168.0.8/database/data/";
    public static final String URL_UPDATES="http://127.0.0.1:8000/version/";
    public void setHandler(AsyncHttpResponseHandler handler) {
        this.handler=handler;
    }

    public void execute(String URL) {
        AsyncHttpClient client=new AsyncHttpClient(8000);
        client.get(URL, handler);

    }
}