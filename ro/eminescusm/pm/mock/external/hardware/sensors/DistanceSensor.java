package ro.eminescusm.pm.mock.external.hardware.sensors;

import ro.eminescusm.pm.mock.external.hardware.HardwareDevice;
import ro.eminescusm.pm.mock.external.util.DistanceUnit;

public interface DistanceSensor extends HardwareDevice {
    /**
     * The value returned when a distance reading is not available.
     */
    double distanceOutOfRange = 1.7976931348623157E308;

    /**
     * Returns the current distance in the indicated distance units
     *
     * @param unit the distance unit to return the distance in
     * @return the current distance in the indicated distance units
     */
    double getDistance(DistanceUnit unit);
}

