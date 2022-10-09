package org.example.opmode;

import ro.eminescusm.pm.mock.external.RobotHardware;
import ro.eminescusm.pm.mock.internal.opmode.LinearOpMode;
import ro.eminescusm.pm.mock.internal.opmode.annotations.TeleOp;

@TeleOp(name = "BasicLinearOpMode", group = "Basic")
public class BasicLinearOpMode extends LinearOpMode {
    private RobotHardware robotHardware;
    @Override
    public void runOpMode() throws InterruptedException {
        System.out.println("BasicLinearOpMode.runOpMode()");
        System.out.println("BasicLinearOpMode init phase");

        robotHardware = new RobotHardware(hardwareMap);
        robotHardware.init();


        waitForStart();

        while (opModeIsActive()) {

        }
        System.out.println("BasicLinearOpMode stop phase");
        throw new InterruptedException();
    }
}
