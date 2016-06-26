package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.Robot;
import com.qualcomm.ftcrobotcontroller.drivers.TouchSensor;
import com.qualcomm.ftcrobotcontroller.math.Tuple;
import com.qualcomm.ftcrobotcontroller.math.Vector2;
import com.qualcomm.hardware.hitechnic.HiTechnicNxtColorSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LegacyModule;

public class AutoOp extends CustomOpMode {
    protected static LegacyModule legMod;
    protected DcMotor BL;
    protected DcMotor BR;
    protected ColorSensor c;
    protected HiTechnicNxtColorSensor e;
    protected boolean reversed = false;
    long startTime = System.currentTimeMillis();
    public class ShutDownIn extends Thread {
        TestOp t;
        public ShutDownIn(TestOp e) {
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
    public static LegacyModule getLegMod() {
        return legMod;
    }

    public Tuple goFromTo(Vector2 start, Vector2 end) {
        double xDelta = end.x-start.x;
        double yDelta = end.y-start.y;
        double magnitude = Math.sqrt(Math.pow(xDelta, 2) + Math.pow(yDelta, 2));
        double angle = Math.atan2(yDelta,xDelta);
        double angleToTurn = angle-Robot.angle_rad;
        return new Tuple<Double, Double>(magnitude, angleToTurn);
    }

    public void initialize() {
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

    private Tuple t;

    @Override
    public void runOpMode() throws InterruptedException {
        t = goFromTo(new Vector2(0,0), new Vector2(2,1));
        double angle = ((Double) t.y);
        //Each motor goes this many rotations
        double rots = angle/1.0471975333333333333333333333333d;
        BL.setPower(1);
        BR.setPower(1);
    }
}
