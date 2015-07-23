package com.mugen.myteam.FragmentTabs;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mugen.myteam.ApiManager.ApiManager;
import com.mugen.myteam.ApiManager.DownloadTeamsHandler;
import com.mugen.myteam.DB.AlmacenSQLite;
import com.mugen.myteam.DataBaseManager;
import com.mugen.myteam.Lock;
import com.mugen.myteam.MillosProgressDialog;
import com.mugen.myteam.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsListFragment extends Fragment {


    public TeamsListFragment() {
        // Required empty public constructor
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            return inflater.inflate(R.layout.teams_list, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        AlmacenSQLite.getAlmacenInstance(view.getContext());
        ListView listaEquipos = (ListView) view.findViewById(R.id.lista_equipos);
        final ProgressDialog progressDialog = new MillosProgressDialog(view.getContext());


        final SwipeRefreshLayout refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initiateRefresh(refreshLayout);
            }
        });

        Lock lock =new Lock(progressDialog);
        Log.d("Estado", "Obtuvo seguro 2");

        //Hilo que Intenta Obtener informacion de la API
        ApiManager apiManager = new ApiManager();
        apiManager.setHandler(new DownloadTeamsHandler(lock, view.getContext()));
        //Hilo que intenta obtener y actualizar lista desde la base de datos
        ListHandler h = new ListHandler(listaEquipos);
        h.setLock(lock);

        h.execute();
        apiManager.execute(ApiManager.URL_TEAMS);
    }

    public static TeamsListFragment newInstance() {
        TeamsListFragment f = new TeamsListFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", 1);
        f.setArguments(args);

        return f;
    }

    public class ListHandler extends AsyncTask<Void,Void,List> {

        View view;
        private Lock lock;

        protected ListHandler(View v){
            super();
            view=v;
        }
        public void setLock(Lock lock) { this.lock = lock;}
        @Override
        protected void onPreExecute(){
        }
        @Override
        protected void onPostExecute(List teams){
            TeamListAdapter listAdapter=new TeamListAdapter(teams,view.getContext());
            ((ListView)view).setAdapter(listAdapter);
        }

        @Override
        protected List doInBackground(Void... params) {
            lock.LoadFromDB();
            Log.i("DBConsumerState", "free");
            return new DataBaseManager().getTeams(view.getContext());
        }
    }
    private void initiateRefresh(final SwipeRefreshLayout refreshLayout) {

        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... params) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                refreshLayout.setRefreshing(false);
            }



        }.execute();



    }

}
