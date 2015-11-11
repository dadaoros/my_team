package com.mugen.myteam.Presenter;

import com.mugen.myteam.View.ViewOps;

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
}
