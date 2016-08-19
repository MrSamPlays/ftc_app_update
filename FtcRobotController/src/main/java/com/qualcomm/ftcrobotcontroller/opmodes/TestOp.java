package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.apis.console.Console;
import com.qualcomm.ftcrobotcontroller.drivers.TouchSensor;
import com.qualcomm.hardware.hitechnic.HiTechnicNxtColorSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LegacyModule;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;


/**
 * Created by sam on 15-Mar-16.
 */

public class TestOp extends DriverOp {
    private static LegacyModule legMod;
    boolean LEDisEnabled = false;
    private DcMotor BL;
    private DcMotor BR;
    private ColorSensor c;
    private HiTechnicNxtColorSensor e;
    com.qualcomm.robotcore.hardware.TouchSensor touch;
    private UltrasonicSensor r;
    private boolean reversed = false;

    @Override
    public void initialize() {
        BL = hardwareMap.dcMotor.get("m1");
        BR = hardwareMap.dcMotor.get("m2");
        c = hardwareMap.colorSensor.get("colour");
        touch = hardwareMap.touchSensor.get("touch");
        legMod = hardwareMap.legacyModule.get("Legacy Module 1");
        e = new HiTechnicNxtColorSensor(legMod, 0);
        BL.setDirection(DcMotor.Direction.REVERSE);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        legMod.enableAnalogReadMode(0);
        r = hardwareMap.ultrasonicSensor.get("ultra");
        legMod.enable9v(5, true);
        legMod.enableAnalogReadMode(5);
        TouchSensor.init();
    }

    @Override
    public void oneRun() {

    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        Console.addTelemetry(telemetry);
        waitForStart();

        while (opModeIsActive()) {
            if (!reversed) {
                BL.setPower(gamepad1.left_stick_y);
                BR.setPower(gamepad1.right_stick_y);
            } else {
                BR.setPower(gamepad1.left_stick_y);
                BL.setPower(gamepad1.right_stick_y);
            }
            // BL.setPower(scaleInput(gamepad1.left_stick_y));
            // BR.setPower(scaleInput(gamepad1.right_stick_y));
            if (gamepad1.a) {
                BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            }
            if (gamepad1.b) {
                if (!LEDisEnabled) {
                    c.enableLed(true);
                    LEDisEnabled = true;
                } else {
                    c.enableLed(false);
                    LEDisEnabled = false;
                }
                sleep(100);
            }
            if (gamepad1.guide) {
                if (!reversed) {
                    BL.setDirection(DcMotor.Direction.FORWARD);
                    BR.setDirection(DcMotor.Direction.REVERSE);
                    reversed = true;
                }
                else {
                    BR.setDirection(DcMotor.Direction.FORWARD);
                    BL.setDirection(DcMotor.Direction.REVERSE);
                    reversed = false;
                }
            }

            telemetry.addData("Ultrasonic", r.getUltrasonicLevel());
        }
    }

    @Override
    public void stop() {
        BL.setPower(0);
        BR.setPower(0);
        super.stop();
    }
}