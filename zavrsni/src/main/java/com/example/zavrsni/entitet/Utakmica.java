package com.example.zavrsni.entitet;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Utakmica implements Serializable {

    @Serial
    private static final long serialVersionUID = 415734398825958406L;

    private Long id;
    private Tim prviTim;
    private Tim drugiTim;
    private Stadion stadion;
    private LocalDateTime datumIVrijemeUtakmice;

    public Utakmica(Long id, Tim prviTim, Tim drugiTim, Stadion stadion, LocalDateTime datumIVrijemeUtakmice) {
        this.id = id;
        this.prviTim = prviTim;
        this.drugiTim = drugiTim;
        this.stadion = stadion;
        this.datumIVrijemeUtakmice = datumIVrijemeUtakmice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tim getPrviTim() {
        return prviTim;
    }

    public void setPrviTim(Tim prviTim) {
        this.prviTim = prviTim;
    }

    public Tim getDrugiTim() {
        return drugiTim;
    }

    public void setDrugiTim(Tim drugiTim) {
        this.drugiTim = drugiTim;
    }

    public Stadion getStadion() {
        return stadion;
    }

    public void setStadion(Stadion stadion) {
        this.stadion = stadion;
    }

    public LocalDateTime getDatumIVrijemeUtakmice() {
        return datumIVrijemeUtakmice;
    }

    public void setDatumIVrijemeUtakmice(LocalDateTime datumIVrijemeUtakmice) {
        this.datumIVrijemeUtakmice = datumIVrijemeUtakmice;
    }
}
