package com.example.zavrsni;

import com.example.zavrsni.entitet.Igrac;
import com.example.zavrsni.entitet.Menadzer;
import com.example.zavrsni.entitet.Tim;
import com.example.zavrsni.entitet.Utakmica;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PretragaUtakmicaController {

    public List<Utakmica> listaUtakmica;

    public static Utakmica odabranaUtakmica;

    @FXML
    private TableView<Utakmica> utakmicaTableView;

    @FXML
    private TableColumn<Utakmica, String> idUtakmiceTableColumn;

    @FXML
    private TableColumn<Utakmica, String> timoviTableColumn;

    @FXML
    private TableColumn<Utakmica, String> stadionTableColumn;

    @FXML
    private TableColumn<Utakmica, String> datumIVrijemeTableColumn;



    @FXML
    public void initialize(){
        listaUtakmica = HelloController.listaUtakmica;

        idUtakmiceTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getId().toString()));
        timoviTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getPrviTim().getImeTima()+" VS "+c.getValue().getDrugiTim().getImeTima()));
        stadionTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getStadion().getImeStadiona()));
        datumIVrijemeTableColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDatumIVrijemeUtakmice().toString()));

        ObservableList<Utakmica> observableListUtakmica = FXCollections.observableList(listaUtakmica);
        utakmicaTableView.setItems(observableListUtakmica);
    }


    @FXML
    public void onKupiButtonClick() throws IOException {
        int selectedIndex = utakmicaTableView.getSelectionModel().getFocusedIndex();

        odabranaUtakmica = listaUtakmica.get(selectedIndex);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("kupnjaKarata.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Hello!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

}
