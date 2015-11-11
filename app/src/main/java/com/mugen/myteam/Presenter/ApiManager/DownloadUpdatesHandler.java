package com.mugen.myteam.Presenter.ApiManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mugen.myteam.DB.AlmacenSQLite;
import com.mugen.myteam.DB.TeamsDataSource;
import com.mugen.myteam.Presenter.PresenterOps;
import com.mugen.myteam.View.LoaderActivity;
import com.mugen.myteam.Model.Update;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dadaoros on 21/07/15.
 */
public class DownloadUpdatesHandler extends AsyncHttpResponseHandler {
    private final String TAG= this.getClass().getName();
    PresenterOps.UpdatesOps presenter;
    public DownloadUpdatesHandler(PresenterOps.UpdatesOps presenter) {
        super();
        this.presenter = presenter;
    }


    @Override
    public void onSuccess(int statusCode,org.apache.http.Header[] headers,byte[] bytes){
        String response=null;
        try {
            response=new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        new LoadToDatabaseTask().execute(response);

    }

    private String getQueries() {
        return null;
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
    {
        Log.d("RESTError", statusCode + " " + error.toString());
        presenter.onUpdateResult(LoaderActivity.NOT_UPDATED,"No se pudo establecer conexión con el Servidor");
    }

    class LoadToDatabaseTask extends AsyncTask<String,Void,Object>{


        @Override
        protected Object doInBackground(String... params) {
            String response=params[0];
            JSONArray array=null;
            int lastId=0;
            List<Update> list = null;

            try {
                array=new JSONArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(array!=null){
                list =new ArrayList<Update>();
                for(int i=0;i<array.length();i++) {
                    JSONObject obj=null;
                    int id=0;
                    String sql="";
                    try {
                        obj= (JSONObject) array.get(i);
                        id=obj.getInt("version");
                        sql=obj.getString("sql");
                        if(id>lastId)lastId=id;
                    } catch (JSONException e) {
                        Log.d("JSONEXCEp",e.getMessage());
                    }
                    if(id!=0) {
                        try {
                            presenter.executeSQLStatementsBlock(sql);
                        }catch (SQLiteException e){
                            Log.e("SQL Exception",e.getMessage());
                        }
                    }
                }
            }
            try {
                presenter.insertUpdateLog(lastId);
            }catch (SQLiteException e){
                Log.e("Error 2", e.toString());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object obj){
            //TODO: que pasa en caso de error
                presenter.onUpdateResult(Activity.RESULT_OK,"Datos Actualizados");
        }
    }
}
