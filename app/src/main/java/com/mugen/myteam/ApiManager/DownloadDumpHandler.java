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

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by dadaoros on 6/07/15.
 */
public class DownloadDumpHandler extends AsyncHttpResponseHandler implements ApiHandler {

    Context ctx;
    public DownloadDumpHandler(Activity activity) {
        super();
        this.ctx = activity;
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
                DatabaseUtils.createDbFromSqlStatements(ctx, AlmacenSQLite.DB_NAME, AlmacenSQLite.DB_VERSION, data);
            } catch (SQLiteException e) {
                Log.d("Error", e.toString());
            }
            try {
                SQLiteDatabase db = AlmacenSQLite.getAlmacenInstance(ctx).getWritableDatabase();
                db.execSQL("INSERT INTO " + TeamsDataSource.VERSIONS_TABLENAME + " VALUES (0,"+String.valueOf(version)+")");
            } catch (SQLiteException e) {
                Log.e("Error 2", e.toString());
            }
            Intent result = new Intent();
            ((Activity) ctx).setResult(Activity.RESULT_OK, result);
        }else{
            Intent result = new Intent();
            ((Activity) ctx).setResult(Activity.RESULT_CANCELED, result);
        }
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

