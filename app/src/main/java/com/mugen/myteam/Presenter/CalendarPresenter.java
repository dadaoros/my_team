package com.mugen.myteam.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.mugen.myteam.Model.DataBaseManager;
import com.mugen.myteam.View.FragmentTabs.CalendarFragment;
import com.mugen.myteam.View.FragmentTabs.CalendarListAdapter;
import com.mugen.myteam.View.ViewOps;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by ORTEGON on 11/11/2015.
 */
public class CalendarPresenter extends MainPresenter implements PresenterOps.CalendarOps{
    WeakReference<ViewOps.CalendarOps> mView;
    public CalendarPresenter(ViewOps.CalendarOps mView) {
        super(mView);
        this.mView=new WeakReference<ViewOps.CalendarOps>(mView);
    }

    @Override
    public void loadCalendar(int championship) {
        new CalendarTask(this).execute(championship);
    }

    @Override
    public void onListUpdated(List rows) {
        mView.get().displayListUpdated(rows);
    }

    private class CalendarTask extends AsyncTask<Integer,Void,List> {
        PresenterOps.CalendarOps presenter;
        protected CalendarTask(PresenterOps.CalendarOps presenter){
            super();
            this.presenter=presenter;
        }
        @Override
        protected void onPostExecute(List rows){
            presenter.onListUpdated(rows);
        }

        @Override
        protected List doInBackground(Integer... params) {
            int campeonato=params[0];
            return new DataBaseManager().getTeamCalendar(mView.get().getActivityContext(),campeonato);
        }

    }
}
