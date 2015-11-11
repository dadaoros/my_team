package com.mugen.myteam.View;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;


import com.mugen.myteam.View.FragmentTabs.BlankFragment;
import com.mugen.myteam.View.FragmentTabs.CalendarFragment;
import com.mugen.myteam.View.FragmentTabs.MyTeamFragment;
import com.mugen.myteam.View.FragmentTabs.PositionListFragment;

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
            case 2:
                fragment= CalendarFragment.newInstance();
                break;
            case 3:
                fragment= MyTeamFragment.newInstance();
                break;
            default:
                fragment= BlankFragment.newInstance();
                break;
        }
        return fragment;
    }


}
