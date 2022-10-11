package ro.eminescusm.pm.mock.internal.hardware.servo;

import ro.eminescusm.pm.mock.internal.hardware.HardwareDevice;

public interface Servo extends HardwareDevice {
    double MIN_POSITION = 0.0;
    double MAX_POSITION = 1.0;

    enum Direction {
        FORWARD,
        REVERSE
    }

    /**
     * Gets the current direction of the servo.
     *
     * @return the current direction of the servo
     */
    Direction getDirection();

    /**
     * Gets the current position of the servo.
     *
     * @return the current position of the servo
     */
    double getPosition();

    /**
     * Scales the available range of the servo to be a subset of the full range.
     *
     * @param min the minimum position of the servo
     * @param max the maximum position of the servo
     */
    void scaleRange(double min, double max);

    /**
     * Sets the direction of the servo.
     *
     * @param direction the direction of the servo
     */
    void setDirection(Direction direction);

    /**
     * Sets the position of the servo.
     *
     * @param position the position of the servo
     */
    void setPosition(double position);
}
