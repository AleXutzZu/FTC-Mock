package ro.eminescusm.pm.mock.internal.impl;

import ro.eminescusm.pm.mock.external.hardware.sensors.DistanceSensor;
import ro.eminescusm.pm.mock.external.util.DistanceUnit;

public class DistanceSensorImpl implements DistanceSensor {

    private final String deviceName;

    DistanceSensorImpl(String deviceName) {
        this.deviceName = deviceName;
    }


    @Override
    public String getDeviceName() {
        return null;
    }

    @Override
    public double getDistance(DistanceUnit unit) {
        return 0;
    }
}
