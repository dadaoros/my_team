package com.mugen.myteam.ApiManager;

import android.util.Log;

import org.apache.http.Header;

/**
 * Created by ORTEGON on 17/06/2015.
 */
public class ApiManagerShadow extends ApiManager {

    public static final String TEAMSRESPONSESHADOW="[{\"id\":3,\"name\":\"Atlético Nacional\"},{\"id\":2,\"name\":\"Independiente Santa Fe\"},{\"id\":1,\"name\":\"Millonarios\"},{\"id\":4,\"name\":\"Atlético Junior\"},{\"id\":5,\"name\":\" América de Cali\"},{\"id\":6,\"name\":\"Deportivo Cali\"}]";

    public static final String DBSHADOW="BEGIN TRANSACTION;\n" +
        "CREATE TABLE \"championships_championship\" (\n" +
        "  \"id\" int(11) NOT NULL ,\n" +
        "  \"name\" varchar(59) NOT NULL,\n" +
        "  \"logo\" varchar(8) NOT NULL,\n" +
        "  \"year\" date NOT NULL,\n" +
        "  PRIMARY KEY (\"id\")\n" +
        ");\n" +
        "CREATE TABLE \"championships_championship_team\" (\n" +
        "  \"id\" int(11) NOT NULL ,\n" +
        "  \"championship_id\" int(11) NOT NULL,\n" +
        "  \"team_id\" int(11) NOT NULL,\n" +
        "  PRIMARY KEY (\"id\")\n" +
        "  CONSTRAINT \"championships__team_id_6e6e7f684c347268_fk_championships_team_id\" FOREIGN KEY (\"team_id\") REFERENCES \"championships_team\" (\"id\"),\n" +
        "  CONSTRAINT \"bae7af006f6dcff77ef20c81548d9998\" FOREIGN KEY (\"championship_id\") REFERENCES \"championships_championship\" (\"id\")\n" +
        ");\n" +
        "CREATE TABLE \"championships_city\" (\n" +
        "  \"id\" int(11) NOT NULL ,\n" +
        "  \"name\" varchar(59) NOT NULL,\n" +
        "  PRIMARY KEY (\"id\")\n" +
        ");\n" +
        "CREATE TABLE \"championships_match\" (\n" +
        "  \"id\" int(11) NOT NULL ,\n" +
        "  \"date\" datetime NOT NULL,\n" +
        "  \"date_number\" int(11) NOT NULL,\n" +
        "  \"local_team_id\" int(11) NOT NULL,\n" +
        "  \"local_goals\" int(11),\n" +
        "  \"stadium_id\" int(11) NOT NULL,\n" +
        "  \"visitor_team_id\" int(11) NOT NULL,\n" +
        "  \"visitor_goals\" int(11),\n" +
        "  PRIMARY KEY (\"id\")\n" +
        "  CONSTRAINT \"champi_visitor_team_id_566ee70f5adef619_fk_championships_team_id\" FOREIGN KEY (\"visitor_team_id\") REFERENCES \"championships_team\" (\"id\"),\n" +
        "  CONSTRAINT \"champion_local_team_id_2b6196acd4159104_fk_championships_team_id\" FOREIGN KEY (\"local_team_id\") REFERENCES \"championships_team\" (\"id\"),\n" +
        "  CONSTRAINT \"champion_stadium_id_5330b18ac038417b_fk_championships_stadium_id\" FOREIGN KEY (\"stadium_id\") REFERENCES \"championships_stadium\" (\"id\")\n" +
        ");\n" +
        "CREATE TABLE \"championships_services\" (\n" +
        "  \"id\" int(11) NOT NULL ,\n" +
        "  \"url\" varchar(20) NOT NULL,\n" +
        "  \"description\" varchar(40) NOT NULL,\n" +
        "  \"has_detail\" tinyint(1) NOT NULL,\n" +
        "  PRIMARY KEY (\"id\")\n" +
        ");\n" +
        "CREATE TABLE \"championships_stadium\" (\n" +
        "  \"id\" int(11) NOT NULL ,\n" +
        "  \"name\" varchar(59) NOT NULL,\n" +
        "  \"capacity\" int(11) NOT NULL,\n" +
        "  \"city_id\" int(11) NOT NULL,\n" +
        "  PRIMARY KEY (\"id\")\n" +
        "  CONSTRAINT \"championships__city_id_3f51b64b94ffb128_fk_championships_city_id\" FOREIGN KEY (\"city_id\") REFERENCES \"championships_city\" (\"id\")\n" +
        ");\n" +
        "CREATE TABLE \"championships_team\" (\n" +
        "  \"id\" int(11) NOT NULL ,\n" +
        "  \"name\" varchar(59) NOT NULL,\n" +
        "  \"logo\" varchar(8) NOT NULL,\n" +
        "  \"city_id\" int(11) NOT NULL,\n" +
        "  PRIMARY KEY (\"id\")\n" +
        "  CONSTRAINT \"championships__city_id_1dffc6e4e23b31d3_fk_championships_city_id\" FOREIGN KEY (\"city_id\") REFERENCES \"championships_city\" (\"id\")\n" +
        ");\n" +

