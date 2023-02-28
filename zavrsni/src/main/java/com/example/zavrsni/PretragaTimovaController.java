package com.example.zavrsni;

import com.example.zavrsni.entitet.Igrac;
import com.example.zavrsni.entitet.Stadion;
import com.example.zavrsni.entitet.Tim;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PretragaTimovaController {

    public List<Tim> listaTimova;
    @FXML
    private TextField imeTima;

    @FXML
    private TextField uneseniMenadzer;

    @FXML
    private TextField uneseniIgrac;

    @FXML
    private TableView<Tim> timTableView;

    @FXML
    private TableColumn<Tim, String> idTimaTableColumn;

    @FXML
    private TableColumn<Tim, String> imeTimaTableColumn;

    @FXML
    private TableColumn<Tim, String> menadzerTableColumn;

    @FXML
    private TableColumn<Tim, String> igraciTableColumn;



    @FXML
    public void initialize(){
        listaTimova = HelloController.listaTimova;

        idTimaTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getId().toString()));
        imeTimaTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getImeTima()));
        menadzerTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getMenadzer().getIme()+" "+c.getValue().getMenadzer().getPrezime()));
        igraciTableColumn.setCellValueFactory(c->{

            Set<Igrac> igraci = c.getValue().getListaIgraca();
            StringBuilder sb = new StringBuilder();
            List<Igrac>  listaIgraca = igraci.stream().toList();

            for(int i=0;i<listaIgraca.size();i++){
                sb.append(listaIgraca.get(i).getIme()+" "+listaIgraca.get(i).getPrezime()+", ");
            }


            return new SimpleStringProperty(sb.toString());
        });


        ObservableList<Tim> observableListTim = FXCollections.observableList(listaTimova);
        timTableView.setItems(observableListTim);
    }


    @FXML
    public void onPretraziButtonClick(){
        String enteredIme= imeTima.getText();
        String enteredMenadzer = uneseniMenadzer.getText();
        String enteredIgrac = uneseniIgrac.getText();

        List<Tim> filtriranaListaTimova = new ArrayList<>(listaTimova);

        if(Optional.of(enteredIme).isEmpty() == false){
            filtriranaListaTimova = filtriranaListaTimova.stream().filter(s->s.getImeTima().toLowerCase().contains(enteredIme.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(enteredMenadzer).isEmpty() == false){
            filtriranaListaTimova = filtriranaListaTimova.stream().filter(s->s.getMenadzer().getIme().toLowerCase().contains(enteredMenadzer.toLowerCase()) || s.getMenadzer().getPrezime().toLowerCase().contains(enteredMenadzer.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.of(enteredIgrac).isEmpty() == false){
            filtriranaListaTimova = filtriranaListaTimova.stream().filter(s->{
                Set<Igrac> igraciSet = s.getListaIgraca();
                List<Igrac> igraci = igraciSet.stream().toList();
                StringBuilder sb = new StringBuilder();

                for(int i=0;i<igraci.size();i++){
                    sb.append(igraci.get(i).getIme().toLowerCase()+" "+igraci.get(i).getPrezime().toLowerCase()+" ");
                }

                return sb.toString().contains(enteredIgrac.toLowerCase());
            }).collect(Collectors.toList());
        }

        timTableView.setItems(FXCollections.observableList(filtriranaListaTimova));

    }

}
