package com.mugen.myteam;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {
    public static final int LOADCODE = 0;
    public static final int NO_INTERNET=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null) {
            Intent i = new Intent(this, LoaderActivity.class);
            startActivityForResult(i, 0);
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
    private void initComponents(Bundle savedInstanceState) {

        SlidingTabsBasicFragment fragment;
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            fragment = new SlidingTabsBasicFragment();
            transaction.replace(R.id.content_fragment, fragment);
            transaction.commit();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==LOADCODE){
            if(resultCode==RESULT_CANCELED)
                this.finish();
            else {
                if(resultCode==LoaderActivity.NOT_UPDATED)
                    Toast.makeText(this,"No pudo ser Actualizado",Toast.LENGTH_SHORT);
                new Handler().post(new Runnable() {
                    public void run() {
                        initComponents(null);
                    }
                });
            }

        }

    }
}
