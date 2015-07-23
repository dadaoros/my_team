package com.mugen.myteam.FragmentTabs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.mugen.myteam.DB.AlmacenSQLite;
import com.mugen.myteam.DataBaseManager;
import com.mugen.myteam.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 13/07/15.
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
        new ListHandler(view).execute();

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
    private class ListHandler extends AsyncTask<Void,Void,List> {

        View view;
        protected ListHandler(View v){
            super();
            view=v;
        }
        @Override
        protected void onPreExecute(){
        }
        @Override
        protected void onPostExecute(List rows){
            PositionListAdapter listAdapter=new PositionListAdapter(rows,view.getContext());
            //((ListView)view).setAdapter(listAdapter);
        }

        @Override
        protected List doInBackground(Void... params) {
            List rows=new DataBaseManager().getTeamRows(view.getContext());
            return rows;
        }
    };
}
