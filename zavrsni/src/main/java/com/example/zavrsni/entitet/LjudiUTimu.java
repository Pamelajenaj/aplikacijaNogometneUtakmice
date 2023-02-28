package com.example.zavrsni.entitet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LjudiUTimu<T> extends Osoba{

    private List<T> ljudiKojiCineTim = new ArrayList<>();

    public LjudiUTimu(String ime, String prezime, String oib, LocalDate datumRodjenja) {
        super(ime, prezime, oib, datumRodjenja);
    }

    public LjudiUTimu() {
    }

    public void dodajOsobuNaTim(T objektOsoba){
        ljudiKojiCineTim.add(objektOsoba);
    }

    public List<T> vratiListu(){
        return ljudiKojiCineTim;
    }

}
