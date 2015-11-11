package com.mugen.myteam.Presenter;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.mugen.myteam.DB.AlmacenSQLite;
import com.mugen.myteam.DB.TeamsDataSource;
import com.mugen.myteam.Model.DataBaseManager;
import com.mugen.myteam.Presenter.ApiManager.ApiManager;
import com.mugen.myteam.Presenter.ApiManager.DownloadUpdatesHandler;
import com.mugen.myteam.View.FragmentTabs.PositionListFragment;
import com.mugen.myteam.View.ViewOps;

import java.lang.ref.WeakReference;

/**
 * Created by ORTEGON on 11/11/2015.
 */
public class MainPresenter implements PresenterOps.MainOps {
    WeakReference<ViewOps.MainOps> mView;
    public MainPresenter(ViewOps.MainOps mView) {
        this.mView=new WeakReference<ViewOps.MainOps>(mView);
    }

    @Override
    public void onUpdateResult(int resultCode, String message) {
        mView.get().setRefreshingBar(false);
        mView.get().displayUpdateResult(resultCode,message);
    }

    @Override
    public void insertUpdateLog(int lastId){
        SQLiteDatabase db = AlmacenSQLite.getAlmacenInstance(mView.get().getActivityContext()).getWritableDatabase();
        db.execSQL("INSERT INTO " + TeamsDataSource.VERSIONS_TABLENAME + " ('" + TeamsDataSource.Versions.UPDATE + "') VALUES (" + String.valueOf(lastId) + ")");
    }
    @Override
    public void executeSQLStatementsBlock (String sql){
        DatabaseUtils.createDbFromSqlStatements(mView.get().getActivityContext(), AlmacenSQLite.DB_NAME, AlmacenSQLite.DB_VERSION, sql);
    }

    @Override
    public void searchForUpdates() {
        ApiManager apiManager = new ApiManager(new DownloadUpdatesHandler(this));
        String lastUpdate=new DataBaseManager().getLastUpdate(mView.get().getActivityContext());
        apiManager.execute(ApiManager.URL_UPDATES + lastUpdate);

    }
}
