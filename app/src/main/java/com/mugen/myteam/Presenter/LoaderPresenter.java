package com.mugen.myteam.Presenter;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.mugen.myteam.DB.AlmacenSQLite;
import com.mugen.myteam.DB.TeamsDataSource;
import com.mugen.myteam.Presenter.ApiManager.ApiManager;
import com.mugen.myteam.Presenter.ApiManager.ApiManagerShadow;
import com.mugen.myteam.Presenter.ApiManager.DownloadDumpHandler;
import com.mugen.myteam.Presenter.ApiManager.DownloadUpdatesHandler;
import com.mugen.myteam.Model.DataBaseManager;
import com.mugen.myteam.View.ViewOps;

import java.lang.ref.WeakReference;

/**
 * Created by ORTEGON on 10/11/2015.
 */
public class LoaderPresenter implements PresenterOps.LoaderOps {
    public WeakReference<ViewOps.LoaderOps> mView;
    DataBaseManager dataBaseManager;
    public LoaderPresenter(ViewOps.LoaderOps view) {
        this.mView=new WeakReference<ViewOps.LoaderOps>(view);
        dataBaseManager=new DataBaseManager();
    }

    @Override
    public void searchForUpdates() {
        if (!dataBaseManager.isInitialized(mView.get().getActivityContext())) {
            ApiManager apiManager = new ApiManager(new DownloadDumpHandler(this));
            apiManager.execute(ApiManager.URL_DUMP);
        } else {
            mView.get().setRefreshingBar(true);;
            ApiManager apiManager = new ApiManager(new DownloadUpdatesHandler(this));
            String lastUpdate=new DataBaseManager().getLastUpdate(mView.get().getActivityContext());
            apiManager.execute(ApiManager.URL_UPDATES+lastUpdate);
        }
    }

    @Override
    public void onUpdateResult(int resultCode,String message) {
        mView.get().navigateBackToMainActivity(resultCode,message);
        mView.get().setRefreshingBar(false);
    }
    @Override
    public void executeSQLStatementsBlock (String sql){
        DatabaseUtils.createDbFromSqlStatements(mView.get().getActivityContext(), AlmacenSQLite.DB_NAME, AlmacenSQLite.DB_VERSION, sql);

    }
    @Override
    public void insertUpdateLog(int lastId){
        SQLiteDatabase db = AlmacenSQLite.getAlmacenInstance(mView.get().getActivityContext()).getWritableDatabase();
        db.execSQL("INSERT INTO " + TeamsDataSource.VERSIONS_TABLENAME +" ('"+ TeamsDataSource.Versions.UPDATE+ "') VALUES ("+String.valueOf(lastId)+")");

    }
}
