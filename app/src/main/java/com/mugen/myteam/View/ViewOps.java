package com.mugen.myteam.View;

import android.content.Context;

import java.util.List;

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
    public interface CalendarOps extends MainOps{
        public void displayListUpdated(List rows);
    }
    public interface PositionListOps extends MainOps{
        public void displayListUpdated(List rows);
    }
    public interface MainOps extends RefreshableOps{
    }
}
