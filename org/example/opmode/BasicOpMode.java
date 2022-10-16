package org.example.opmode;

import ro.eminescusm.pm.mock.external.sample.RobotHardware;
import ro.eminescusm.pm.mock.internal.opmode.OpMode;
import ro.eminescusm.pm.mock.internal.opmode.annotations.TeleOp;
import ro.eminescusm.pm.mock.internal.telemetry.Telemetry;


@TeleOp(name = "BasicOpMode", group = "Basic")
public class BasicOpMode extends OpMode {
    private RobotHardware hardware;


    int count = 0;
    int loopCount = 0;


    @Override
    public void init() {
        hardware = new RobotHardware(hardwareMap);
        hardware.init();
        telemetry.clearAll();
    }

    @Override
    public void init_loop() {
        count++;
        telemetry.addData("Count", count);
        telemetry.addData("Loop count", loopCount);
    }

    @Override
    public void start() {
        loopCount++;
    }

    @Override
    public void loop() {
        loopCount++;
        telemetry.addData("Count", count);
        telemetry.addData("Loop count", loopCount);
    }

    @Override
    public void stop() {

    }
}
