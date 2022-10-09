package ro.eminescusm.pm.mock.external.opmode;

import com.sun.istack.internal.NotNull;
import ro.eminescusm.pm.mock.internal.hardwareMap.HardwareMap;
import ro.eminescusm.pm.mock.internal.motor.DcMotor;
import ro.eminescusm.pm.mock.internal.opmode.LinearOpMode;
import ro.eminescusm.pm.mock.internal.opmode.OpMode;
import ro.eminescusm.pm.mock.internal.opmode.annotations.Disabled;

import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        internalOpModeSetup(opMode);

        //LinearOpMode case
        if (opMode instanceof LinearOpMode) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            LinearOpMode linearOpMode = (LinearOpMode) opMode;
            Callable<Void> startOpMode = () -> {
                linearOpMode.runOpMode();
                return null;
            };

            Callable<Void> changeOpModeState = () -> {
                long initStartTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - initStartTime < initDuration) {
                    //Do nothing

                }
                for (Field field : linearOpMode.getClass().getSuperclass().getDeclaredFields()) {
                    if (field.getName().equals("isStarted")) {
                        field.setAccessible(true);
                        field.set(linearOpMode, true);
                    }
                }
                long loopStartTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - loopStartTime < loopDuration) {
                    //Do nothing

                }
                for (Field field : linearOpMode.getClass().getSuperclass().getDeclaredFields()) {
                    if (field.getName().equals("stopRequested")) {
                        field.setAccessible(true);
                        field.set(linearOpMode, true);
                        executorService.shutdown();
                    }
                }
                return null;
            };
            executorService.submit(startOpMode);
            executorService.submit(changeOpModeState);
        }//OpMode case
        else {
            //Wait for all other threads to finish
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
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
        }
    }

    private void internalOpModeSetup(@NotNull OpMode opMode) {
        try {
            for (Field field : opMode.getClass().getSuperclass().getSuperclass().getDeclaredFields()) {
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
