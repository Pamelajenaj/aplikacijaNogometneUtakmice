package com.example.zavrsni.threadovi;

import com.example.zavrsni.PromjeneController;


public class UcitajPromjene implements Runnable{

    @Override
    public void run(){
        PromjeneController.ucitavanjePromjena();
    }

}
