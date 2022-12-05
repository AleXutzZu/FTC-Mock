package ro.eminescusm.pm.mock.external.util;

import ro.eminescusm.pm.mock.internal.opmode.OpMode;

public interface OpModeStarter {
    /**
     * Schedules an OpMode to be run. It will use the 1000ms for the init loop and 1000ms for the loop.
     *
     * @param opMode the OpMode to be run
     */
    void schedule(OpMode opMode);

    /**
     * Schedules an OpMode to be run.
     * @param opMode the OpMode to be run
     * @param initDuration the duration of the init loop phase (in milliseconds)
     * @param loopDuration the duration of the loop phase (in milliseconds)
     */
    void schedule(OpMode opMode, int initDuration, int loopDuration);
}
