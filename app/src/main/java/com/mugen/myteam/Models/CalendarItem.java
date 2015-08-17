package com.mugen.myteam.Models;

import java.util.Date;

/**
 * Created by dadaoros on 28/07/15.
 */
public class CalendarItem implements Comparable<CalendarItem>{
    private String localTeamName;
    private String visitorTeamName;
    private Date dateTimePartido;
    private String fechaPartido;
    private String golesLocal;

    public CalendarItem(String localTeamName, String visitorTeamName, Date datetimePartido, String fechaPartido, String golesLocal, String golesVisitante, boolean jugado) {
        this.localTeamName = localTeamName;
        this.visitorTeamName = visitorTeamName;
        this.fechaPartido = fechaPartido;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.dateTimePartido = datetimePartido;
        this.jugado = jugado;
    }

    public String getLocalTeamName() {
        return localTeamName;
    }

    public String getVisitorTeamName() {
        return visitorTeamName;
    }

    public Date getDateTimePartido() {
        return dateTimePartido;
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

    public boolean fueJugado() {
        return jugado;
    }

    private String golesVisitante;
    private boolean jugado;

    @Override
    public int compareTo(CalendarItem another) {
        return dateTimePartido.compareTo(another.getDateTimePartido());
    }
}
