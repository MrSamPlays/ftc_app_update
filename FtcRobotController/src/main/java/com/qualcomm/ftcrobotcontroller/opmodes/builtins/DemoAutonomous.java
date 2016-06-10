package com.qualcomm.ftcrobotcontroller.opmodes.builtins;

// import com.qualcomm.ftcrobotcontroller.opmodes.*;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by sam on 14-Sep-15.
 */


public class DemoAutonomous extends OpMode {
    RobotMethodLibraries rml = new RobotMethodLibraries();
    Telemetry t = new Telemetry();

    public void init() {
        t.addData("Motors", rml.FR.getController());
        rml.Init_Motors();
        rml.FL.setPower(0);
        rml.FR.setPower(0);
    }

    public void loop() {
        rml.FR.setTargetPosition(0);
        t.addData("Current Position", rml.FR.getCurrentPosition());
        while (rml.FR.getCurrentPosition() < 1000) {
            rml.FL.setPower(1);
            rml.FR.setPower(1);
        }
    }
}
