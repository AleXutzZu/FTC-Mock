package ro.eminescusm.pm.mock.external.util;

import com.sun.istack.internal.NotNull;
import ro.eminescusm.pm.mock.external.opmode.HardwareMap;
import ro.eminescusm.pm.mock.internal.impl.TelemetryImpl;
import ro.eminescusm.pm.mock.internal.opmode.OpMode;
import ro.eminescusm.pm.mock.internal.opmode.annotations.Disabled;
import ro.eminescusm.pm.mock.internal.telemetry.Telemetry;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OpModeStarter {
    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;
    private List<String> lastDisplay;

    OpModeStarter(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        telemetry = new TelemetryImpl();
    }

    /**
     * Schedules an op mode to be executed.
     * It will use the values of 1000ms for the init loop and 1000ms for the loop.
     *
     * @param opMode the op mode to be executed
     */
    public void schedule(@NotNull OpMode opMode) {
        schedule(opMode, 1000, 1000);
    }

    /**
     * Schedules an op mode to be executed
     *
     * @param opMode       The op mode to be executed
     * @param initDuration The duration of the init loop phase (in milliseconds)
     * @param loopDuration The duration of the loop phase (in milliseconds)
     */
    public void schedule(@NotNull OpMode opMode, int initDuration, int loopDuration) {
        if (opMode.getClass().isAnnotationPresent(Disabled.class)) {
            System.out.println("OpMode is disabled");
            return;
        }
        internalOpModeSetup(opMode);
        telemetry.addLine("Initializing...");

        internalAutoUpdateTelemetry(opMode);


        opMode.init();
        internalAutoUpdateTelemetry(opMode);

        //use the loop for initDuration seconds
        long initStartTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - initStartTime < initDuration) {
            opMode.init_loop();

            internalAutoUpdateTelemetry(opMode);
        }

        opMode.start();
        internalAutoUpdateTelemetry(opMode);

        //use the loop for loopDuration seconds
        long loopStartTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - loopStartTime < loopDuration) {
            opMode.loop();

            internalAutoUpdateTelemetry(opMode);
        }
        opMode.stop();
//        internalAutoUpdateTelemetry(opMode);

        System.out.println("OpMode " + opMode.getName() + " finished");
    }

    private void internalOpModeSetup(@NotNull OpMode opMode) {
        try {
            for (Field field : OpMode.class.getDeclaredFields()) {
                if (field.getName().equals("hardwareMap")) {
                    field.setAccessible(true);
                    field.set(opMode, hardwareMap);
                }
                if (field.getName().equals("telemetry")) {
                    field.setAccessible(true);
                    field.set(opMode, telemetry);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void internalAutoUpdateTelemetry(@NotNull OpMode opMode) {
        //Wait
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        List<String> linesToDisplay = new ArrayList<>();

        Method telemetryMethod = null;
        for (Method method : OpMode.class.getDeclaredMethods()) {
            if (method.getName().equals("internalUpdateTelemetry")) {
                telemetryMethod = method;
                break;
            }
        }
        //should never happen
        if (telemetryMethod == null) {
            throw new RuntimeException("Could not find internalUpdateTelemetry() method");
        }

        Boolean update = null;

        try {
            telemetryMethod.setAccessible(true);
            update = (Boolean) telemetryMethod.invoke(opMode);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        if (Boolean.FALSE.equals(update)) {
            linesToDisplay = lastDisplay;
        } else if (Boolean.TRUE.equals(update)) {
            Method getSerializedLines = null;
            for (Method method : TelemetryImpl.class.getDeclaredMethods()) {
                if (method.getName().equals("getSerializedLines")) {
                    getSerializedLines = method;
                    break;
                }
            }
            //should never happen
            if (getSerializedLines == null) {
                throw new RuntimeException("Could not find getSerializedLines() method");
            }

            try {
                getSerializedLines.setAccessible(true);
                linesToDisplay = (List<String>) getSerializedLines.invoke(telemetry);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            lastDisplay = linesToDisplay;
        }
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
        linesToDisplay.forEach(System.out::println);
    }
}
