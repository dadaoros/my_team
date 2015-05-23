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

    public String getStadium() {
        return stadium;
    }

    private String name;
    private String logo;
    private String stadium;
    public Team(String name,String logo,String stadium){
        this.name=name;
        this.logo=logo;
        this.stadium=stadium;
    }

}
