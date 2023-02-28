package com.example.zavrsni.entitet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poruke<T extends Osoba,S extends String> {

    public static Map<Osoba, List<String>> porukeOdOsobe = new HashMap<>();

    public void dodajPoruku(T osoba, S poruka){
        List<String> dosadasnjePoruke = porukeOdOsobe.get(osoba);
        dosadasnjePoruke.add(poruka);
        porukeOdOsobe.put(osoba,dosadasnjePoruke);
    }



}
