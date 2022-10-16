package ro.eminescusm.pm.mock.internal.impl;

import ro.eminescusm.pm.mock.external.hardware.servo.Servo;

public class ServoImpl implements Servo {
    @Override
    public String getDeviceName() {
        return null;
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public double getPosition() {
        return 0;
    }

    @Override
    public void scaleRange(double min, double max) {

    }

    @Override
    public void setDirection(Direction direction) {

    }

    @Override
    public void setPosition(double position) {

    }
}
