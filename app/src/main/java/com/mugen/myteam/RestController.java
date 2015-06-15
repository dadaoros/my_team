package com.mugen.myteam;

import android.os.AsyncTask;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ORTEGON on 13/06/2015.
 */
public class RestController {
    public class downloadTeams extends AsyncTask<Void,Void,List<Team>> {

        @Override
        protected List<Team> doInBackground(Void... params) {
            String url="http://192.168.0.6/teams/";
            AsyncHttpClient client=new AsyncHttpClient(8000);
            //TODO solucionar llenado de lista


            RequestHandle handle = client.get(url,new AsyncHttpResponseHandler(){
                @Override
                public void onSuccess(String response){
                    List<Team> list = null;
                    JSONArray array=null;
                    Log.d("logro", response);
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
                                list.add(new Team(obj.getString("name"), obj.getString("logo"), obj.getString("stadium")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                    //_responseHandler.receiveSuccess(new RestResponse(chisimbaResponse.data,true));
                }
                @Override
                public void onFailure(int statusCode, Throwable error, String content){
                    Log.d("equivo", error.toString());
                }

            });
            return null;

        }

        @Override
        protected void onPostExecute(List<Team> teams ){

        }
    }
}
