package me.alexutzzu.mock.internal.impl;

import me.alexutzzu.mock.internal.hardwareMap.HardwareMap;

import java.util.HashMap;

public class HardwareMapImpl implements HardwareMap {
    private final HashMap<String, Object> devices = new HashMap<>();

    public HardwareMapImpl() {
        devices.put("leftFront", new me.alexutzzu.mock.internal.impl.DcMotorImpl("leftFront"));
        devices.put("rightFront", new me.alexutzzu.mock.internal.impl.DcMotorImpl("rightFront"));
        devices.put("leftBack", new me.alexutzzu.mock.internal.impl.DcMotorImpl("leftBack"));
        devices.put("rightBack", new me.alexutzzu.mock.internal.impl.DcMotorImpl("rightBack"));
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
