package com.mugen.myteam;

/**
 * Created by ORTEGON on 18/06/2015.
 */
public class Lock {
    private boolean ready;
    Lock(){
        ready=false;
    }
    public synchronized void LoadFromApi(){
        ready=true;
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
