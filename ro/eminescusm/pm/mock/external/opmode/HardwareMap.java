package ro.eminescusm.pm.mock.external.opmode;

import com.sun.istack.internal.Nullable;

public interface HardwareMap {

    /**
     * Retrieves the device with the indicated name which is also an instance of the indicated class.
     *
     * @param classOrInterface the class or interface of the device
     * @param deviceName       the name of the device
     * @param <T>              the type of the device
     * @return the device
     */
    @Nullable
    <T> T get(Class<? extends T> classOrInterface, String deviceName);
}
