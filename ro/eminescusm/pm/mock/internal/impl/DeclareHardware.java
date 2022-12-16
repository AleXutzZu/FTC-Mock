package ro.eminescusm.pm.mock.internal.impl;

import ro.eminescusm.pm.mock.external.util.OpModeStarter;

import java.lang.reflect.InvocationTargetException;

public interface DeclareHardware {

    /**
     * Adds a motor to the list of motors that will be used in the OpMode.
     *
     * @param motorName the name of the motor
     * @return the current DeclareHardware object
     */
    DeclareHardware addMotor(String motorName);

    /**
     * Adds multiple motors to the list of motors that will be used in the OpMode.
     *
     * @param motorNames the names of the motors
     * @return the current DeclareHardware object
     */
    DeclareHardware addMotors(String... motorNames);

    /**
     * Adds a servo to the list of servos that will be used in the OpMode.
     *
     * @param servoName the name of the servo
     * @return the current DeclareHardware object
     */
    DeclareHardware addServo(String servoName);

    /**
     * Adds multiple servos to the list of servos that will be used in the OpMode.
     *
     * @param servoNames the names of the servos
     * @return the current DeclareHardware object
     */
    DeclareHardware addServos(String... servoNames);

    /**
     * Adds a sensor to the list of sensors that will be used in the OpMode.
     *
     * @param distanceSensorName the name of the distance sensor
     * @return the current DeclareHardware object
     */
    DeclareHardware addDistanceSensor(String distanceSensorName);

    /**
     * Adds multiple sensors to the list of sensors that will be used in the OpMode.
     *
     * @param distanceSensorNames the names of the distance sensors
     * @return the current DeclareHardware object
     */
    DeclareHardware addDistanceSensors(String... distanceSensorNames);


    /**
     * Returns the OpModeStarter object that will be used to start the OpMode.
     *
     * @return the OpModeStarter object with the registered motors, servos and sensors
     */
    OpModeStarter getStartOpMode();

    /**
     * Creates a new DeclareHardware object.
     *
     * @return a new DeclareHardware object
     */
    static DeclareHardware getInstance() {
        //Use reflection to create a new instance of the DeclareHardwareImpl class

        try {
            return DeclareHardwareImpl.class.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
