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

    Telemetry.Line line1;
    Telemetry.Item item1;
    Telemetry.Item item2;

    @Override
    public void init() {
        hardware = new RobotHardware(hardwareMap);
        hardware.init();
        telemetry.clearAll();
        line1 = telemetry.addLine("Hello World!");
        item1 = line1.addData("count", count);
        item2 = line1.addData("loopCount", loopCount);
    }

    @Override
    public void init_loop() {
        count++;
        item1.setValue(count);
    }

    @Override
    public void start() {
        loopCount++;
    }

    @Override
    public void loop() {
        loopCount++;
        item2.setValue(loopCount);
    }

    @Override
    public void stop() {
        item2.setValue(0);
    }
}
