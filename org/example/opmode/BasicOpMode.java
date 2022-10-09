package org.example.opmode;

import ro.eminescusm.pm.mock.internal.opmode.OpMode;
import ro.eminescusm.pm.mock.internal.opmode.annotations.TeleOp;


@TeleOp(name = "BasicOpMode", group = "Basic")
public class BasicOpMode extends OpMode {
    @Override
    public void init() {
        System.out.println("BasicOpMode.init()");
    }

    @Override
    public void init_loop() {
        System.out.println("BasicOpMode.init_loop()");
    }

    @Override
    public void start() {
        System.out.println("BasicOpMode.start()");
    }

    @Override
    public void loop() {
        System.out.println("BasicOpMode.loop()");
    }

    @Override
    public void stop() {
        System.out.println("BasicOpMode.stop()");
    }
}
