package ro.eminescusm.pm.mock.external.opmode;

import ro.eminescusm.pm.mock.internal.hardware.HardwareDevice;
import ro.eminescusm.pm.mock.internal.hardwareMap.HardwareMap;
import ro.eminescusm.pm.mock.internal.impl.DcMotorImpl;
import ro.eminescusm.pm.mock.internal.impl.HardwareMapImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeclareHardware {
    private final Map<String, List<HardwareDevice>> allDevicesMap = new HashMap<>();


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

    public OpModeStarter getStartOpMode() {
        HardwareMapImpl.Builder builder = new HardwareMapImpl.Builder();
        builder.setAllDevicesMap(allDevicesMap);
        HardwareMap hardwareMap = builder.build();
        return new OpModeStarter(hardwareMap);
    }
}
