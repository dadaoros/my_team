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
import com.mugen.myteam.Models.Team;
import com.mugen.myteam.Models.Update;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 21/07/15.
 */
public class DownloadUpdatesHandler extends AsyncHttpResponseHandler implements ApiHandler {
    Context ctx;
    public DownloadUpdatesHandler(Activity activity) {
        super();
        this.ctx = activity;
    }


    @Override
    public void onSuccess(int statusCode,org.apache.http.Header[] headers,byte[] bytes){
        JSONArray array=null;
        String response=null;
        List<Update> list = null;
        try {
            response=new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(id!=0) {
                    list.add(new Update(id,getQueries()));
                }

            }
        }
        try {
            //TODO: ejecuta varias inserciones
        } catch (SQLiteException e) {
            Log.e("Error", e.toString());

        }
        try {
            //TODO: actualiza la version de la actualización
            //SQLiteDatabase db = AlmacenSQLite.getAlmacenInstance(ctx).getWritableDatabase();
            //db.execSQL("INSERT INTO " + TeamsDataSource.VERSIONS_TABLENAME + " VALUES (0,1)");
        }catch (SQLiteException e){
            Log.e("Error 2", e.toString());
        }
        Intent result = new Intent();
        ((Activity)ctx).setResult(Activity.RESULT_OK, result);

    }

    private String[] getQueries() {
        return null;
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