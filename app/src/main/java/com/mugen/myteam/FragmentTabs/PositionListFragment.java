package com.mugen.myteam.FragmentTabs;

import android.app.ActionBar;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
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
 * Created by dadaoros on 13/07/15.
 */
public class PositionListFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    MyTableLayout table;
    Spinner spinner;
    public PositionListFragment(){

    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pos_table, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        ScrollView scrollView = (ScrollView) view.findViewById(R.id.positionScrollView);
        final SwipeRefreshLayout refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        table = (MyTableLayout)view.findViewById(R.id.pos_table);

        AlmacenSQLite.getAlmacenInstance(view.getContext());
        loadSpinner(view);
        spinner.setOnItemSelectedListener(this);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initiateRefresh(refreshLayout);
            }
        });
        int championshipSelected=0;
        switch (spinner.getSelectedItemPosition()){
            case 0:
                championshipSelected= TeamsDataSource.LIGAAGUILA1;
                break;
            case 1:
                championshipSelected=TeamsDataSource.LIGAAGUILA2;
                break;
            case 2:
                championshipSelected=TeamsDataSource.RECLASIFICACION;
                break;
            default:
                championshipSelected=TeamsDataSource.RECLASIFICACION;
                break;

        }
        new TableHandler(table).execute(championshipSelected);

    }

    public static PositionListFragment newInstance() {
        PositionListFragment f = new PositionListFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", 1);
        f.setArguments(args);

        return f;
    }
    private void loadSpinner(View view){
        spinner = (Spinner) view.findViewById(R.id.championship_spinner1);
        String[] list={"LIGA AGUILA 2015-1","LIGA AGUILA 2015-2","RECLASIFICACIÓN 2015"};
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(view.getContext(),android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        //deja seleccionado por defecto la posición 0 del spinner

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
            case 0:
                championshipSelected= TeamsDataSource.LIGAAGUILA1;
                break;
            case 1:
                championshipSelected=TeamsDataSource.LIGAAGUILA2;
                break;
            case 2:
                championshipSelected=TeamsDataSource.RECLASIFICACION;
                break;
            default:
                championshipSelected=TeamsDataSource.RECLASIFICACION;
                break;

        }
        new TableHandler(table).execute(championshipSelected);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class TableHandler extends AsyncTask<Integer,Void,List> {
        Context ctx;
        MyTableLayout tableLayout;
        protected TableHandler(MyTableLayout v){
            super();
            tableLayout=v;
            ctx=v.getContext();
        }
        @Override
        protected void onPreExecute(){
        }
        @Override
        protected void onPostExecute(List rows){
            PositionListAdapter listAdapter=new PositionListAdapter(rows,tableLayout.getContext());
            tableLayout.setAdapter(listAdapter);
        }

        @Override
        protected List doInBackground(Integer... params) {
            int championship=params[0];
            return new DataBaseManager().getTeamRows(ctx,championship,TeamsDataSource.G_FIRSTPHASE);
        }
    }
}
