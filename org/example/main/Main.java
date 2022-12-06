package org.example.main;

import org.example.opmode.BasicOpMode;
import ro.eminescusm.pm.mock.internal.impl.DeclareHardware;
import ro.eminescusm.pm.mock.external.util.OpModeStarter;

public class Main {

    public static void main(String[] args) {
        DeclareHardware declareHardware = DeclareHardware.getInstance();
        declareHardware.addMotors("leftFront", "rightFront", "leftBack", "rightBack");
        OpModeStarter opModeStarter = declareHardware.getStartOpMode();

        opModeStarter.schedule(new BasicOpMode());
    }
}