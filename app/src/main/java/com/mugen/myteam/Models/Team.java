package com.mugen.myteam.Models;

/**
 * Created by ORTEGON on 22/05/2015.
 */
public class Team {
    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public String getCity() {
        return city;
    }

    public int getID() {
        return ID;
    }

    private int ID;
    private String name;
    private String logo;
    private String city;
    public Team(int ID, String name,String logo,String city){
        this.name=name;
        this.logo=logo;
        this.city=city;
        this.ID=ID;
    }

}
