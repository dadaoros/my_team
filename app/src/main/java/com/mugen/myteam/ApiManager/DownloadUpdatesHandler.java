package com.mugen.myteam.ApiManager;

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
import com.mugen.myteam.LoaderActivity;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

/**
 * Created by root on 21/07/15.
 */
public class DownloadUpdatesHandler extends AsyncHttpResponseHandler implements ApiHandler {
    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    Context ctx;
    public DownloadUpdatesHandler(Activity activity) {
        super();
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
            //TODO: ejecuta varias inserciones
        } catch (SQLiteException e) {
            Log.e("Error", e.toString());

        }
        try {
            //TODO: actualiza la version de la actualización
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
        Intent intentR = new Intent();
        ((Activity)ctx).setResult(LoaderActivity.NOT_UPDATED, intentR);


    }
    @Override
    public void onFinish(){

        ((Activity)ctx).finish();
    }
}
