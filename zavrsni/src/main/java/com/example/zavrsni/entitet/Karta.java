package com.example.zavrsni.entitet;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Karta implements Serializable {

    @Serial
    private static final long serialVersionUID = -685142501662965531L;
    private Long idKarte;
    private Navijac navijac;
    private Utakmica utakmica;
    private Integer odabranoSjedalo;

    private LocalDateTime vrijemeKupnje;

    public Karta(Long idKarte, Navijac navijac, Utakmica utakmica, Integer odabranoSjedalo, LocalDateTime vrijemeKupnje) {
        this.idKarte = idKarte;
        this.navijac = navijac;
        this.utakmica = utakmica;
        this.odabranoSjedalo = odabranoSjedalo;
        this.vrijemeKupnje = vrijemeKupnje;
    }

    public Navijac getNavijac() {
        return navijac;
    }

    public void setNavijac(Navijac idNavijaca) {
        this.navijac = idNavijaca;
    }

    public Utakmica getUtakmica() {
        return utakmica;
    }

    public void setUtakmica(Utakmica utakmica) {
        this.utakmica = utakmica;
    }

    public Integer getOdabranoSjedalo() {
        return odabranoSjedalo;
    }

    public void setOdabranoSjedalo(Integer odabranoSjedalo) {
        this.odabranoSjedalo = odabranoSjedalo;
    }

    public Long getIdKarte() {
        return idKarte;
    }

    public void setIdKarte(Long idKarte) {
        this.idKarte = idKarte;
    }

    public LocalDateTime getVrijemeKupnje() {
        return vrijemeKupnje;
    }

    public void setVrijemeKupnje(LocalDateTime vrijemeKupnje) {
        this.vrijemeKupnje = vrijemeKupnje;
    }
}
