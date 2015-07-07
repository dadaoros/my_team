package com.mugen.myteam.ApiManager;

import org.apache.http.Header;

/**
 * Created by ORTEGON on 17/06/2015.
 */
public class ApiManagerShadow extends ApiManager{

    public static final String TEAMSRESPONSESHADOW="[{\"id\":3,\"name\":\"Atlético Nacional\"},{\"id\":2,\"name\":\"Independiente Santa Fe\"},{\"id\":1,\"name\":\"Millonarios\"},{\"id\":4,\"name\":\"Atlético Junior\"},{\"id\":5,\"name\":\" América de Cali\"},{\"id\":6,\"name\":\"Deportivo Cali\"}]";
    public static final String DBSHADOW="BEGIN;\n" +
            "CREATE TABLE championships_championship (\n" +
            "    id integer NOT NULL,\n" +
            "    name character varying(59) NOT NULL,\n" +
            "    logo character varying(8) NOT NULL,\n" +
            "    year date NOT NULL\n" +
            ");\n" +
            "CREATE TABLE championships_championship_team (\n" +
            "    id integer NOT NULL,\n" +
            "    championship_id integer NOT NULL,\n" +
            "    team_id integer NOT NULL\n" +
            ");\n" +
            "CREATE TABLE championships_city (\n" +
            "    id integer NOT NULL,\n" +
            "    name character varying(59) NOT NULL\n" +
            ");\n" +
            "CREATE TABLE championships_match (\n" +
            "    id integer NOT NULL,\n" +
            "    date timestamp with time zone NOT NULL,\n" +
            "    date_number integer NOT NULL,\n" +
            "    local_team_id integer NOT NULL,\n" +
            "    stadium_id integer NOT NULL,\n" +
            "    visitor_team_id integer NOT NULL\n" +
            ");\n" +
            "CREATE TABLE championships_services (\n" +
            "    id integer NOT NULL,\n" +
            "    url character varying(20) NOT NULL,\n" +
            "    description character varying(40) NOT NULL,\n" +
            "    has_detail boolean NOT NULL\n" +
            ");\n" +
            "CREATE TABLE championships_stadium (\n" +
            "    id integer NOT NULL,\n" +
            "    name character varying(59) NOT NULL,\n" +
            "    capacity integer NOT NULL,\n" +
            "    city_id integer NOT NULL\n" +
            ");\n" +
            "CREATE TABLE championships_team (\n" +
            "    id integer NOT NULL,\n" +
            "    name character varying(59) NOT NULL,\n" +
            "    logo character varying(8) NOT NULL,\n" +
            "    stadium character varying(50) NOT NULL,\n" +
            "    city_id integer NOT NULL\n" +
            ");\n" +
            "END;";

    @Override
    public void execute(String URL) {
        if (URL == URL_TEAMS) {
            Header[] headers = {};
            byte[] a=TEAMSRESPONSESHADOW.getBytes();
            super.getHandler().onSuccess(0, headers, a);
            super.getHandler().onFinish();
        }
        if(URL== URL_DUMP){
            Header[] headers = {};
            byte[] a=DBSHADOW.getBytes();
            super.getHandler().onSuccess(0, headers, a);
            super.getHandler().onFinish();
        }
    }
}
