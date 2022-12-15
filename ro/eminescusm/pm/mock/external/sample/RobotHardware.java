package ro.eminescusm.pm.mock.external.sample;

import ro.eminescusm.pm.mock.external.hardware.sensors.DistanceSensor;
import ro.eminescusm.pm.mock.external.hardware.servo.Servo;
import ro.eminescusm.pm.mock.external.opmode.HardwareMap;
import ro.eminescusm.pm.mock.external.hardware.motor.DcMotor;

public class RobotHardware {
    private DcMotor leftFrontMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor leftBackMotor = null;
    private DcMotor rightBackMotor = null;

    private Servo leftServo = null;
    private Servo rightServo = null;

    private DistanceSensor rearDistanceSensor = null;
    private DistanceSensor frontDistanceSensor = null;
    private DistanceSensor leftDistanceSensor = null;
    private DistanceSensor rightDistanceSensor = null;

    private final HardwareMap hardwareMap;

    public RobotHardware(HardwareMap hardwareMap) {

        this.hardwareMap = hardwareMap;
    }

    /**
     * Initialize the robot hardware
     */
    public void init() {
        leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFront");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFront");
        leftBackMotor = hardwareMap.get(DcMotor.class, "leftBack");
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBack");

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);

        leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftServo = hardwareMap.get(Servo.class, "leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");

        rearDistanceSensor = hardwareMap.get(DistanceSensor.class, "rearDistanceSensor");
        frontDistanceSensor = hardwareMap.get(DistanceSensor.class, "frontDistanceSensor");
        leftDistanceSensor = hardwareMap.get(DistanceSensor.class, "leftDistanceSensor");
        rightDistanceSensor = hardwareMap.get(DistanceSensor.class, "rightDistanceSensor");
    }

    public DcMotor getLeftFrontMotor() {
        return leftFrontMotor;
    }

    public DcMotor getRightFrontMotor() {
        return rightFrontMotor;
    }

    public DcMotor getLeftBackMotor() {
        return leftBackMotor;
    }

    public DcMotor getRightBackMotor() {
        return rightBackMotor;
    }

    public Servo getLeftServo() {
        return leftServo;
    }

    public Servo getRightServo() {
        return rightServo;
    }

    public DistanceSensor getRearDistanceSensor() {
        return rearDistanceSensor;
    }

    public DistanceSensor getFrontDistanceSensor() {
        return frontDistanceSensor;
    }

    public DistanceSensor getLeftDistanceSensor() {
        return leftDistanceSensor;
    }

    public DistanceSensor getRightDistanceSensor() {
        return rightDistanceSensor;
    }
}
