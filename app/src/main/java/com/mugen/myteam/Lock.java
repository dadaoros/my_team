package com.mugen.myteam;

import android.app.ProgressDialog;

/**
 * Created by ORTEGON on 18/06/2015.
 */
public class Lock {
    private boolean ready;
    private ProgressDialog progressDialog;
    Lock(){
        progressDialog=null;
        ready=false;
    }
    Lock(ProgressDialog progressDialog){
        this.progressDialog=progressDialog;
        progressDialog.setTitle("Obteniendo datos del Servidor");
        progressDialog.show();
        ready=false;
    }
    public synchronized void LoadedFromApi(){
        ready=true;
        if(progressDialog!=null)
            progressDialog.dismiss();
        notify();
    }
    public synchronized void LoadFromDB(){
        while(!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
