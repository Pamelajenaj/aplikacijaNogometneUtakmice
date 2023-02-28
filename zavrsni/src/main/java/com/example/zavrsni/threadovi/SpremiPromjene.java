package com.example.zavrsni.threadovi;

import com.example.zavrsni.PromjeneController;


public class SpremiPromjene implements Runnable{

    @Override
    public void run() {
        PromjeneController.activeThread=true;
        PromjeneController.spremanjePromjena();
    }
}
