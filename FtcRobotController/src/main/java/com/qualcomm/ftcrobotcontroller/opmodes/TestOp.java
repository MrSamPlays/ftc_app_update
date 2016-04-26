package com.qualcomm.ftcrobotcontroller.opmodes;

import android.content.Context;
import android.hardware.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LegacyModule;

import java.util.List;
import java.util.jar.JarFile;

/**
 * Created by sam on 15-Mar-16.
 */

public class TestOp extends LinearOpMode {
    private DcMotor BL;
    private DcMotor BR;
    private LegacyModule l;
    private ColorSensor c;
    boolean LEDisEnabled = false;
    void initialize() {
        BL = hardwareMap.dcMotor.get("m1");
        BR = hardwareMap.dcMotor.get("m2");
        c = hardwareMap.colorSensor.get("colour");
        l = hardwareMap.legacyModule.get("Legacy Module 1");
        BL.setDirection(DcMotor.Direction.REVERSE);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        l.enableAnalogReadMode(0);
    }
    @Override
    public void runOpMode() throws InterruptedException {

        initialize();
        waitForStart();
        while (opModeIsActive()) {

            BL.setPower(gamepad1.left_stick_y);
            BR.setPower(gamepad1.right_stick_y);
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
            telemetry.addData("Encoders", BL.getCurrentPosition() + "\n" + BR.getCurrentPosition());
            telemetry.addData("Colour sensor", l.readAnalog(0).toString() + "\n" + Integer.toHexString(c.argb()));
        }
    }
}