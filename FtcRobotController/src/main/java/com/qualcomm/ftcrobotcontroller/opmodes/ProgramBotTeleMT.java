package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by sam on 12-Oct-15.
 */
class DriveL extends OpMode implements Runnable {
    RobotMethodLibraries rml = new RobotMethodLibraries();
    public void init() {
        rml.Init_Motors();
    }
    @Override
    public void loop() {
        if ((-gamepad1.left_stick_y) > 5) {
            rml.FL.setPower(1);
            rml.BL.setPower(1);
        }
    }
    @Override
    public void run() {
        while(true) {
            //uses loop statement
        }
    }
}
class DriveR extends OpMode implements Runnable {
    public void init() {

    }

    @Override
    public void loop() {

    }
    @Override
    public void run() {

    }
}
public class ProgramBotTeleMT implements Runnable {
    @Override
    public void run() {
        DriveL l = new DriveL();
        Thread t = new Thread(l);

        t.start();
    }
}
