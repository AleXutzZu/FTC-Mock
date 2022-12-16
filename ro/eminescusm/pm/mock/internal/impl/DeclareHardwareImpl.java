package ro.eminescusm.pm.mock.internal.impl;

import ro.eminescusm.pm.mock.external.hardware.HardwareDevice;
import ro.eminescusm.pm.mock.external.opmode.HardwareMap;
import ro.eminescusm.pm.mock.external.util.OpModeStarter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeclareHardwareImpl implements DeclareHardware {
    private final Map<String, List<HardwareDevice>> allDevicesMap = new HashMap<>();

    DeclareHardwareImpl() {
    }

    public DeclareHardware addMotor(String motor) {
        //If a device with the name given already exists, add it to its list
        //otherwise create a new list and add it to the map
        if (allDevicesMap.containsKey(motor)) {
            allDevicesMap.get(motor).add(new DcMotorImpl(motor));
        } else {
            List<HardwareDevice> newList = new ArrayList<>();
            newList.add(new DcMotorImpl(motor));
            allDevicesMap.put(motor, newList);
        }
        return this;
    }

    public DeclareHardware addMotors(String... motors) {
        for (String motor : motors) {
            addMotor(motor);
        }
        return this;
    }

    public DeclareHardware addServo(String servo) {
        //If a device with the name given already exists, add it to its list
        //otherwise create a new list and add it to the map
        if (allDevicesMap.containsKey(servo)) {
            allDevicesMap.get(servo).add(new ServoImpl(servo));
        } else {
            List<HardwareDevice> newList = new ArrayList<>();
            newList.add(new ServoImpl(servo));
            allDevicesMap.put(servo, newList);
        }
        return this;
    }

    public DeclareHardware addServos(String... servos) {
        for (String servo : servos) {
            addServo(servo);
        }
        return this;
    }

    public DeclareHardware addDistanceSensor(String distanceSensor) {
        //If a device with the name given already exists, add it to its list
        //otherwise create a new list and add it to the map
        if (allDevicesMap.containsKey(distanceSensor)) {
            allDevicesMap.get(distanceSensor).add(new DistanceSensorImpl(distanceSensor));
        } else {
            List<HardwareDevice> newList = new ArrayList<>();
            newList.add(new DistanceSensorImpl(distanceSensor));
            allDevicesMap.put(distanceSensor, newList);
        }
        return this;
    }

    public DeclareHardware addDistanceSensors(String... distanceSensors) {
        for (String distanceSensor : distanceSensors) {
            addDistanceSensor(distanceSensor);
        }
        return this;
    }

    public OpModeStarter getStartOpMode() {
        HardwareMapImpl.Builder builder = new HardwareMapImpl.Builder();
        builder.setAllDevicesMap(allDevicesMap);
        HardwareMap hardwareMap = builder.build();
        return new OpModeStarterImpl(hardwareMap);
    }
}
