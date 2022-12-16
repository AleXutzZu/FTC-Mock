package ro.eminescusm.pm.mock.external.sample;

import ro.eminescusm.pm.mock.external.util.ElapsedTime;
import ro.eminescusm.pm.mock.internal.opmode.OpMode;
import ro.eminescusm.pm.mock.internal.opmode.annotations.TeleOp;

/**
 * This file contains an example of an OpMode that prints the runtime
 * to the telemetry.
 */
@TeleOp(name = "Print Runtime", group = "Sample")
public class PrintRuntimeOpMode extends OpMode {
    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
        runtime.reset();
        telemetry.addData("Runtime", runtime);
    }

    @Override
    public void init_loop() {
        telemetry.addData("Runtime", runtime);
    }

    @Override
    public void start() {
        telemetry.addData("Runtime", runtime);
    }

    @Override
    public void loop() {
        telemetry.addData("Runtime", runtime);
    }

    @Override
    public void stop() {
        telemetry.addData("Runtime", runtime);
    }
}
