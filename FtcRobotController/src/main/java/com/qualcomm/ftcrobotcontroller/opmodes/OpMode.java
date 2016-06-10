/*package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.drivers.TouchSensor;
import com.qualcomm.hardware.hitechnic.HiTechnicNxtColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LegacyModule;

/**
 * Created by medude on 4/28/16.
 */
/*
public class OpMode extends LinearOpMode {
    private static LegacyModule legMod;
    boolean LEDEnabled=false;
    private DcMotor[] motors;
    private ColorSensor color;
    private HiTechnicNxtColorSensor e;
    private boolean driveReversed=false;

    public OpMode(String[] motorNames){
        motors=new DcMotor[motorNames.length];
        for(int i=0; i<motorNames.length; i++){
            motors[i]=hardwareMap.dcMotor.get(motorNames[i]);
        }

        //BL.setDirection(DcMotor.Direction.REVERSE);
        //BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        //BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        //BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        //BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
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

    //For left: (opposite for right)
    //False- left stick
    //True- right stick
    private boolean controllerLocation;
    //private boolean lastSwap=reversed;

    //Powers- confusing, remember to explain these.
    float holder1;
    float holder2;

    private void getLocation(){
        if(controllerLocation){
            holder1=gamepad1.left_stick_y;
            holder2=gamepad1.right_stick_y;
        }else{
            holder2=gamepad1.left_stick_y;
            holder1=gamepad1.right_stick_y;
        }
    }

    private void handleUpdates(){
        //if(reversed!=lastSwap){
        //    controllerLocation=!controllerLocation;
        //}

        getLocation();

        //BL.setPower(holder1);
        //BL.setPower(holder2);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        //initialize();
        waitForStart();
        while (opModeIsActive()) {
            //if (!reversed) {
            //    BL.setPower(gamepad1.left_stick_y);
            //    BR.setPower(gamepad1.right_stick_y);
            //} else {
            //    BR.setPower(gamepad1.left_stick_y);
            //    BL.setPower(gamepad1.right_stick_y);
            //}

            // BL.setPower(scaleInput(gamepad1.left_stick_y));
            // BR.setPower(scaleInput(gamepad1.right_stick_y));
            if (gamepad1.a) {
             //   BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
             //   BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
             //   BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
             //   BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            }
            if (gamepad1.b) {
             //   if (!LEDisEnabled) {
             //       c.enableLed(true);
             //       LEDisEnabled = true;
             //   } else {
             //       c.enableLed(false);
             //       LEDisEnabled = false;
                }
                sleep(100);
            }
            if (gamepad1.guide) {
              //  if (!reversed) {
              //      BL.setDirection(DcMotor.Direction.FORWARD);
              //      BR.setDirection(DcMotor.Direction.REVERSE);
              //      reversed = true;
                }
                else {
              //      BR.setDirection(DcMotor.Direction.FORWARD);
              //      BL.setDirection(DcMotor.Direction.REVERSE);
              //      reversed = false;
                }
            }
            //telemetry.addData("Touch", TouchSensor.getPressed());
           // telemetry.addData("Reversed?", reversed);
            //telemetry.addData("Colour sensor0", legMod.readAnalog(5)[0] + "\n" + Integer.toHexString(c.argb()));
           // telemetry.addData("Colour sensor1", legMod.readAnalog(5)[1] + "\n" + Integer.toHexString(c.argb()));
           // telemetry.addData("HtColorSensor", "#" + Integer.toHexString(e.argb()));
        }
    }
}*/