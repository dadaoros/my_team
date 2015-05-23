package com.mugen.myteam;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ORTEGON on 21/05/2015.
 */
public class PageBuilder {
    private static PageBuilder factory=null;
    public static PageBuilder getFactory(){
        if(factory==null){
            factory=new PageBuilder();
        }
        return factory;
    }
    private PageBuilder(){

    }
    public View getPage(Fragment activity,ViewGroup container,int position){
        View view=null;
        switch (position) {
            case 0:
                view = activity.getActivity().getLayoutInflater().inflate(R.layout.pos_table, container, false);
                ListView listaEquipos=(ListView)activity.getActivity().findViewById(R.id.lista_equipos);
                new descargaEquipos().execute();
              //  TeamListAdapter listAdapter=new TeamListAdapter(activity,);

                break;
            case 1:
                view = activity.getActivity().getLayoutInflater().inflate(R.layout.calendar,container, false);

                //loadFragment(((MainActivity)f.getActivity()).getReposFragment());
                break;
            // Return the View
        }
        return view;
    }
    public class descargaEquipos extends AsyncTask<Void,Void,List<Team>>{



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
