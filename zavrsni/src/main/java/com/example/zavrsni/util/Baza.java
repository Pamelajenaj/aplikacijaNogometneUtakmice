package com.example.zavrsni.util;
import com.example.zavrsni.HelloController;
import com.example.zavrsni.entitet.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Baza {

    public static Connection connectToDatabase() throws SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(new FileReader("dat/database.properties"));

        String databaseURL = configuration.getProperty("databaseURL");
        String databaseUsername = configuration.getProperty("databaseUsername");
        String databasePassword = configuration.getProperty("databasePassword");

        Connection veza = DriverManager.getConnection(databaseURL,databaseUsername,databasePassword);

        return veza;
    }

    public static List<Igrac> dohvatiIgraceIzBaze(Connection veza) throws SQLException {
        List<Igrac> igracLista = new ArrayList<>();

        Statement sqlStatement = veza.createStatement();

        ResultSet igracResultSet = sqlStatement.executeQuery("SELECT * FROM igrac");

        while(igracResultSet.next()) {
            Long igracId = igracResultSet.getLong("ID");
            String ime = igracResultSet.getString("IME");
            String prezime = igracResultSet.getString("PREZIME");
            String oib = igracResultSet.getString("OIB");
            LocalDate datumRodenja = igracResultSet.getDate("DATUM_RODJENJA").toLocalDate();
            String drzava = igracResultSet.getString("DRZAVA");

            Igrac igrac = new Igrac(igracId,ime,prezime,oib,datumRodenja,drzava);

            igracLista.add(igrac);
        }

        return igracLista;
    }

    public static List<Menadzer> dohvatiMenadzereIzBaze(Connection veza) throws SQLException {
        List<Menadzer> menadzerLista = new ArrayList<>();

        Statement sqlStatement = veza.createStatement();

        ResultSet menadzerResultSet = sqlStatement.executeQuery("SELECT * FROM MENADZER");

        while(menadzerResultSet.next()) {
            Long menadzerId = menadzerResultSet.getLong("ID");
            String ime = menadzerResultSet.getString("IME");
            String prezime = menadzerResultSet.getString("PREZIME");
            String oib = menadzerResultSet.getString("OIB");
            LocalDate datumRodenja = menadzerResultSet.getDate("DATUM_RODJENJA").toLocalDate();

            Menadzer menadzer = new Menadzer.BuilderPattern(menadzerId).imeMenadzera(ime).prezimeMendazera(prezime)
                                                .oibMenadzera(oib).datumRodjenjaMenadzera(datumRodenja).builder();

            menadzerLista.add(menadzer);
        }

        return menadzerLista;
    }

    public static List<Stadion> dohvatiStadioneIzBaze(Connection veza) throws SQLException {
        List<Stadion> stadionLista = new ArrayList<>();

        Statement sqlStatement = veza.createStatement();

        ResultSet stadionResultSet = sqlStatement.executeQuery("SELECT * FROM STADION");

        while(stadionResultSet.next()) {
            Long stadionId = stadionResultSet.getLong("ID");
            String naziv = stadionResultSet.getString("NAZIV");
            Integer broj_sjedala = stadionResultSet.getInt("BROJ_SJEDALA");
            String lokacija = stadionResultSet.getString("LOKACIJA");

            Stadion stadion = new Stadion(stadionId,naziv,broj_sjedala,lokacija);

            stadionLista.add(stadion);
        }

        return stadionLista;
    }


    public static List<Tim> dohvatiTimoveIzBaze(Connection veza) throws SQLException {
        List<Tim> timLista = new ArrayList<>();

        Statement sqlStatement = veza.createStatement();

        ResultSet stadionResultSet = sqlStatement.executeQuery("SELECT * FROM TIM");

        while(stadionResultSet.next()) {
            Long timID = stadionResultSet.getLong("ID");
            Long menadzer= stadionResultSet.getLong("MENADZER_ID");
            String ime_tima= stadionResultSet.getString("IMETIMA");

            Tim tim = new Tim(timID,new HashSet<>(), HelloController.listaMenadzera.get(Math.toIntExact(menadzer)-1),ime_tima);

            timLista.add(tim);
        }

        ResultSet igraciUTimuResultSet = sqlStatement.executeQuery("SELECT * FROM TIM_IGRACI");

        while(igraciUTimuResultSet.next()) {
            Long timID = igraciUTimuResultSet.getLong("TIM_ID");
            Long igracID= igraciUTimuResultSet.getLong("IGRAC_ID");

            Igrac igrac = HelloController.listaIgraca.get(Math.toIntExact(igracID)-1);

            timLista.get(Math.toIntExact(timID)-1).getListaIgraca().add(igrac);
        }

        return timLista;
    }


    public static List<Utakmica> dohvatiUtakmiceIzBaze(Connection veza) throws SQLException {
        List<Utakmica> utakmicaLista = new ArrayList<>();

        Statement sqlStatement = veza.createStatement();

        ResultSet utakmicaResultSet = sqlStatement.executeQuery("SELECT * FROM UTAKMICA");

        while(utakmicaResultSet.next()) {
            Long utakmicaId = utakmicaResultSet.getLong("ID");
            Long prviTimID = utakmicaResultSet.getLong("ID_PRVOG_TIMA");
            Long drugiTimID = utakmicaResultSet.getLong("ID_DRUGOG_TIMA");
            Long stadionID = utakmicaResultSet.getLong("ID_STADIONA");
            LocalDate datumUtakmice = utakmicaResultSet.getDate("DATUM").toLocalDate();
            String vrijemeUtakmice = utakmicaResultSet.getString("VRIJEME");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
            String datumIVrijeme = datumUtakmice.format(formatter) +" "+ vrijemeUtakmice;

            DateTimeFormatter noviFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
            LocalDateTime datumIVrijemeUtakmice = LocalDateTime.parse(datumIVrijeme,noviFormatter);

            Tim prviTim = HelloController.listaTimova.get(Math.toIntExact(prviTimID)-1);
            Tim drugiTim = HelloController.listaTimova.get(Math.toIntExact(drugiTimID)-1);
            Stadion stadion = HelloController.listaStadiona.get(Math.toIntExact(stadionID)-1);

            Utakmica utakmica = new Utakmica(utakmicaId,prviTim,drugiTim,stadion,datumIVrijemeUtakmice);

            utakmicaLista.add(utakmica);
        }

        return utakmicaLista;
    }


}
