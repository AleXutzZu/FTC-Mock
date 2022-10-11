package ro.eminescusm.pm.mock.external.opmode;

import com.sun.istack.internal.NotNull;
import ro.eminescusm.pm.mock.internal.hardwareMap.HardwareMap;
import ro.eminescusm.pm.mock.internal.opmode.OpMode;
import ro.eminescusm.pm.mock.internal.opmode.annotations.Disabled;

import java.lang.reflect.Field;

public class OpModeStarter {
    private final HardwareMap hardwareMap;

    OpModeStarter(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    /**
     * Schedules an op mode to be executed.
     * It will use the values of 1ms for the init loop and 1ms for the loop.
     *
     * @param opMode the op mode to be executed
     */
    public void schedule(@NotNull OpMode opMode) {
        schedule(opMode, 1, 3);
    }

    //TODO Rework input system

    /**
     * Schedules an op mode to be executed
     *
     * @param opMode       The op mode to be executed
     * @param initDuration The duration of the init phase (in milliseconds)
     * @param loopDuration The duration of the loop phase (in milliseconds)
     */
    public void schedule(@NotNull OpMode opMode, int initDuration, int loopDuration) {
        if (opMode.getClass().isAnnotationPresent(Disabled.class)) {
            System.out.println("OpMode is disabled");
            return;
        }
        System.out.println("Starting OpMode " + opMode.getName());

        internalOpModeSetup(opMode);

        opMode.init();
        //use the loop for initDuration seconds
        long initStartTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - initStartTime < initDuration) {
            opMode.init_loop();
        }
        opMode.start();
        //use the loop for loopDuration seconds
        long loopStartTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - loopStartTime < loopDuration) {
            opMode.loop();
        }
        opMode.stop();

        System.out.println("OpMode " + opMode.getName() + " finished");
    }

    private void internalOpModeSetup(@NotNull OpMode opMode) {


        try {
            for (Field field : OpMode.class.getDeclaredFields()) {
                if (field.getName().equals("hardwareMap")) {
                    field.setAccessible(true);
                    field.set(opMode, hardwareMap);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
