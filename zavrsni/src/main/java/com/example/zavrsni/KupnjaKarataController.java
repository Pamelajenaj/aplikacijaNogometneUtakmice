package com.example.zavrsni;

import com.example.zavrsni.entitet.Karta;
import com.example.zavrsni.entitet.Utakmica;
import com.example.zavrsni.exceptioni.NepostojeceSjedalo;
import com.example.zavrsni.exceptioni.ZauzetoSjedalo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalLong;

public class KupnjaKarataController {
    public static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @FXML
    private Label utakmicaLabel;

    @FXML
    private Label brojSjedalaLabel;

    @FXML
    private TextField odabranoSjedalo;

    @FXML
    public void initialize(){

        Utakmica utakmica = PretragaUtakmicaController.odabranaUtakmica;

        utakmicaLabel.setText(utakmica.getPrviTim().getImeTima()+" VS "+utakmica.getDrugiTim().getImeTima()+", Stadion:"+utakmica.getStadion().getImeStadiona()+", Datum: "+utakmica.getDatumIVrijemeUtakmice().toString());

        brojSjedalaLabel.setText("Max broj sjedala: "+utakmica.getStadion().getBrojSjedala());

    }

    @FXML
    public void onPotvrdiButtonClick() {

        String sjedalo = odabranoSjedalo.getText();

        StringBuilder pogreske = new StringBuilder();
        Utakmica utakmica = PretragaUtakmicaController.odabranaUtakmica;

        try {
            utakmica.getStadion().provjeraSjedala(sjedalo);
            utakmica.getStadion().zauzetostSjedala(sjedalo);
        } catch (NepostojeceSjedalo | ZauzetoSjedalo e) {
            pogreske.append(e.getMessage());
            logger.error("Korisnik "+HelloController.ulogiraniNavijac.getUsername()+" napravio je pogrešku: "+e.getMessage()+"\n");
            HelloController.svePromjene.add("Korisnik "+HelloController.ulogiraniNavijac.getUsername()+" napravio je pogrešku: "+e.getMessage());
        }

        if(pogreske.isEmpty()) {

            List<Karta> listaKarata = HelloController.listaKarata;

            OptionalLong maksimalniId = listaKarata.stream().mapToLong(ispit -> ispit.getIdKarte()).max();

            if(maksimalniId.isPresent()){
                Karta karta=new Karta(maksimalniId.getAsLong()+1,HelloController.ulogiraniNavijac,utakmica,Integer.valueOf(sjedalo), LocalDateTime.now());
                listaKarata.add(karta);


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

            }
            else{
                Karta karta=new Karta(1L,HelloController.ulogiraniNavijac,utakmica,Integer.valueOf(sjedalo),LocalDateTime.now());
                listaKarata.add(karta);


                /** SERIJALIZACIJA **/
                try {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("dat/serijalizraneKarte.dat"));

                    out.writeObject(listaKarata);

                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno ste kupili kartu!");
            alert.setHeaderText("Zahvaljujemo na kupovini");
            alert.setContentText("Karta se nalazi u \"Moje karte\" ");

            alert.showAndWait();

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pogresan unos podataka");
            alert.setHeaderText("Pogreška");
            alert.setContentText(pogreske.toString());

            alert.showAndWait();
        }

    }





}
