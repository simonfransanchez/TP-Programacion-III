package org.example;

import naves.Nave;
import naves.NaveFactory;

public class Main {

    public static void main(String[] args) {

        NaveFactory naveFactory = new NaveFactory();
        Nave nave1 = naveFactory.getNave("Exploradora");
    }
}