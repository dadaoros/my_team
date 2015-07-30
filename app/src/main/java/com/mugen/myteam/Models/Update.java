package com.mugen.myteam.Models;

/**
 * Created by dadaoros on 21/07/15.
 */
public class Update {
    private int id;
    private String queries;
    public Update(int id,String queries){
        this.id=id;
        this.queries=queries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQueries() {
        return queries;
    }

}
