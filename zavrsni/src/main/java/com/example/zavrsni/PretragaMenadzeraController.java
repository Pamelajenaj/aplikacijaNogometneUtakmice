package com.example.zavrsni;

import com.example.zavrsni.entitet.Igrac;
import com.example.zavrsni.entitet.Menadzer;
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

public class PretragaMenadzeraController {

    public List<Menadzer> listaMenadzera;
    @FXML
    private TextField imeMenadzera;

    @FXML
    private TextField prezimeMenadzera;

    @FXML
    private TextField oibMenadzera;

    @FXML
    private DatePicker datumRodenjaMenadzera;


    @FXML
    private TableView<Menadzer> menadzerTableView;

    @FXML
    private TableColumn<Menadzer, String> idMenadzeraTableColumn;

    @FXML
    private TableColumn<Menadzer, String> imeMenadzeraTableColumn;

    @FXML
    private TableColumn<Menadzer, String> prezimeMenadzeraTableColumn;

    @FXML
    private TableColumn<Menadzer, String> oibMenadzeraTableColumn;

    @FXML
    private TableColumn<Menadzer, String> datumRodenjaMenadzeraTableColumn;


    @FXML
    public void initialize(){
        listaMenadzera = HelloController.listaMenadzera;

        idMenadzeraTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getId().toString()));
        imeMenadzeraTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIme()));
        prezimeMenadzeraTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getPrezime()));
        oibMenadzeraTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getOib()));
        datumRodenjaMenadzeraTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDatumRodenja().toString()));

        ObservableList<Menadzer> observableListMenadzer = FXCollections.observableList(listaMenadzera);
        menadzerTableView.setItems(observableListMenadzer);
    }


    @FXML
    public void onPretraziButtonClick(){
        String enteredIme= imeMenadzera.getText();
        String enteredPrezime = prezimeMenadzera.getText();
        String enteredOib= oibMenadzera.getText();
        LocalDate enteredDatum = datumRodenjaMenadzera.getValue();

        List<Menadzer> filtriranaListaMenadzera = new ArrayList<>(listaMenadzera);

        if(Optional.of(enteredIme).isEmpty() == false){
            filtriranaListaMenadzera = filtriranaListaMenadzera.stream().filter(s->s.getIme().toLowerCase().contains(enteredIme.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(enteredPrezime).isEmpty() == false){
            filtriranaListaMenadzera = filtriranaListaMenadzera.stream().filter(s->s.getPrezime().toLowerCase().contains(enteredPrezime.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(enteredOib).isEmpty() == false){
            filtriranaListaMenadzera = filtriranaListaMenadzera.stream().filter(s->s.getOib().toLowerCase().contains(enteredOib.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.ofNullable(enteredDatum).isPresent()){

            filtriranaListaMenadzera = filtriranaListaMenadzera.stream().filter(s->{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
                String formatedString = s.getDatumRodenja().format(formatter);

                return formatedString.equals(enteredDatum.toString());
            }).collect(Collectors.toList());
        }

        menadzerTableView.setItems(FXCollections.observableList(filtriranaListaMenadzera));

    }
}
