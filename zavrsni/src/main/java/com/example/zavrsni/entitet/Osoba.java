package com.example.zavrsni.entitet;

import java.time.LocalDate;

public abstract class Osoba {

    private String ime;
    private String prezime;
    private String oib;
    private LocalDate datumRodjenja;

    public Osoba(String ime, String prezime, String oib, LocalDate datumRodjenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.oib = oib;
        this.datumRodjenja = datumRodjenja;
    }

    public Osoba(){}

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
