package com.mugen.myteam.FragmentTabs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mugen.myteam.DB.AlmacenSQLite;
import com.mugen.myteam.DataBaseManager;
import com.mugen.myteam.R;
import com.mugen.myteam.my_layouts.MyTableLayout;

import java.util.List;

/**
 * Created by dadaoros on 13/07/15.
 */
public class PositionListFragment extends Fragment {
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
        AlmacenSQLite.getAlmacenInstance(view.getContext());
        loadSpinner(view);
        final SwipeRefreshLayout refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initiateRefresh(refreshLayout);
            }
        });
        MyTableLayout table = (MyTableLayout)view.findViewById(R.id.pos_table);
        new TableHandler(table).execute();

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
        Spinner spinner = (Spinner) view.findViewById(R.id.championship_spinner1);
        String[] list={"LIGA AGUILA 2015-2"};
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(view.getContext(),android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
    private class TableHandler extends AsyncTask<Void,Void,List> {

        MyTableLayout tableLayout;
        protected TableHandler(MyTableLayout v){
            super();
            tableLayout=v;
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
        protected List doInBackground(Void... params) {
            return new DataBaseManager().getTeamRows(tableLayout.getContext());
        }
    }
}
