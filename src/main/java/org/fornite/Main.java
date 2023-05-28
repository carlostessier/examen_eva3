package org.fornite;

import org.fornite.entidades.Arma;
import org.fornite.entidades.Jugador;

public class Main {
    public static void main(String[] args) {
        Jugador jugador = new Jugador("Test", 100, 50,0);
        Arma arma = new Arma("Subfusil aguijon",30,12);

        System.out.println(arma);
    }
}