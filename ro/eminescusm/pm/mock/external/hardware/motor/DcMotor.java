package ro.eminescusm.pm.mock.external.hardware.motor;

public interface DcMotor extends DcMotorSimple {
    enum RunMode {
        RUN_WITHOUT_ENCODER,
        RUN_USING_ENCODER,
        RUN_TO_POSITION,
        STOP_AND_RESET_ENCODER
    }

    enum ZeroPowerBehavior {
        BRAKE,
        FLOAT
    }

    /**
     * Gets the mode of the motor.
     * @return
     */
    RunMode getMode();

    /**
     * Sets the mode of the motor.
     * @param mode the mode to set
     */
    void setMode(RunMode mode);

    /**
     * Sets the zero power behavior of the motor.
     * @param zeroPowerBehavior the zero power behavior to set
     */
    void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior);

    /**
     * Gets the zero power behavior of the motor.
     * @return the zero power behavior of the motor
     */
    ZeroPowerBehavior getZeroPowerBehavior();

    /**
     * Returns true if the motor is busy.
     * @return true if the motor is busy
     */
    boolean isBusy();
}
