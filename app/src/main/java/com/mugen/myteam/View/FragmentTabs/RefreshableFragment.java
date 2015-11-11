package com.mugen.myteam.View.FragmentTabs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Toast;

import com.mugen.myteam.Model.DataBaseManager;
import com.mugen.myteam.Presenter.ApiManager.ApiManager;
import com.mugen.myteam.Presenter.ApiManager.DownloadUpdatesHandler;
import com.mugen.myteam.Presenter.PresenterOps;
import com.mugen.myteam.R;
import com.mugen.myteam.View.ContextView;
import com.mugen.myteam.View.ViewOps;

/**
 * Created by ORTEGON on 11/11/2015.
 */
public class RefreshableFragment extends Fragment implements ViewOps.RefreshableOps{
    private PresenterOps.UpdatesOps presenter;
    SwipeRefreshLayout refreshLayout;
    public RefreshableFragment(){
    }
    public void setPresenter(PresenterOps.UpdatesOps presenter){
        this.presenter=presenter;

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);

    }

    protected void startRefreshing() {
        presenter.searchForUpdates();
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public Context getApplicationContext() {
        return getApplicationContext();
    }

    @Override
    public void displayUpdateResult(int resultCode, String message) {
        if(resultCode!= Activity.RESULT_OK)
            Toast.makeText(getActivityContext(),message,Toast.LENGTH_SHORT);
    }

    @Override
    public void setRefreshingBar(boolean display) {
        if(refreshLayout!=null)
            refreshLayout.setRefreshing(display);
    }
}
