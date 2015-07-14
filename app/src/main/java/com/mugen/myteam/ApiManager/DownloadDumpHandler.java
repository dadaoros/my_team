package com.mugen.myteam.ApiManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mugen.myteam.DB.AlmacenSQLite;
import com.mugen.myteam.DB.TeamsDataSource;
import com.mugen.myteam.DataBaseManager;
import com.mugen.myteam.Lock;
import com.mugen.myteam.Models.Team;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 6/07/15.
 */
public class DownloadDumpHandler extends AsyncHttpResponseHandler implements ApiHandler {
    Lock lock;

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    Context ctx;
    public DownloadDumpHandler(Activity activity) {
        super();
        this.lock = lock;
        this.ctx = activity;
    }


    @Override
    public void onSuccess(int statusCode,org.apache.http.Header[] headers,byte[] bytes){

        String response=null;
        try {
            response=new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        try {
            DatabaseUtils.createDbFromSqlStatements(ctx, AlmacenSQLite.DB_NAME, AlmacenSQLite.DB_VERSION, response);
        } catch (SQLiteException e) {
            Log.e("Error", e.toString());

        }
        try {
            SQLiteDatabase db = AlmacenSQLite.getAlmacenInstance(ctx).getWritableDatabase();
            db.execSQL("INSERT INTO " + TeamsDataSource.VERSIONS_TABLENAME + " VALUES (0,1)");
        }catch (SQLiteException e){
            Log.e("Error 2", e.toString());
        }
        Intent result = new Intent();
        ((Activity)ctx).setResult(Activity.RESULT_OK, result);

    }
    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
    {
        Log.d("RESTError", statusCode + " " + error.toString());
        Toast.makeText(ctx, "No se pudo establecer conexión con el Servidor", Toast.LENGTH_LONG).show();
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... params) {


                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {

                Intent intentR = new Intent();
                ((Activity)ctx).setResult(Activity.RESULT_CANCELED, intentR);
            }



        }.execute();


    }
    @Override
    public void onFinish(){

        ((Activity)ctx).finish();
    }

}

