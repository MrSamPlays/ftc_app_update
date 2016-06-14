package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.console.Console;
import com.qualcomm.ftcrobotcontroller.errorHandle.ErrorHandle;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.robocol.Telemetry;

public class Drive extends DriverOp {
    @Override
    public void initialize(){
        Console.addTelemetry(telemetry);

        //Setup variables found in DriverOp
        legMod=hardwareMap.legacyModule.get("Legacy Module 1");
        BL=hardwareMap.dcMotor.get("m1");
        BR=hardwareMap.dcMotor.get("m2");
        BL.setDirection(DcMotor.Direction.REVERSE);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }
    @Override
    public void oneRun() {
        Telemetry e = null;
        try {
            e.addData("problem", "haha");
        }catch (Error e0){
            ErrorHandle.handle(e0);
        }
    }
}