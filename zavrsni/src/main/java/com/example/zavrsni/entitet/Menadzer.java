package com.example.zavrsni.entitet;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Menadzer extends Osoba implements Serializable {
    @Serial
    private static final long serialVersionUID = -6394266476886557181L;
    private Long id;
    private String ime;
    private String prezime;
    private String oib;
    private LocalDate datumRodenja;

    public Menadzer(Long id, String ime, String prezime, String oib, LocalDate datumRodenja) {
        super(ime,prezime,oib,datumRodenja);
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.oib = oib;
        this.datumRodenja = datumRodenja;
    }

    public Menadzer(){}

    public static class BuilderPattern{
        private Long id;
        private String ime;
        private String prezime;
        private String oib;
        private LocalDate datumRodenja;

        public BuilderPattern (Long id){
            this.id= id;
        }

        public BuilderPattern imeMenadzera(String ime){
            this.ime = ime;
            return this;
        }

        public BuilderPattern prezimeMendazera(String prezime){
            this.prezime = prezime;
            return this;
        }

        public BuilderPattern oibMenadzera(String oib){
            this.oib = oib;
            return this;
        }

        public BuilderPattern datumRodjenjaMenadzera(LocalDate datumRodenja){
            this.datumRodenja = datumRodenja;
            return this;
        }

        public Menadzer builder(){
            Menadzer menadzer= new Menadzer();

            menadzer.id = this.id;
            menadzer.ime = this.ime;
            menadzer.prezime = this.prezime;
            menadzer.oib = this.oib;
            menadzer.datumRodenja = this.datumRodenja;

            return menadzer;
        }


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
}
