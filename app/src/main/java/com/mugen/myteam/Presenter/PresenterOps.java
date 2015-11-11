package com.mugen.myteam.Presenter;

import com.mugen.myteam.View.ViewOps;

import java.util.List;

/**
 * Created by ORTEGON on 10/11/2015.
 */
public interface PresenterOps {
    public interface UpdatesOps{
        public void onUpdateResult(int resultCode,String message);
        public void executeSQLStatementsBlock(String sqlStatements);
        public void insertUpdateLog(int updateId);
        public void searchForUpdates();
    }
    public interface LoaderOps extends UpdatesOps{
    }
    public interface MainOps extends UpdatesOps{
    }
    public interface CalendarOps extends UpdatesOps{
        public void loadCalendar(int championship);
        void onListUpdated(List rows);
    }
    public interface PositionListOps extends UpdatesOps{
        public void loadList(int championship);
        void onListUpdated(List rows);
    }

}
