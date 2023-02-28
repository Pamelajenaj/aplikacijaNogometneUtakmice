package com.example.zavrsni.entitet;

import com.example.zavrsni.HelloController;
import com.example.zavrsni.PretragaUtakmicaController;
import com.example.zavrsni.exceptioni.NepostojeceSjedalo;
import com.example.zavrsni.exceptioni.ZauzetoSjedalo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public final class Stadion implements Serializable, sjedalaStadiona {

    @Serial
    private static final long serialVersionUID = 2384131885466312625L;
    private Long id;
    private String imeStadiona;
    private Integer brojSjedala;
    private String lokacija;

    public Stadion(Long id, String imeStadiona, Integer brojSjedala, String lokacija) {
        this.id = id;
        this.imeStadiona = imeStadiona;
        this.brojSjedala = brojSjedala;
        this.lokacija = lokacija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImeStadiona() {
        return imeStadiona;
    }

    public void setImeStadiona(String imeStadiona) {
        this.imeStadiona = imeStadiona;
    }

    public Integer getBrojSjedala() {
        return brojSjedala;
    }

    public void setBrojSjedala(Integer brojSjedala) {
        this.brojSjedala = brojSjedala;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }


    @Override
    public void provjeraSjedala(String sjedalo) throws NepostojeceSjedalo {

            Utakmica utakmica = PretragaUtakmicaController.odabranaUtakmica;

            if(Integer.parseInt(sjedalo)<1 || Integer.parseInt(sjedalo)>utakmica.getStadion().getBrojSjedala()) {
                throw new NepostojeceSjedalo("Odabrali ste sjedalo koje ne postoji!");
            }

    }

    @Override
    public void zauzetostSjedala(String sjedalo) throws ZauzetoSjedalo {
        List<Karta> listaKarata = HelloController.listaKarata;

        for(int i=0;i<listaKarata.size();i++){
            if(listaKarata.get(i).getOdabranoSjedalo().equals(Integer.valueOf(sjedalo))) {
                throw new ZauzetoSjedalo("Sjedalo koje ste odabrali je zauzeto!");
            }
        }
    }
}
