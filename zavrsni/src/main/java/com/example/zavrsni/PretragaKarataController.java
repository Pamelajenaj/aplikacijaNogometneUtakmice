package com.example.zavrsni;

import com.example.zavrsni.entitet.Karta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.stream.Collectors;

public class PretragaKarataController {

    public List<Karta> listaKarata;

    public static Karta KartaZaIzmjenu;

    public static int index;

    @FXML
    private Label upitnik;

    @FXML
    private Button gumbDa;

    @FXML
    private Button gumbNe;

    @FXML
    private Button gumbDaUredi;

    @FXML
    private TableView<Karta> kartaTableView;

    @FXML
    private TableColumn<Karta, String> idKarteTableColumn;

    @FXML
    private TableColumn<Karta, String> imeTableColumn;

    @FXML
    private TableColumn<Karta, String> utakmicaTableColumn;

    @FXML
    private TableColumn<Karta, String> sjedaloTableColumn;


    @FXML
    public void initialize(){
        listaKarata = HelloController.listaKarata;

        List<Karta> filtriranaListaKarata = listaKarata.stream().filter(s->s.getNavijac().getUsername().equals(HelloController.ulogiraniNavijac.getUsername())).collect(Collectors.toList());

        idKarteTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIdKarte().toString()));
        imeTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNavijac().getUsername()+" "+c.getValue().getNavijac().getPassword()));
        utakmicaTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getUtakmica().getPrviTim().getImeTima()+" VS "+c.getValue().getUtakmica().getDrugiTim().getImeTima()));
        sjedaloTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getOdabranoSjedalo().toString()));

        ObservableList<Karta> observableListKarata = FXCollections.observableList(filtriranaListaKarata);
        kartaTableView.setItems(observableListKarata);

        upitnik.setVisible(false);
        gumbDa.setVisible(false);
        gumbNe.setVisible(false);
        gumbDaUredi.setVisible(false);
    }

    @FXML
    public void onObrisiButtonClick() {
        upitnik.setVisible(true);
        gumbDa.setVisible(true);
        gumbNe.setVisible(true);
    }

    @FXML
    public void onIzmjeniButtonClick() {
        upitnik.setVisible(true);
        gumbDaUredi.setVisible(true);
        gumbNe.setVisible(true);
    }


    @FXML
    public void onDaButtonClick(){
        int indexKarte = kartaTableView.getSelectionModel().getFocusedIndex();
        listaKarata = HelloController.listaKarata;
        KupnjaKarataController.logger.info("Korisnik "+HelloController.ulogiraniNavijac.getUsername()+" obrisao je kartu: ID:"+listaKarata.get(indexKarte).getIdKarte()+"\n");
        HelloController.svePromjene.add("Korisnik "+HelloController.ulogiraniNavijac.getUsername()+" obrisao je kartu: ID:"+listaKarata.get(indexKarte).getIdKarte());

        listaKarata.remove(indexKarte);

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

        ObservableList<Karta> observableListKarata = FXCollections.observableList(listaKarata);
        kartaTableView.setItems(observableListKarata);

        upitnik.setVisible(false);
        gumbDa.setVisible(false);
        gumbNe.setVisible(false);
    }

    @FXML
    public void onNeButtonClick(){
        upitnik.setVisible(false);
        gumbDa.setVisible(false);
        gumbNe.setVisible(false);
        gumbDaUredi.setVisible(false);
    }

    @FXML
    public void onDaIzmjeniButtonClick() throws IOException {
        index = kartaTableView.getSelectionModel().getSelectedIndex();
        KartaZaIzmjenu = HelloController.listaKarata.get(index);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("izmjeniKartu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Hello!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }


}
