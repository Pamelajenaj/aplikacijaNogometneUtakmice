package com.example.zavrsni;

import com.example.zavrsni.entitet.Menadzer;
import com.example.zavrsni.entitet.Stadion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PretragaStadionaController {

    public List<Stadion> listaStadiona;
    @FXML
    private TextField imeStadiona;

    @FXML
    private TextField lokacija;


    @FXML
    private TableView<Stadion> stadionTableView;

    @FXML
    private TableColumn<Stadion, String> idStadionTableColumn;

    @FXML
    private TableColumn<Stadion, String> imeStadionTableColumn;

    @FXML
    private TableColumn<Stadion, String> brojSjedalaTableColumn;

    @FXML
    private TableColumn<Stadion, String> lokacijaTableColumn;



    @FXML
    public void initialize(){
        listaStadiona = HelloController.listaStadiona;

        idStadionTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getId().toString()));
        imeStadionTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getImeStadiona()));
        brojSjedalaTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getBrojSjedala().toString()));
        lokacijaTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getLokacija()));


        ObservableList<Stadion> observableListStadion = FXCollections.observableList(listaStadiona);
        stadionTableView.setItems(observableListStadion);
    }


    @FXML
    public void onPretraziButtonClick(){
        String enteredIme= imeStadiona.getText();
        String enteredLokacija = lokacija.getText();


        List<Stadion> filtriranaListaStadiona = new ArrayList<>(listaStadiona);

        if(Optional.of(enteredIme).isEmpty() == false){
            filtriranaListaStadiona = filtriranaListaStadiona.stream().filter(s->s.getImeStadiona().toLowerCase().contains(enteredIme.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(enteredLokacija).isEmpty() == false){
            filtriranaListaStadiona = filtriranaListaStadiona.stream().filter(s->s.getLokacija().toLowerCase().contains(enteredLokacija.toLowerCase())).collect(Collectors.toList());
        }

        stadionTableView.setItems(FXCollections.observableList(filtriranaListaStadiona));

    }
}
