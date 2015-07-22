package com.mugen.myteam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mugen.myteam.ApiManager.ApiManager;
import com.mugen.myteam.ApiManager.ApiManagerShadow;
import com.mugen.myteam.ApiManager.DownloadDumpHandler;
import com.mugen.myteam.ApiManager.DownloadTeamsHandler;
import com.mugen.myteam.ApiManager.DownloadUpdatesHandler;


public class LoaderActivity extends Activity {

    public static final int NOT_UPDATED = 4;
    public static final String VERSION="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        if (!new DataBaseManager().isInitialized(this)) {
            //El Handler del Hilo Retorna a la actividad principal cuando termina
            DownloadDumpHandler handler = new DownloadDumpHandler(this);
            ApiManager apiManager = new ApiManagerShadow();
            apiManager.setHandler(handler);
            apiManager.execute(ApiManager.URL_DUMP);

         } else {
            DownloadUpdatesHandler handler = new DownloadUpdatesHandler(this);
            ApiManager apiManager = new ApiManagerShadow();
            apiManager.setHandler(handler);
            apiManager.execute(ApiManager.URL_UPDATES+VERSION);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.no_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
