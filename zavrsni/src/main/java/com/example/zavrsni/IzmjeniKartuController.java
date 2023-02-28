package com.example.zavrsni;

import com.example.zavrsni.entitet.Karta;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class IzmjeniKartuController {

    @FXML
    private Label kartaLabel;

    @FXML
    private Label brojSjedalaLabel;

    @FXML
    private TextField odabranoSjedalo;

    @FXML
    public void initialize(){

        Karta karta = PretragaKarataController.KartaZaIzmjenu;

        kartaLabel.setText(karta.getUtakmica().getPrviTim().getImeTima()+" VS "+karta.getUtakmica().getDrugiTim().getImeTima()
                +", Stadion:"+karta.getUtakmica().getStadion().getImeStadiona()+", Datum: "+karta.getUtakmica().getDatumIVrijemeUtakmice().toString());

        brojSjedalaLabel.setText("Max broj sjedala: "+karta.getUtakmica().getStadion().getBrojSjedala());
    }

    @FXML
    public void onIzmjeniButtonClick(){
        String sjedalo = odabranoSjedalo.getText();

        List<Karta> listaKarata = HelloController.listaKarata;
        Karta karta = PretragaKarataController.KartaZaIzmjenu;
        int indexKarte = PretragaKarataController.index;

        KupnjaKarataController.logger.info("Korisnik "+HelloController.ulogiraniNavijac.getUsername()+" napravio je izmjenu: staro sjedalo: "+karta.getOdabranoSjedalo()+", novo sjedalo:"+sjedalo+"\n");
        HelloController.svePromjene.add("Korisnik "+HelloController.ulogiraniNavijac.getUsername()+" napravio je izmjenu: staro sjedalo: "+karta.getOdabranoSjedalo()+", novo sjedalo:"+sjedalo);

        karta.setOdabranoSjedalo(Integer.valueOf(sjedalo));
        listaKarata.set(indexKarte,karta);

                /** SERIJALIZACIJA **/
                try {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("dat/serijalizraneKarte.dat"));
                    ObjectOutputStream pogreskeFile = new ObjectOutputStream(new FileOutputStream("dat/serijaliziranePogreske.dat"));

                    out.writeObject(listaKarata);
                    pogreskeFile.writeObject(HelloController.svePromjene);

                    out.close();
                    pogreskeFile.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Uspješno ste izmjenili kartu!");
                alert.setHeaderText("Zahvaljujemo na kupovini");
                alert.setContentText("Karta se nalazi u \"Moje karte\" ");

                alert.showAndWait();

    }


}
