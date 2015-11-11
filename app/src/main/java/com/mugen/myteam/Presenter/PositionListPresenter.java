package com.mugen.myteam.Presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.mugen.myteam.DB.TeamsDataSource;
import com.mugen.myteam.Model.DataBaseManager;
import com.mugen.myteam.View.FragmentTabs.PositionListAdapter;
import com.mugen.myteam.View.ViewOps;
import com.mugen.myteam.View.my_layouts.MyTableLayout;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by ORTEGON on 11/11/2015.
 */
public class PositionListPresenter extends MainPresenter implements PresenterOps.PositionListOps{
    WeakReference<ViewOps.PositionListOps> mView;
    public PositionListPresenter(ViewOps.PositionListOps mView) {
        super(mView);
        this.mView=new WeakReference<ViewOps.PositionListOps>(mView);
    }

    @Override
    public void loadList(int championship) {
        new LoadPositionListTask(this).execute(championship);
    }

    @Override
    public void onListUpdated(List rows) {
        mView.get().displayListUpdated(rows);
    }
    private class LoadPositionListTask extends AsyncTask<Integer,Void,List> {
        PositionListPresenter presenter;
        protected LoadPositionListTask(PositionListPresenter presenter){
            super();
            this.presenter=presenter;
        }
        @Override
        protected void onPreExecute(){
        }
        @Override
        protected void onPostExecute(List rows){
            presenter.onListUpdated(rows);
        }

        @Override
        protected List doInBackground(Integer... params) {
            int championship=params[0];
            //TODO: esta tarea la deberia realizar el presentador
            return new DataBaseManager().getTeamRows(mView.get().getActivityContext(),championship, TeamsDataSource.G_FIRSTPHASE);
        }
    }
}
