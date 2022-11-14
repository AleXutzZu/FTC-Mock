package ro.eminescusm.pm.mock.external.hardware.sensors;

import ro.eminescusm.pm.mock.external.hardware.HardwareDevice;

public interface DistanceSensor extends HardwareDevice {
    double distanceOutOfRange = 0;
    double getDistance();
}

