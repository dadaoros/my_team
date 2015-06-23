package com.mugen.myteam.ApiManager;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by ORTEGON on 17/06/2015.
 */
public class ApiManager {
    public AsyncHttpResponseHandler getHandler() {
        return handler;
    }

    private AsyncHttpResponseHandler handler;
    public static final String URLTEAMS= "http://127.0.0.1:8000/teams/.json ";
    public void setHandler(AsyncHttpResponseHandler handler) {
        this.handler=handler;
    }

    public void execute(String URL) {
        String url="http://200.118.215.195/teams/";
        AsyncHttpClient client=new AsyncHttpClient(8000);

        client.get(url, handler);

    }
}