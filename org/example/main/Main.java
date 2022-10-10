package org.example.main;

import org.example.opmode.BasicOpMode;
import ro.eminescusm.pm.mock.external.opmode.DeclareHardware;
import ro.eminescusm.pm.mock.external.opmode.OpModeStarter;

public class Main {

    public static void main(String[] args) {
        DeclareHardware declareHardware = new DeclareHardware();
        declareHardware.addMotors("leftFront", "rightFront", "leftBack", "rightBack");
        OpModeStarter opModeStarter = declareHardware.getStartOpMode();

        opModeStarter.schedule(new BasicOpMode());
    }
}