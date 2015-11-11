package com.mugen.myteam.View;

import android.content.Context;

/**
 * Created by ORTEGON on 10/11/2015.
 */
public interface ViewOps {

    public interface RefreshableOps extends ContextView{
        public void displayUpdateResult(int resultCode, String message);
        public void setRefreshingBar(boolean display);
    }
    public interface LoaderOps extends ContextView{
        public void setRefreshingBar(boolean display);
        public void navigateBackToMainActivity(int resultCode,String message);
    }
    public interface MainOps extends RefreshableOps{
    }
}
