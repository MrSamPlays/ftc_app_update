package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.drivers.TouchSensor;
import com.qualcomm.hardware.hitechnic.HiTechnicNxtColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LegacyModule;


/**
 * Created by sam on 15-Mar-16.
 */

public class TestOp extends LinearOpMode {
    private static LegacyModule legMod;
    boolean LEDisEnabled = false;
    private DcMotor BL;
    private DcMotor BR;
    private LegacyModule l;
    private ColorSensor c;
    private HiTechnicNxtColorSensor e;
    private boolean reversed = false;

    public static LegacyModule getLegMod() {
        return legMod;
    }

    void initialize() {
        BL = hardwareMap.dcMotor.get("m1");
        BR = hardwareMap.dcMotor.get("m2");
        c = hardwareMap.colorSensor.get("colour");
        legMod = hardwareMap.legacyModule.get("Legacy Module 1");
        e = new HiTechnicNxtColorSensor(legMod, 0);
        BL.setDirection(DcMotor.Direction.REVERSE);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        legMod.enableAnalogReadMode(0);

        legMod.enable9v(5, true);
        legMod.enableAnalogReadMode(5);

        TouchSensor.init();
    }

    double scaleInput(double d) {
        double[] br = {0, 0.1, 0.2, 0.2, 0.2, 0.5, 0.7, 0.7, 0.9, 1, 1};
        int index = (int) Math.round(d);
        try {
            if (index < 0) {
                index = -index;
                return -br[index];
            }
        } catch (ArrayIndexOutOfBoundsException a) {
            a.printStackTrace();
            a.getCause();
        }
        return br[index];
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
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
            telemetry.addData("Touch", TouchSensor.getPressed());
            telemetry.addData("Reversed?", reversed);
            telemetry.addData("Colour sensor0", legMod.readAnalog(5)[0] + "\n" + Integer.toHexString(c.argb()));
            telemetry.addData("Colour sensor1", legMod.readAnalog(5)[1] + "\n" + Integer.toHexString(c.argb()));
            telemetry.addData("HtColorSensor", "#" + Integer.toHexString(e.argb()));
        }
    }
}