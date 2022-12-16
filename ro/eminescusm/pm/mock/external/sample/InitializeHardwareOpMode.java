package ro.eminescusm.pm.mock.external.sample;

import ro.eminescusm.pm.mock.internal.opmode.OpMode;
import ro.eminescusm.pm.mock.internal.opmode.annotations.TeleOp;

@TeleOp(name = "Initialize Hardware", group = "Sample")
public class InitializeHardwareOpMode extends OpMode {
    private RobotHardware hardware;

    @Override
    public void init() {
        hardware = new RobotHardware(hardwareMap);
        hardware.init();
    }

    @Override
    public void loop() {
        telemetry.addData("Motor", hardware.getLeftServo().getDeviceName());
    }
}
