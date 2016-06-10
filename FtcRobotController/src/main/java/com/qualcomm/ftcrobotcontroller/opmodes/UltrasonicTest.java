package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.drivers.UltrasonicDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LegacyModule;

import java.util.Random;

/**
 * Created by sam on 22-May-16.
 */

public class UltrasonicTest extends DriverOp {
    // IO

    //private LegacyModule legMod=hardwareMap.legacyModule.get("Legacy Module 1");
    private LegacyModule legMod=DriverOp.getLegMod();
    private DcMotor BL;
    private DcMotor BR;
    private boolean reversed=false;
    UltrasonicDriver ultrasonic;

    // Stopping stuff
    Thread t=null;
    long startTime;

    class ShutDownIn extends Thread {
        UltrasonicTest t;
        public ShutDownIn(UltrasonicTest e) {
            this.t = e;
        }
        @Override
        public void run() {
            System.out.println("Shutdown Sequence initialized");
            while (true) {
                if (System.currentTimeMillis() > startTime + 120000) {
                    System.out.println("SHUTTING DOWN");
                    break;
                }
            }
            t.stop();
        }
    }

    @Override
    public void initialize() {
        BL = hardwareMap.dcMotor.get("m1");
        BR = hardwareMap.dcMotor.get("m2");
        legMod = hardwareMap.legacyModule.get("Legacy Module 1");
        BL.setDirection(DcMotor.Direction.REVERSE);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        legMod.enableAnalogReadMode(0);

        legMod.enable9v(5, true);
        legMod.enableAnalogReadMode(5);

        ultrasonic=new UltrasonicDriver(legMod, 5);
    }

    @Override
    public void oneRun() {
        
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        startTime = System.currentTimeMillis();
        t = new ShutDownIn(this);
        t.setDaemon(true);

        t.start();
        while(opModeIsActive()) {

            ///////////////
            //   Input   //
            ///////////////

            // Reversing code
            if (gamepad1.guide) {
                if (!reversed) {
                    BL.setDirection(DcMotor.Direction.FORWARD);
                    BR.setDirection(DcMotor.Direction.REVERSE);
                }
                else {
                    BR.setDirection(DcMotor.Direction.FORWARD);
                    BL.setDirection(DcMotor.Direction.REVERSE);
                }

                reversed=!reversed;
            }

            if (!reversed) {
                BL.setPower(gamepad1.left_stick_y);
                BR.setPower(gamepad1.right_stick_y);
            } else {
                BR.setPower(gamepad1.left_stick_y);
                BL.setPower(gamepad1.right_stick_y);
            }

            if (gamepad1.a) {
                BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            }

            ////////////////
            //   Output   //
            ////////////////

            telemetry.addData("Ultrasonic Sensor", ultrasonic.getDistance(16));
        }
    }

    // Called to end program
    @Override
    public void stop() {
        BL.setPower(0);
        BR.setPower(0);
        super.stop();
        BL.setPower(0);
        BR.setPower(0);
    }
}
