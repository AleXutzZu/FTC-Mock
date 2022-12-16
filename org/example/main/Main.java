package org.example.main;

import ro.eminescusm.pm.mock.external.sample.BasicOpMode;
import ro.eminescusm.pm.mock.external.sample.InitializeHardwareOpMode;
import ro.eminescusm.pm.mock.internal.impl.DeclareHardware;
import ro.eminescusm.pm.mock.external.util.OpModeStarter;

public class Main {

    public static void main(String[] args) {
        DeclareHardware declareHardware = DeclareHardware.getInstance();
        declareHardware.addMotors("leftFront", "rightFront", "leftBack", "rightBack");
        declareHardware.addServos("leftServo", "rightServo");
        declareHardware.addDistanceSensors("rearDistanceSensor", "frontDistanceSensor");
        OpModeStarter opModeStarter = declareHardware.getStartOpMode();

        opModeStarter.schedule(new InitializeHardwareOpMode());
    }
}