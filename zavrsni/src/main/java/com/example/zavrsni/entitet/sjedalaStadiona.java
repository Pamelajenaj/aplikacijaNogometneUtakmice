package com.example.zavrsni.entitet;

import com.example.zavrsni.exceptioni.NepostojeceSjedalo;
import com.example.zavrsni.exceptioni.ZauzetoSjedalo;

public sealed interface sjedalaStadiona permits Stadion{

    void provjeraSjedala(String sjedalo) throws NepostojeceSjedalo;

    void zauzetostSjedala(String sjedalo) throws ZauzetoSjedalo;
}
