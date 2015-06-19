package com.mugen.myteam.ApiManager;

import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mugen.myteam.PageBuilder;
import com.mugen.myteam.Team;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ORTEGON on 17/06/2015.
 */
public class DownloadTeamsHandler extends AsyncHttpResponseHandler implements ApiHandler {
    Object lock;
    public DownloadTeamsHandler(Object lock) {
        super();
        this.lock=lock;
    }

    @Override
        public void onSuccess(int statusCode,org.apache.http.Header[] headers,byte[] bytes){

            List<Team> list = null;
            JSONArray array=null;
            String response=null;
            try {
                response=new String(bytes, "UTF-8");
                Log.d("logro",response);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            try {
                array=new JSONArray(response);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(array!=null){
                list =new ArrayList<Team>();
                for(int i=0;i<array.length();i++) {
                    JSONObject obj=null;
                    try {
                        obj= (JSONObject) array.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        list.add(new Team(obj.getString("name"), null, null));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }


        }
        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
        {
            Log.d("equivo", error.toString());
        }
        @Override
        public void onFinish(){
            synchronized (lock) {
                lock.notify();
            }
            Log.d("final","final" );
        }

}
