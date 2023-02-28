package com.example.zavrsni.entitet;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Tim implements Serializable {


    @Serial
    private static final long serialVersionUID = 8011264959914380491L;
    private Long id;

    private String imeTima;

    private Set<Igrac> listaIgraca;
    private Menadzer menadzer;

    public Tim(Long id, Set<Igrac> listaIgraca, Menadzer menadzer,String imeTima) {
        this.id = id;
        this.listaIgraca = listaIgraca;
        this.menadzer = menadzer;
        this.imeTima = imeTima;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tim tim)) return false;
        return Objects.equals(getId(), tim.getId()) && Objects.equals(getListaIgraca(), tim.getListaIgraca()) && Objects.equals(getMenadzer(), tim.getMenadzer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getListaIgraca(), getMenadzer());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Igrac> getListaIgraca() {
        return listaIgraca;
    }

    public void setListaIgraca(Set<Igrac> listaIgraca) {
        this.listaIgraca = listaIgraca;
    }

    public Menadzer getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
    }

    public String getImeTima() {
        return imeTima;
    }

    public void setImeTima(String imeTima) {
        this.imeTima = imeTima;
    }
}
