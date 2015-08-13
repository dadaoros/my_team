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
    //http://200.118.215.195:8080/
    private AsyncHttpResponseHandler handler;
    public static final String IP="http://200.118.215.195/";
    public static final int PORT=8080;

    public static final String URL_TEAMS = IP+"teams/";
    public static final String URL_DUMP=IP+"database/data/";
    public static final String URL_UPDATES=IP+"version/";
    public void setHandler(AsyncHttpResponseHandler handler) {
        this.handler=handler;
    }

    public void execute(String URL) {
        AsyncHttpClient client=new AsyncHttpClient(PORT);
        client.get(URL, handler);

    }
}