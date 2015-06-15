package com.mugen.myteam;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.mugen.myteam.DB.AlmacenSQLite;

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
        AlmacenSQLite.getAlmacenInstance(activity.getActivity());
        switch (position) {
            case 0:
                view = activity.getActivity().getLayoutInflater().inflate(R.layout.pos_table, container, false);
                container.addView(view);
                RestController restController= new RestController();


                ListView listaEquipos=(ListView)activity.getActivity().findViewById(R.id.lista_equipos);
                List teams=new DataBaseManager().getTeams(activity.getActivity());
                teams.add("Millonarios");
                TeamListAdapter listAdapter=new TeamListAdapter(teams,activity.getActivity());
                listaEquipos.setAdapter(listAdapter);

                break;
            case 1:
                view = activity.getActivity().getLayoutInflater().inflate(R.layout.calendar,container, false);

                //loadFragment(((MainActivity)f.getActivity()).getReposFragment());
                break;
            // Return the View
        }
        return view;
    }

}
