package com.mugen.myteam.Models;

/**
 * Created by dadaoros on 28/07/15.
 */
public class CalendarItem {
    private String localTeamName;
    private String visitorTeamName;
    private String horaPartido;
    private String fechaPartido;
    private String golesLocal;

    public CalendarItem(String localTeamName, String visitorTeamName, String horaPartido, String fechaPartido, String golesLocal, String golesVisitante, boolean jugado) {
        this.localTeamName = localTeamName;
        this.visitorTeamName = visitorTeamName;
        this.horaPartido = horaPartido;
        this.fechaPartido = fechaPartido;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.jugado = jugado;
    }

    public String getLocalTeamName() {
        return localTeamName;
    }

    public String getVisitorTeamName() {
        return visitorTeamName;
    }

    public String getHoraPartido() {
        return horaPartido;
    }

    public String getFechaPartido() {
        return fechaPartido;
    }

    public String getGolesLocal() {
        return golesLocal;
    }

    public String getGolesVisitante() {
        return golesVisitante;
    }

    public boolean isJugado() {
        return jugado;
    }

    private String golesVisitante;
    private boolean jugado;
}
