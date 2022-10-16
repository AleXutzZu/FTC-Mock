package ro.eminescusm.pm.mock.internal.opmode;

import ro.eminescusm.pm.mock.internal.gamepad.Gamepad;
import ro.eminescusm.pm.mock.external.opmode.HardwareMap;
import ro.eminescusm.pm.mock.internal.opmode.annotations.Autonomous;
import ro.eminescusm.pm.mock.internal.opmode.annotations.TeleOp;
import ro.eminescusm.pm.mock.internal.telemetry.Telemetry;

public abstract class OpMode {
    /**
     * The hardware map for the op mode
     */
    protected HardwareMap hardwareMap = null;

    /**
     * Gamepad 1
     */
    protected Gamepad gamepad1 = null;

    /**
     * Gamepad 2
     */
    protected Gamepad gamepad2 = null;

    /**
     * The telemetry for the op mode
     */
    public Telemetry telemetry = null;

    /**
     * Number of seconds this op mode has been running
     */
    protected double time = 0f;

    protected OpMode() {
        if (this.getClass().isAnnotationPresent(Autonomous.class) && this.getClass().isAnnotationPresent(TeleOp.class)) {
            throw new IllegalArgumentException("OpMode cannot be both Autonomous and TeleOp");
        }
        if (!this.getClass().isAnnotationPresent(Autonomous.class) && !this.getClass().isAnnotationPresent(TeleOp.class)) {
            throw new IllegalArgumentException("OpMode must be either Autonomous or TeleOp");
        }
    }

    /**
     * Logic on initialization of the OpMode.
     * Will be executed once when the OpMode is initialized.
     */
    public abstract void init();


    /**
     * Logic on initialization of the OpMode.
     * Will be executed iteratively when the OpMode is initialized, after the init() method.
     */
    public void init_loop() {

    }

    /**
     * Logic on start of the OpMode.
     * Will be executed once when the OpMode is started.
     */
    public void start() {

    }

    /**
     * Iterative logic of the OpMode.
     * Will be executed iteratively until the stop button is pressed.
     */
    public abstract void loop();

    /**
     * Logic on stop of the OpMode.
     * Will be executed once when the OpMode is stopped.
     */
    public void stop() {

    }

    public String getName() {
        if (this.getClass().isAnnotationPresent(Autonomous.class)) {
            return this.getClass().getAnnotation(Autonomous.class).name();
        } else if (this.getClass().isAnnotationPresent(TeleOp.class)) {
            return this.getClass().getAnnotation(TeleOp.class).name();
        } else {
            return null;
        }
    }

    /**
     * Updates the telemetry. Internal use only.
     */
    private void internalUpdateTelemetry() {
        telemetry.update();
    }
}
