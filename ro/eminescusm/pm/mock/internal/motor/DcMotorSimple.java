package ro.eminescusm.pm.mock.internal.motor;

import ro.eminescusm.pm.mock.internal.hardware.HardwareDevice;

public interface DcMotorSimple extends HardwareDevice {
    /**
     * DC Motor direction
     */
    enum Direction {
        FORWARD,
        REVERSE
    }

    /**
     * Sets the direction of the motor.
     *
     * @param direction the direction to set
     */
    void setDirection(Direction direction);

    /**
     * Returns the direction of this motor.
     *
     * @return the direction of this motor
     */
    Direction getDirection();

    /**
     * Returns the current power level of the motor.
     */
    double getPower();

    /**
     * Sets the power level of the motor.
     *
     * @param power The power level to set. Valid values are in the range [-1, 1].
     */
    void setPower(double power);
}