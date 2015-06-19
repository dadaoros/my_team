package com.mugen.myteam.ApiManager;

import android.os.AsyncTask;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.mugen.myteam.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        String url="http://192.168.0.6/teams/";
        AsyncHttpClient client=new AsyncHttpClient(8000);

        client.get(url, handler);

    }
}