package com.mugen.myteam.ApiManager;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mugen.myteam.DataBaseManager;
import com.mugen.myteam.Lock;
import com.mugen.myteam.Models.Team;

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
    Lock lock;


    Context ctx;
    public DownloadTeamsHandler(Lock lock, Context activity) {
        super();
        this.lock = lock;
        this.ctx = activity;
    }


    @Override
        public void onSuccess(int statusCode,org.apache.http.Header[] headers,byte[] bytes){

            List<Team> list = null;
            JSONArray array=null;
            String response=null;
            try {
                response=new String(bytes, "UTF-8");
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
                        list.add(new Team(obj.getInt("id"),obj.getString("name"), null, null));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        new DataBaseManager().putTeams(list);
        Log.i("REST", "success");

        }
        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
        {
            Toast.makeText(ctx,"No se pudo establecer conexión con el Servidor",Toast.LENGTH_LONG).show();
            Log.d("RESTError", statusCode+" "+error.toString());
        }
        @Override
        public void onFinish(){
            lock.LoadedFromApi();
        }

}
