package com.example.zavrsni.entitet;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Igrac extends Osoba implements Serializable {

    @Serial
    private static final long serialVersionUID = 215980053203549305L;
    private Long id;
    private String ime;
    private String prezime;
    private String oib;
    private LocalDate datumRodenja;
    private String drzava;


    public Igrac(Long id, String ime, String prezime, String oib, LocalDate datumRodenja, String drzava) {
        super(ime,prezime,oib,datumRodenja);
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.oib = oib;
        this.datumRodenja = datumRodenja;
        this.drzava = drzava;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getDatumRodenja() {
        return datumRodenja;
    }

    public void setDatumRodenja(LocalDate datumRodenja) {
        this.datumRodenja = datumRodenja;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }
}
