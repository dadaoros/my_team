package com.mugen.myteam;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mugen.myteam.ApiManager.ApiManager;
import com.mugen.myteam.ApiManager.ApiManagerShadow;
import com.mugen.myteam.ApiManager.DownloadDumpHandler;
import com.mugen.myteam.ApiManager.DownloadTeamsHandler;


public class LoaderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        final Lock lock =new Lock();
        iniciarCarga(lock);

        //Hilo que busca regresar a la actividad Principal
        new Thread(){
            public void run(){
                /*
                try{
                    sleep(3000);
                }catch(InterruptedException ex){

                }
                */
                //pausa la ejecución de esta secuencia de codigo hasta que se libere el seguro
                lock.LoadFromDB();
                //TODO: cambiar el estado aqui
                Log.d("Estado", "conexionFinalizada");

                Intent result = new Intent();
                setResult(Activity.RESULT_OK, result);
                finish();
            }

        }.start();

    }

    private void iniciarCarga(Lock lock) {
        Log.d("Estado", "Obtuvo seguro");
        ApiManager apiManager = new ApiManager();
        apiManager.setHandler(new DownloadDumpHandler(lock, this));
        apiManager.execute(ApiManager.URL_DUMP);

        //TODO: hilo toast
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
