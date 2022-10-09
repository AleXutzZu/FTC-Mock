package ro.eminescusm.pm.mock.internal.opmode;

public abstract class LinearOpMode extends OpMode {
    private boolean isStarted = false;
    private boolean stopRequested = false;

    public abstract void runOpMode() throws InterruptedException;


    public final boolean isStarted() {
        return isStarted;
    }

    public void waitForStart() {
        while (!isStarted()) {
            // wait
        }
    }

    public final void idle() {
        Thread.yield();
    }

    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public final boolean opModeIsActive() {
        boolean isActive = !this.isStopRequested() && this.isStarted();
        if (isActive) idle();
        return isActive;
    }

    public final boolean isStopRequested() {
        return this.stopRequested;
    }

    public final boolean opModeInInit() {
        return !isStarted() && !isStopRequested();
    }

    @Override
    public final void init() {
        // Do nothing
    }

    @Override
    public final void loop() {
        // Do nothing
    }

    @Override
    public final void start() {
        try {
            runOpMode();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
