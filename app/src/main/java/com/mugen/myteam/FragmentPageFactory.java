package com.mugen.myteam;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import com.mugen.myteam.FragmentTabs.BlankFragment;
import com.mugen.myteam.FragmentTabs.PositionListFragment;
import com.mugen.myteam.FragmentTabs.TeamsListFragment;

import java.util.List;

/**
 * Created by ORTEGON on 21/05/2015.
 */
public class FragmentPageFactory {
    private static FragmentPageFactory factory=null;
    public static synchronized FragmentPageFactory getFactory(){
        if(factory==null){
            factory=new FragmentPageFactory();
        }
        return factory;
    }
    private FragmentPageFactory(){ }

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
    public View getFragmentPage(Fragment activity, ViewGroup container, int position) {
        View view = null;

        switch (position) {
            case 0:



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

    public Fragment getFragmentPage(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment= TeamsListFragment.newInstance();
                break;
            case 1:
                fragment= PositionListFragment.newInstance();
                break;
            default:
                fragment= BlankFragment.newInstance();
                break;
        }
        return fragment;
    }

    public class ListHandler extends AsyncTask<Void,Void,List>{

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
            List teams=new DataBaseManager().getTeams(view.getContext());
            return teams;
        }
    };

}
