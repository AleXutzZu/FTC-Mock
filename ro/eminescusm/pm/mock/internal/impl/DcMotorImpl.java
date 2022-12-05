package ro.eminescusm.pm.mock.internal.impl;

import ro.eminescusm.pm.mock.external.hardware.motor.DcMotor;

public class DcMotorImpl implements DcMotor {
    private int currentPosition = 0;
    private int targetPosition = 0;
    private RunMode mode = RunMode.RUN_WITHOUT_ENCODER;
    private ZeroPowerBehavior zeroPowerBehavior = ZeroPowerBehavior.FLOAT;
    private Direction direction = Direction.FORWARD;
    private double power = 0;
    private final String deviceName;

    DcMotorImpl(String deviceName) {
        this.deviceName = deviceName;
    }


    @Override
    public int getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public void setTargetPosition(int position) {
        targetPosition = position;
    }

    @Override
    public RunMode getMode() {
        return mode;
    }

    @Override
    public void setMode(RunMode mode) {
        this.mode = mode;
    }

    @Override
    public void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior) {
        this.zeroPowerBehavior = zeroPowerBehavior;
    }

    @Override
    public ZeroPowerBehavior getZeroPowerBehavior() {
        return zeroPowerBehavior;
    }

    @Override
    public boolean isBusy() {
        return (currentPosition < targetPosition && power > 0) || (currentPosition > targetPosition && power < 0);
    }

    @Override
    public int getTargetPosition() {
        return targetPosition;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public double getPower() {
        return power;
    }

    @Override
    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }
}
