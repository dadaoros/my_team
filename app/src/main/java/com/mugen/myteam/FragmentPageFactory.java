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

    public Fragment getFragmentPage(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment= BlankFragment.newInstance();
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


}
