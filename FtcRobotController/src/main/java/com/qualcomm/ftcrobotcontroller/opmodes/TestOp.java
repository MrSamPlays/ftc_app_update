package com.qualcomm.ftcrobotcontroller.opmodes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.ftcrobotcontroller.apis.console.Console;
import com.qualcomm.ftcrobotcontroller.apis.map.AndroidImageLoader;
import com.qualcomm.ftcrobotcontroller.drivers.TouchSensor;
import com.qualcomm.hardware.hitechnic.HiTechnicNxtColorSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LegacyModule;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

public class TestOp extends DriverOp {
    private static LegacyModule legMod;
    private DcMotor BL;
    private DcMotor BR;
    private ColorSensor c;
    private HiTechnicNxtColorSensor e;
    private com.qualcomm.robotcore.hardware.TouchSensor touch;
    private UltrasonicSensor r;
    private boolean reversed = false;
    private Bitmap bitmap;

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
        bitmap = AndroidImageLoader.decodeSampledBitmapFromResource(R.drawable.background, 10, 10);
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