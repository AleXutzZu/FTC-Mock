package me.alexutzzu.mock.internal.opmode;

import me.alexutzzu.mock.internal.gamepad.Gamepad;
import me.alexutzzu.mock.internal.hardwareMap.HardwareMap;
import me.alexutzzu.mock.internal.impl.HardwareMapImpl;
import me.alexutzzu.mock.internal.impl.TelemetryImpl;
import me.alexutzzu.mock.internal.opmode.annotations.Autonomous;
import me.alexutzzu.mock.internal.opmode.annotations.TeleOp;
import me.alexutzzu.mock.internal.telemetry.Telemetry;

public abstract class OpMode {
    /**
     * The hardware map for the op mode
     */
    protected final HardwareMap hardwareMap = new HardwareMapImpl();

    /**
     * Gamepad 1
     */
    protected final Gamepad gamepad1 = null;

    /**
     * Gamepad 2
     */
    protected final Gamepad gamepad2 = null;

    protected final int msStuckDetectInit = 10000;
    protected final int msStuckDetectInitLoop = 10000;
    protected final int msStuckDetectStart = 10000;
    protected final int msStuckDetectLoop = 10000;

    /**
     * The telemetry for the op mode
     */
    protected final Telemetry telemetry = new TelemetryImpl();

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
}
