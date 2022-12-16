package ro.eminescusm.pm.mock.external.sample;

import ro.eminescusm.pm.mock.external.sample.RobotHardware;
import ro.eminescusm.pm.mock.external.util.ElapsedTime;
import ro.eminescusm.pm.mock.internal.opmode.OpMode;
import ro.eminescusm.pm.mock.internal.opmode.annotations.TeleOp;


@TeleOp(name = "BasicOpMode", group = "Basic")
public class BasicOpMode extends OpMode {
    private RobotHardware hardware;
    private ElapsedTime runtime = new ElapsedTime();

    int count = 0;
    int loopCount = 0;


    @Override
    public void init() {
        hardware = new RobotHardware(hardwareMap);
        hardware.init();
        runtime.reset();
    }

    @Override
    public void init_loop() {
        count++;
        telemetry.addData("Count", count);
        telemetry.addData("Loop count", loopCount);
        telemetry.addData("Runtime", runtime);
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
        telemetry.addData("Runtime", runtime);
    }

    @Override
    public void stop() {
        telemetry.addData("Count", count);
        telemetry.addData("Loop count", loopCount);
        telemetry.addData("Runtime", runtime);
    }
}
