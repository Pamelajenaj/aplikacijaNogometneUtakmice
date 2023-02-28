package com.example.zavrsni;

import com.example.zavrsni.entitet.Igrac;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class pretragaIgracaController {

    public List<Igrac> listaIgraca;
    @FXML
    private TextField imeIgraca;

    @FXML
    private TextField prezimeIgraca;

    @FXML
    private TextField oibIgraca;

    @FXML
    private DatePicker datumRodenjaIgraca;


    @FXML
    private TableView<Igrac> igracTableView;

    @FXML
    private TableColumn<Igrac, String> idIgracaTableColumn;

    @FXML
    private TableColumn<Igrac, String> imeIgracaTableColumn;

    @FXML
    private TableColumn<Igrac, String> prezimeIgracaTableColumn;

    @FXML
    private TableColumn<Igrac, String> oibIgracaTableColumn;

    @FXML
    private TableColumn<Igrac, String> datumRodenjaIgracaTableColumn;


    @FXML
    public void initialize(){
        listaIgraca = HelloController.listaIgraca;

        idIgracaTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getId().toString()));
        imeIgracaTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIme()));
        prezimeIgracaTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getPrezime()));
        oibIgracaTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getPrezime()));
        datumRodenjaIgracaTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDatumRodenja().toString()));

        ObservableList<Igrac> observableListIgrac = FXCollections.observableList(listaIgraca);
        igracTableView.setItems(observableListIgrac);
    }


    @FXML
    public void onPretraziButtonClick(){
        String enteredIme= imeIgraca.getText();
        String enteredPrezime = prezimeIgraca.getText();
        String enteredOib= oibIgraca.getText();
        LocalDate enteredDatum = datumRodenjaIgraca.getValue();

        List<Igrac> filtriranaListaIgraca = new ArrayList<>(listaIgraca);

        if(Optional.of(enteredIme).isEmpty() == false){
            filtriranaListaIgraca = filtriranaListaIgraca.stream().filter(s->s.getIme().toLowerCase().contains(enteredIme.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(enteredPrezime).isEmpty() == false){
            filtriranaListaIgraca = filtriranaListaIgraca.stream().filter(s->s.getPrezime().toLowerCase().contains(enteredPrezime.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(enteredOib).isEmpty() == false){
            filtriranaListaIgraca = filtriranaListaIgraca.stream().filter(s->s.getOib().toLowerCase().contains(enteredOib.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.ofNullable(enteredDatum).isPresent()){

            filtriranaListaIgraca = filtriranaListaIgraca.stream().filter(s->{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
                String formatedString = s.getDatumRodenja().format(formatter);

                return formatedString.equals(enteredDatum.toString());
            }).collect(Collectors.toList());
        }

        igracTableView.setItems(FXCollections.observableList(filtriranaListaIgraca));

    }

}
