package com.mugen.myteam.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.mugen.myteam.Presenter.LoaderPresenter;
import com.mugen.myteam.Presenter.PresenterOps;
import com.mugen.myteam.R;


public class LoaderActivity extends Activity implements ViewOps.LoaderOps{
    SwipeRefreshLayout refreshLayout;
    public static final int NOT_UPDATED = 4;
    public static final String RESULT_MESSAGE="Result message";
    PresenterOps.LoaderOps presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        presenter=new LoaderPresenter(this);
        refreshLayout= (SwipeRefreshLayout) findViewById(R.id.loader_refresh_layout);

        setContentView(R.layout.activity_loader);

        presenter.searchForUpdates();
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


    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void setRefreshingBar(boolean display) {
        if(refreshLayout!=null)
            refreshLayout.setRefreshing(display);
    }

    @Override
    public void navigateBackToMainActivity(int resultCode,String message) {
        Intent intent = new Intent();
        intent.putExtra(RESULT_MESSAGE,message);
        setResult(resultCode, intent);
        finish();
    }
}
