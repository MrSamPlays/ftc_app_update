package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by sam on 03-Oct-15.
 * Changelog:
 * 05-Oct-15
 */
public class ProgramBotTele extends OpMode {
    Telemetry t = new Telemetry();
    private boolean dead = true;
    private boolean Ltdead = true;
    private boolean RtDead = true;
    private RobotMethodLibraries rml = new RobotMethodLibraries();

    public void init() {
        rml.Init_Motors();
    }

    public void loop() {
        while (true) {
            if (gamepad1.left_stick_x <= -5 || gamepad1.left_stick_x >= 5) {
                dead = false;
                rml.BL.setPower(rml.scaleInput(gamepad1.left_stick_x));
                rml.FL.setPower(rml.scaleInput(gamepad1.left_stick_x));
                rml.BR.setPower(-rml.scaleInput(gamepad1.left_stick_x));
                rml.FR.setPower(-rml.scaleInput(gamepad1.left_stick_x));
            } else if (gamepad1.left_trigger != 0) {
                Ltdead = false;
                rml.BL.setPower(-rml.scaleInput(gamepad1.left_trigger));
                rml.BR.setPower(-rml.scaleInput(gamepad1.left_trigger));
                rml.FL.setPower(-rml.scaleInput(gamepad1.left_trigger));
                rml.FR.setPower(-rml.scaleInput(gamepad1.left_trigger));
            } else if (gamepad1.right_trigger != 0) {
                RtDead = false;
                rml.BL.setPower(rml.scaleInput(gamepad1.left_trigger));
                rml.BR.setPower(rml.scaleInput(gamepad1.left_trigger));
                rml.FL.setPower(rml.scaleInput(gamepad1.left_trigger));
                rml.FR.setPower(rml.scaleInput(gamepad1.left_trigger));
            } else {
                dead = true;
                Ltdead = true;
                RtDead = true;
            }
            /**
             * When you press start and select
             */
            /*
            if (gamepad1.start && gamepad1.back) {
                return;
            }
            */
            t.addData("Left Trigger pulled", !Ltdead);
            t.addData("Right Trigger pulled", !RtDead);
            t.addData("Left-stick out of deadzone", dead);
            // check to see if both triggers are pressed
            /*
            if (!Ltdead && !RtDead) {
                rml.FL.setPower(0);
                rml.FR.setPower(0);
                rml.BR.setPower(0);
                rml.BL.setPower(0);
            } */
        }
    }
}
