package com.example.zavrsni.util;

import com.example.zavrsni.entitet.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Datoteke {
    private static final int BROJ_LINIJA_NAVIJAC = 3;

    private static final String NAVIJAC_FILE_NAME="dat/navijaci.txt";


    public static List<Navijac> ucitajNavijace() {
        List<Navijac> listaNavijaca= new ArrayList<>();
        File navijaciFile = new File(NAVIJAC_FILE_NAME);

        String line;
        Integer lineCounter=1;

        String id = "", username="", password="";

        try {
            BufferedReader navijacFileReader = new BufferedReader(new FileReader(navijaciFile));

            while((line=navijacFileReader.readLine()) != null){
                if(lineCounter % BROJ_LINIJA_NAVIJAC == 1){
                    id = line;
                }
                else if(lineCounter % BROJ_LINIJA_NAVIJAC == 2){
                    username = line;
                }
                else if(lineCounter % BROJ_LINIJA_NAVIJAC == 0){
                    password = line;

                    listaNavijaca.add(new Navijac(Long.valueOf(id),username,password));
                }
                lineCounter++;
            }

            navijacFileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return listaNavijaca;
    }

    public static List<Karta> ucitajKarte() {
        List<Karta> lista;

        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("dat/serijalizraneKarte.dat"));

            lista = (List<Karta>) in.readObject();

            in.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public static List<String> ucitajPromjene() {
        List<String> promjene;

        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream( "dat/serijaliziranePogreske.dat"));

            promjene = (List<String>) in.readObject();

            in.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return promjene;

    }
}
