package com.example.zavrsni;

import com.example.zavrsni.threadovi.SpremiPromjene;
import com.example.zavrsni.threadovi.UcitajPromjene;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PromjeneController {

    public static Boolean activeThread=false;
    private final static Object objekt=new Object();

    @FXML
    private TextArea promjene;

    public static List<String> promjeneUAplikaciji = new ArrayList<>();
    
    @FXML
    public void initialize(){

        SpremiPromjene prviThread = new SpremiPromjene();
        UcitajPromjene drugiThread = new UcitajPromjene();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(prviThread);
        executorService.execute(drugiThread);

        executorService.close();

        for(int i=0;i<promjeneUAplikaciji.size();i++){
            promjene.appendText(promjeneUAplikaciji.get(i)+"\n");
        }
    }


    public static synchronized void spremanjePromjena(){

        try {
            ObjectOutputStream pogreskeFile = new ObjectOutputStream(new FileOutputStream("dat/serijaliziranePogreske.dat"));

            pogreskeFile.writeObject(HelloController.svePromjene);

            pogreskeFile.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        activeThread=false;
        synchronized (objekt) {
            objekt.notifyAll();
        }
    }


    public static synchronized void ucitavanjePromjena(){
        if(activeThread.equals(true)) {
            synchronized (objekt) {
                try {
                    objekt.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        PromjeneController.promjeneUAplikaciji = HelloController.svePromjene;
    }


}
