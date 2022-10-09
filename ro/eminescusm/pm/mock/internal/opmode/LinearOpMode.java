package ro.eminescusm.pm.mock.internal.opmode;

public abstract class LinearOpMode extends OpMode {

    public abstract void runOpMode() throws InterruptedException;

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
