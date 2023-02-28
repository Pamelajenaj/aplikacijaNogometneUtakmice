package com.example.zavrsni;

import com.example.zavrsni.entitet.*;
import com.example.zavrsni.exceptioni.PogresanUserPass;
import com.example.zavrsni.exceptioni.UsernameSadrziBrojke;
import com.example.zavrsni.util.Baza;
import com.example.zavrsni.util.Datoteke;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class HelloController {

    public static List<Igrac> listaIgraca = new ArrayList<>();
    public static List<Menadzer> listaMenadzera = new ArrayList<>();

    public static List<Stadion> listaStadiona = new ArrayList<>();

    public static List<Tim> listaTimova = new ArrayList<>();

    public static List<Utakmica> listaUtakmica = new ArrayList<>();

    public static List<Navijac> listaNavijaca = new ArrayList<>();

    public static List<Karta> listaKarata = new ArrayList<>();

    public static Navijac ulogiraniNavijac;

    public static List<String> svePromjene = new ArrayList<>();

    @FXML
    private TextField unosUsername;

    @FXML
    private TextField unosPassword;


    public void initialize() {
        try(Connection veza = Baza.connectToDatabase()) {

            System.out.println("Connected to database!");

            listaIgraca = Baza.dohvatiIgraceIzBaze(veza);
            listaMenadzera = Baza.dohvatiMenadzereIzBaze(veza);
            listaStadiona = Baza.dohvatiStadioneIzBaze(veza);
            listaTimova = Baza.dohvatiTimoveIzBaze(veza);
            listaUtakmica = Baza.dohvatiUtakmiceIzBaze(veza);

            listaNavijaca = Datoteke.ucitajNavijace();
            listaKarata = Datoteke.ucitajKarte();

            svePromjene = Datoteke.ucitajPromjene();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void prijavaClick() throws IOException {
        String username = unosUsername.getText();
        String password = unosPassword.getText();

        StringBuilder pogreske = new StringBuilder();

        try {
            provjeraUsernamea(username);
        } catch (UsernameSadrziBrojke e) {
            pogreske.append(e.getMessage());
        }

        boolean uspjesnostLogina = false;

        try {
            uspjesnostLogina = provjeraUSernameaIPassworda(username, password);
        }catch (PogresanUserPass e){
            pogreske.append(e.getMessage());
        }


        if(uspjesnostLogina == true){
            System.out.println("Uspješan login!");

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pretragaIgraca.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600,400);
            HelloApplication.getMainStage().setTitle("Hello!");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska kod logina");
            alert.setHeaderText("Pogreška");
            alert.setContentText(pogreske.toString());

            alert.showAndWait();
        }

    }

    private boolean provjeraUSernameaIPassworda(String username, String password) throws PogresanUserPass{
        Boolean provjeraLogina = false;

        String hashPassword ="";

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes());
            hashPassword = Base64.getEncoder().encodeToString(hash);


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


        for(int i=0;i<listaNavijaca.size();i++){
            if(listaNavijaca.get(i).getUsername().equals(username)){
                if(listaNavijaca.get(i).getPassword().equals(hashPassword)){
                    provjeraLogina = true;
                    ulogiraniNavijac = listaNavijaca.get(i);
                }
            }
        }

        if(provjeraLogina == false){
            throw new PogresanUserPass("Unijeli ste pogresan username ili password!\n");
        }

        return provjeraLogina;
    }


    private void provjeraUsernamea(String username) throws UsernameSadrziBrojke {
        char[] razdvojenaSlova = username.toCharArray();

        for(char c: razdvojenaSlova){
            if(Character.isDigit(c)){
                throw new UsernameSadrziBrojke("Username ne smije sadrzavati brojke!\n");
            }
        }

    }

}