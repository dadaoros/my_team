package com.mugen.myteam;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mugen.myteam.ApiManager.ApiManager;
import com.mugen.myteam.ApiManager.ApiManagerShadow;
import com.mugen.myteam.ApiManager.DownloadTeamsHandler;
import com.mugen.myteam.DB.AlmacenSQLite;

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

    public View getPage(Fragment activity, ViewGroup container, int position) {
        View view = null;
        AlmacenSQLite.getAlmacenInstance(activity.getActivity());
        switch (position) {
            case 0:
                view = activity.getActivity().getLayoutInflater().inflate(R.layout.pos_table, container, false);
                container.addView(view);

                ListView listaEquipos = (ListView) activity.getActivity().findViewById(R.id.lista_equipos);

                Object lock=new Object();
                //Hilo que Intenta Obtener informacion de la API
                ApiManager apiManager = new ApiManagerShadow();
                apiManager.setHandler(new DownloadTeamsHandler(lock));
                //Hilo que intenta obtener y actualizar lista desde la base de datos
                ListHandler h = new ListHandler(listaEquipos);
                h.setLock(lock);

                h.execute();
                apiManager.execute(ApiManager.URLTEAMS);


                break;
            case 1:
                view = activity.getActivity().getLayoutInflater().inflate(R.layout.calendar, container, false);
                container.addView(view);

                //new ApiManager().execute();
                TextView tPrueba = (TextView) activity.getActivity().findViewById(R.id.textView);
                tPrueba.setText("TPrueba");

                break;
            // Return the View
        }
        return view;
    }
    public class ListHandler extends AsyncTask<Void,Void,List>{

        View view;
        private Object lock;

        protected ListHandler(View v){
            super();
            view=v;
        }
        public void setLock(Object lock) { this.lock = lock;}
        @Override
        protected void onPreExecute(){
        }
        @Override
        protected void onPostExecute(List teams){
            teams.add("Millonarios");
            TeamListAdapter listAdapter=new TeamListAdapter(teams,view.getContext());
            ((ListView)view).setAdapter(listAdapter);
        }

        @Override
        protected List doInBackground(Void... params) {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.i("ConsumerState","free");
            List teams=new DataBaseManager().getTeams(view.getContext());
            return teams;
        }
    };

}
