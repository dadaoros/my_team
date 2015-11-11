package com.mugen.myteam.Presenter.ApiManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mugen.myteam.DB.AlmacenSQLite;
import com.mugen.myteam.DB.TeamsDataSource;
import com.mugen.myteam.Presenter.LoaderPresenter;
import com.mugen.myteam.Presenter.PresenterOps;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by dadaoros on 6/07/15.
 */
public class DownloadDumpHandler extends AsyncHttpResponseHandler {
    private final String TAG=this.getClass().getName();
    LoaderPresenter presenter;
    public DownloadDumpHandler(LoaderPresenter presenter) {
        super();
        this.presenter = presenter;
    }

    @Override
    public void onSuccess(int statusCode,org.apache.http.Header[] headers,byte[] bytes){
        JSONArray array = null;
        String response=null;
        try {
            response=new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            array=new JSONArray(response);
        } catch (JSONException e) {
            Log.d("JSON Error",e.getMessage());
        }
        if(array!=null) {

            JSONObject obj=null;
            int version=0;
            String data="";
            try {
                obj= (JSONObject) array.get(0);
                version=obj.getInt("version");
                data=obj.getString("data");
            } catch (JSONException e) {
                Log.d("JSONEXCEp",e.getMessage());
            }
            try {
                DatabaseUtils.createDbFromSqlStatements(
                        presenter.mView.get().getActivityContext(),
                        AlmacenSQLite.DB_NAME,
                        AlmacenSQLite.DB_VERSION,
                        data);
            } catch (Exception e) {
                Log.d("Error", e.toString());
            }
            try {
                SQLiteDatabase db = AlmacenSQLite.getAlmacenInstance(presenter.mView.get().getActivityContext()).getWritableDatabase();
                db.execSQL("INSERT INTO " + TeamsDataSource.VERSIONS_TABLENAME + " VALUES (0,"+String.valueOf(version)+")");
            } catch (SQLiteException e) {
                Log.e("Error 2", e.toString());
            }
            presenter.onUpdateResult(Activity.RESULT_OK,null);
        }else{
            presenter.onUpdateResult(Activity.RESULT_CANCELED,"Error al obtener datos del servidor");
        }
    }
    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
    {
        Log.d(TAG, statusCode + " " + error.toString());
        presenter.onUpdateResult(Activity.RESULT_CANCELED,"No se pudo establecer conexión con el Servidor");

    }

}

