package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.hardware.hitechnic.HiTechnicNxtColorSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LegacyModule;

public abstract class DriverOp extends CustomOpMode {
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

    public abstract void initialize();

    public enum runState {
        IDLE,
        RUNNING,
        SHUTDOWN,
        ERROR
    }

    public double scaleInput(double d) {
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
    private boolean lastSwap=reversed;

    //Powers- confusing, remember to explain these.
    float holder1;
    float holder2;

    private void getLocation(){
        if(controllerLocation){
            holder1=gamepad1.right_stick_y;
            holder2=gamepad1.left_stick_y;
        }else{
            holder2=gamepad1.right_stick_y;
            holder1=gamepad1.left_stick_y;
        }
    }

    private void handleUpdates(){
        if(reversed!=lastSwap){
            controllerLocation=!controllerLocation;
        }

        getLocation();

        BL.setPower(holder1);
        BR.setPower(holder2);
    }

    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        while (opModeIsActive()) {
            handleUpdates();
            oneRun();
        }
    }

    public abstract void oneRun();
}