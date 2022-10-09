package ro.eminescusm.pm.mock.internal.impl;

import ro.eminescusm.pm.mock.internal.hardwareMap.HardwareMap;

import java.util.HashMap;

public class HardwareMapImpl implements HardwareMap {
    private final HashMap<String, Object> devices = new HashMap<>();

    public HardwareMapImpl() {
        devices.put("leftFront", new DcMotorImpl("leftFront"));
        devices.put("rightFront", new DcMotorImpl("rightFront"));
        devices.put("leftBack", new DcMotorImpl("leftBack"));
        devices.put("rightBack", new DcMotorImpl("rightBack"));
    }

    @Override
    public <T> T get(Class<? extends T> classOrInterface, String deviceName) {
        //Check if the device name exists in the hashmap

        if (!devices.containsValue(deviceName)) {
            return null;
        }

        if (devices.get(deviceName).getClass() != classOrInterface) return null;

        try {
            return (T) devices.get(deviceName);
        } catch (Exception e) {
            return null;
        }
    }
}
