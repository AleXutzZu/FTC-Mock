package ro.eminescusm.pm.mock.internal.impl;

import com.sun.istack.internal.Nullable;
import ro.eminescusm.pm.mock.internal.hardware.HardwareDevice;
import ro.eminescusm.pm.mock.internal.hardwareMap.HardwareMap;
import ro.eminescusm.pm.mock.internal.motor.DcMotor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HardwareMapImpl implements HardwareMap {

    protected final Map<String, List<HardwareDevice>> allDevicesMap;

    private HardwareMapImpl(Map<String, List<HardwareDevice>> allDevicesMap) {
        this.allDevicesMap = allDevicesMap;
    }

    @Override
    public @Nullable <T> T get(Class<? extends T> classOrInterface, String deviceName) {
        List<HardwareDevice> deviceList = allDevicesMap.get(deviceName);
        T result = null;
        if (deviceList != null) {
            for (HardwareDevice device : deviceList) {
                if (classOrInterface.isInstance(device)) {
                    result = classOrInterface.cast(device);
                    break;
                }
            }
        }
        return result;
    }

    public static class Builder {
        public void setAllDevicesMap(Map<String, List<HardwareDevice>> allDevicesMap) {
            this.allDevicesMap = allDevicesMap;
        }

        private Map<String, List<HardwareDevice>> allDevicesMap = new HashMap<>();

        public HardwareMap build() {
            return new HardwareMapImpl(allDevicesMap);
        }
    }
}
