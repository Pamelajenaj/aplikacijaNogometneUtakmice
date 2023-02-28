package com.example.zavrsni;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuBarController {

    @FXML
    public void showIgracSearchScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pretragaIgraca.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Hello!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    @FXML
    public void showMenadzerSearchScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pretragaMenadzera.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Hello!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    @FXML
    public void showStadionSearchScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pretragaStadiona.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Hello!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    @FXML
    public void showTimSearchScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pretragaTimova.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Hello!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    @FXML
    public void showUtakmicaSearchScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pretragaUtakmica.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Hello!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    @FXML
    public void showMojeKarteSearchScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pretragaKarata.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Hello!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    @FXML
    public void showPromjeneSearchScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("promjene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Hello!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

}
