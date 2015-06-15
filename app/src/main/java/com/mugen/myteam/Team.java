package com.mugen.myteam;

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

    private String name;
    private String logo;
    private String city;
    public Team(String name,String logo,String city){
        this.name=name;
        this.logo=logo;
        this.city=city;
    }

}
