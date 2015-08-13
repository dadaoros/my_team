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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.mugen.myteam.DB.AlmacenSQLite;
import com.mugen.myteam.DataBaseManager;
import com.mugen.myteam.R;
import com.mugen.myteam.my_layouts.MyTableLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {

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
        AlmacenSQLite.getAlmacenInstance(view.getContext());
        final ListView lista = (ListView)view.findViewById(R.id.calendar_listview);
        lista.setOnScrollListener(new AbsListView.OnScrollListener() {
            int mLastFirstVisibleItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {   }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (view.getId() == lista.getId()) {
                    final int currentFirstVisibleItem = lista.getFirstVisiblePosition();

                    if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                        // getSherlockActivity().getSupportActionBar().hide();
                        ((Activity)view.getContext()).getActionBar().hide();
                    } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                        // getSherlockActivity().getSupportActionBar().show();
                        ((Activity)view.getContext()).getActionBar().show();
                    }

                    mLastFirstVisibleItem = currentFirstVisibleItem;
                }
            }
        });
        loadSpinner(view);
        new CalendarHandler(lista).execute();

    }
    private void loadSpinner(View view){
        Spinner spinner = (Spinner) view.findViewById(R.id.championship_spinner2);
        String[] list={"TODOS LOS TORNEOS","LIGA AGUILA 2015-2"};
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(view.getContext(),android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    private class CalendarHandler extends AsyncTask<Void,Void,List> {
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
        protected List doInBackground(Void... params) {
            return new DataBaseManager().getTeamCalendar(ctx);
        }
    }


}
