package me.alexutzzu.mock.external.opmode;

import me.alexutzzu.mock.internal.opmode.annotations.Disabled;
import me.alexutzzu.mock.internal.opmode.OpMode;

import java.util.Scanner;

public class StartOpMode {
    private final OpMode opMode;
    private final Scanner scanner = new Scanner(System.in);

    public StartOpMode(OpMode opMode) {
        this.opMode = opMode;
    }

    public enum OpModeStates {
        PRE_INIT, INIT, INIT_LOOP, START, LOOP, STOP
    }

    //TODO Rework input system
    public void start() {
        if (opMode.getClass().isAnnotationPresent(Disabled.class)) {
            System.out.println("OpMode is disabled");
            return;
        }

        System.out.println("Welcome to the mock OpMode Starter!");
        System.out.println("This will attempt to start the " + opMode.getName() + " OpMode.");
        System.out.println("Type \"init\" to start the OpMode initialization.");

        OpModeStates state = OpModeStates.PRE_INIT;

        while (true) {
            String input = scanner.nextLine();

            switch (state) {
                case PRE_INIT:
                    if (input.equalsIgnoreCase("init")) {
                        state = OpModeStates.INIT;
                        System.out.println("Initializing OpMode...");
                    }
                    break;
                case INIT:
                    opMode.init();
                    state = OpModeStates.INIT_LOOP;
                    break;
                case INIT_LOOP:
                    opMode.init_loop();
                    if (input.equalsIgnoreCase("start")) {
                        state = OpModeStates.START;
                        System.out.println("Starting OpMode...");
                    }
                    break;
                case START:
                    opMode.start();
                    state = OpModeStates.LOOP;
                    System.out.println("OpMode started!");
                    break;
                case LOOP:
                    System.out.println("Looping");
                    opMode.loop();
                    if (input.equalsIgnoreCase("stop")) {
                        state = OpModeStates.STOP;
                        System.out.println("Stopping OpMode...");
                    }
                    break;
                case STOP:
                    opMode.stop();
                    System.out.println("The OpMode has been stopped.");
                    return;
            }
            //Clear the console with a delay
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
}
