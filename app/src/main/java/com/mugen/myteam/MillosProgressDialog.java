package com.mugen.myteam;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by ORTEGON on 22/06/2015.
 */
public class MillosProgressDialog extends ProgressDialog {
    public MillosProgressDialog(Context context) {
        super(context);
        setIcon(R.drawable.millos);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.custom_progressdialog);


    }
}
