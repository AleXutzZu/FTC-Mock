package ro.eminescusm.pm.mock.internal.impl;

import ro.eminescusm.pm.mock.external.hardware.servo.Servo;

public class ServoImpl implements Servo {
    private final String deviceName;

    private Direction direction = Direction.FORWARD;
    private double position = 0;

    private double minPosition = 0;
    private double maxPosition = 1;

    ServoImpl(String deviceName) {
        this.deviceName = deviceName;
    }


    @Override
    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public double getPosition() {
        return position;
    }

    @Override
    public void scaleRange(double min, double max) {
        minPosition = min;
        maxPosition = max;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void setPosition(double position) {
        //Scale the position according to the min and max values
        this.position = (position - minPosition) / (maxPosition - minPosition);
    }
}
