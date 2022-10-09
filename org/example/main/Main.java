package org.example.main;

import ro.eminescusm.pm.mock.external.opmode.StartOpMode;
import org.example.opmode.BasicOpMode;

public class Main {

    public static void main(String[] args) {
        StartOpMode startOpMode = new StartOpMode(new BasicOpMode());
        startOpMode.start();
    }
}