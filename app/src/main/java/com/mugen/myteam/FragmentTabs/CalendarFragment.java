package com.mugen.myteam.FragmentTabs;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.mugen.myteam.ApiManager.ApiManager;
import com.mugen.myteam.ApiManager.DownloadUpdatesHandler;
import com.mugen.myteam.DB.AlmacenSQLite;
import com.mugen.myteam.DB.TeamsDataSource;
import com.mugen.myteam.DataBaseManager;
import com.mugen.myteam.R;
import com.mugen.myteam.my_layouts.MyTableLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    ListView lista;

    public static CalendarFragment newInstance() {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        spinner = (Spinner) view.findViewById(R.id.championship_spinner2);
        lista = (ListView)view.findViewById(R.id.calendar_listview);
        final SwipeRefreshLayout refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);

        AlmacenSQLite.getAlmacenInstance(view.getContext());

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initiateRefresh(refreshLayout);
            }
        });
        loadSpinner(view);
        int championshipSelected=0;
        switch (spinner.getSelectedItemPosition()){
            case 1:
                championshipSelected=TeamsDataSource.LIGAAGUILA1;
                break;
            case 2:
                championshipSelected=TeamsDataSource.LIGAAGUILA2;
                break;
            default:
                championshipSelected=0;
                break;

        }
        new CalendarHandler(lista).execute(championshipSelected);

    }
    private void loadSpinner(View view){
        String[] list={"TODOS LOS TORNEOS","LIGA AGUILA 2015-1","LIGA AGUILA 2015-2"};
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(view.getContext(),android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(1);
        //deja seleccionado por defecto la posicion 1 del spinner
    }
    private void initiateRefresh(final SwipeRefreshLayout refreshLayout) {
        DownloadUpdatesHandler handler = new DownloadUpdatesHandler(this.getActivity(), this.getClass().getName());
        handler.setRefreshLayout(refreshLayout);
        ApiManager apiManager = new ApiManager();
        apiManager.setHandler(handler);
        String lastUpdate=new DataBaseManager().getLastUpdate(this.getActivity());
        apiManager.execute(ApiManager.URL_UPDATES + lastUpdate);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int championshipSelected=0;
        switch (position){
            case 1:
                championshipSelected=TeamsDataSource.LIGAAGUILA1;
                break;
            case 2:
                championshipSelected=TeamsDataSource.LIGAAGUILA2;
                break;
            default:
                championshipSelected=0;
                break;

        }
        new CalendarHandler(lista).execute(championshipSelected);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class CalendarHandler extends AsyncTask<Integer,Void,List> {
        private Context ctx;
        ListView listView;
        protected CalendarHandler(ListView v){
            super();
            listView=v;
        }
        @Override
        protected void onPreExecute(){
            this.ctx=listView.getContext();
        }
        @Override
        protected void onPostExecute(List rows){
            CalendarListAdapter listAdapter=new CalendarListAdapter(rows,listView.getContext());
            listView.setAdapter(listAdapter);
        }

        @Override
        protected List doInBackground(Integer... params) {
            int campeonato=params[0];
            return new DataBaseManager().getTeamCalendar(ctx,campeonato);
        }

    }


}
