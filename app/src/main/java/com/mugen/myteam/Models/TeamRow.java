package com.mugen.myteam.Models;

/**
 * Created by dadaoros on 22/07/15.
 */
public class TeamRow {
    private int team_id;
    private String teamName;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int gamesDrawn;
    private int teamGoals;
    private int opponentGoals;
    private int goalDifference;
    private int points;



    public TeamRow(String teamName, int gamesPlayed, int gamesWon, int gamesLost, int gamesDrawn, int teamGoals, int opponentGoals, int goalDifference, int points) {
        this.teamName = teamName;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.gamesDrawn = gamesDrawn;
        this.teamGoals = teamGoals;
        this.opponentGoals = opponentGoals;
        this.goalDifference = goalDifference;
        this.points = points;
    }
    public TeamRow(int id, String teamName){
        this.team_id=id;
        this.teamName=teamName;


    }


    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public int getGamesDrawn() {
        return gamesDrawn;
    }

    public int getTeamGoals() {
        return teamGoals;
    }

    public int getOpponentGoals() {
        return opponentGoals;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public int getPoints() {
        return points;
    }



    public String getTeamName() {
        return teamName;
    }
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getTeam_id() {
        return team_id;
    }
}