        "CREATE INDEX \"championships_match_championships_match_aadf6262\" ON \"championships_match\" (\"local_team_id\");\n" +
        "CREATE INDEX \"championships_match_championships_match_5c1e67db\" ON \"championships_match\" (\"stadium_id\");\n" +
        "CREATE INDEX \"championships_match_championships_match_0fc7cc15\" ON \"championships_match\" (\"visitor_team_id\");\n" +
        "CREATE INDEX \"championships_championship_team_championships_championship_championship_id_5ddce2d3e999df95_uniq\" ON \"championships_championship_team\" (\"championship_id\",\"team_id\");\n" +
        "CREATE INDEX \"championships_championship_team_championships_championship_team_f6a7ca40\" ON \"championships_championship_team\" (\"team_id\");\n" +
        "CREATE INDEX \"championships_stadium_championships__city_id_3f51b64b94ffb128_fk_championships_city_id\" ON \"championships_stadium\" (\"city_id\");\n" +
        "CREATE INDEX \"championships_team_championships__city_id_1dffc6e4e23b31d3_fk_championships_city_id\" ON \"championships_team\" (\"city_id\");\n" +

        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (1,\"Medellin\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (2,\"Cali\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (3,\"Envigado\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (4,\"Ibague\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (5,\"Bogotá\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (6,\"Pasto\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (7,\"Barranquilla\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (8,\"Tunja\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (9,\"Cúcuta\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (10,\"Tuluá\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (11,\"Monteria\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (12,\"Manizales\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (13,\"Neiva\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (14,\"Cartagena\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (15,\"Rionegro\");\n"+
        "INSERT INTO \"championships_city\"(\"id\",\"name\") VALUES (16,\"Barrancabermeja\");\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (1,'Millonarios F.C.','url','5');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (2,'Independiente Santa Fe','url','5');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (3,'Atlético Nacional','url','1');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (4,'Atlético Junior','url','7');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (5,'America de Cali','url','2');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (6,'Deportivo Cali','url','2');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (7,'Patriotas F.C.','url','8');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (8,'Cortuluá','url','10');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (9,'Alianza Petrolera','url','16');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (10,'Jaguares F.C','url','11');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (11,'Envigado F.C','url','3');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (12,'Once Caldas','url','12');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (13,'Independiente Medellin','url','1');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (14,'Atletico Huila','url','13');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (15,'Aguilas Doradas','url','15');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (16,'Uniautónoma','url','7');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (17,'Deportivo Pasto','url','6');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (18,'Boyacá Chicó','url','8');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (19,'Deportes Tolima','url','4');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (20,'Cúcuta Deportivo','url','9');\n"+
        "INSERT INTO `championships_team`(`id`,`name`,`logo`,`city_id`) VALUES (21,'La Equidad','url','5');\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (1,2,1);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (2,2,2);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (3,2,3);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (4,2,4);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (5,2,6);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (6,2,7);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (7,2,8);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (8,2,9);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (9,2,10);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (10,2,11);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (11,2,12);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (12,2,13);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (13,2,14);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (14,2,15);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (15,2,16);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (16,2,17);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (17,2,18);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (18,2,19);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (19,2,20);\n"+
        "INSERT INTO `championships_championship_team`(`id`,`championship_id`,`team_id`) VALUES (20,2,21);\n"+
        "INSERT INTO `championships_stadium`(`id`,`name`,`capacity`,`city_id`) VALUES (1,'Estadio Nemesio Camacho El Campin','0','5');\n"+
        "INSERT INTO `championships_match`(`id`,`date`,`date_number`,`local_team_id`,`stadium_id`,`visitor_team_id`,`local_goals`,`visitor_goals`) VALUES (1,'2015-01-01 10:00:00',1,1,1,2,0,1);\n"+
        "INSERT INTO `championships_match`(`id`,`date`,`date_number`,`local_team_id`,`stadium_id`,`visitor_team_id`,`local_goals`,`visitor_goals`) VALUES (2,'2015-01-05 10:00:00',2,3,1,1,1,0);\n"+

            "END TRANSACTION;";
    public static final String UPDATES_SHADOW="[{\"version\":2,\"sql\":\"Segundo SQL\"},{\"version\":3,\"sql\":\"Tercer SQL :)\"}]";

    @Override
    public void execute(String URL) {
        Header[] headers = {};
        byte[] a=null;
        switch (URL){
            case URL_TEAMS:
                a=TEAMSRESPONSESHADOW.getBytes();
                super.getHandler().onSuccess(0, headers, a);
                super.getHandler().onFinish();
                break;
            case URL_DUMP:
                a=DBSHADOW.getBytes();
                super.getHandler().onSuccess(0, headers, a);
                super.getHandler().onFinish();
                break;
            default:
                a=UPDATES_SHADOW.getBytes();
                super.getHandler().onSuccess(0, headers, a);
                super.getHandler().onFinish();
                break;

        }
    }
}
