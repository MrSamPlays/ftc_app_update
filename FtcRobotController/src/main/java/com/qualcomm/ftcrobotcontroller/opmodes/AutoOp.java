package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.Robot;
import com.qualcomm.ftcrobotcontroller.console.Console;
import com.qualcomm.ftcrobotcontroller.errorHandle.ErrorHandle;
import com.qualcomm.ftcrobotcontroller.math.Tuple;
import com.qualcomm.ftcrobotcontroller.math.Vector2;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LegacyModule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AutoOp extends CustomOpMode {
    protected static LegacyModule legMod;
    protected static DcMotor BL;
    protected static DcMotor BR;

    private FileWriter failWriter;
    private File failLog = new File("/sdcard/ftclog/fail.log");

    public Tuple goFromTo(Vector2 start, Vector2 end) {
        double xDelta = end.x - start.x;
        double yDelta = -end.y - start.y;
        double magnitude = Math.sqrt(Math.pow(xDelta, 2) + Math.pow(yDelta, 2));
        double theta = Math.atan2(yDelta, xDelta);
        double angleToTurn = theta;
        return new Tuple<Double, Double>(magnitude, angleToTurn);
    }

    private void resetEncoders() {
        int i = 0;
        do {
            BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            ++i;
        } while (BL.getCurrentPosition() != 0 || BR.getCurrentPosition() != 0);
        Console.log("Encoders reset Successfully in " + i + " tries");
    }

    public void initialize() {
        Console.addTelemetry(telemetry);

        try {
            if (!failLog.exists()) {
                failLog.createNewFile();
            }

            failWriter = new FileWriter(failLog, true);
        } catch (IOException e) {
            ErrorHandle.handle(e, "file I/O error", true);
        }

        Console.addFile(failWriter);
        Console.log("\n\nFile opened at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") .format(Calendar.getInstance().getTime()) + ".");

        BL = hardwareMap.dcMotor.get("m1");
        BR = hardwareMap.dcMotor.get("m2");
        legMod = hardwareMap.legacyModule.get("Legacy Module 1");

        BL.setDirection(DcMotor.Direction.REVERSE);
        resetEncoders();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();

        Tuple t = goFromTo(new Vector2(0, 0), new Vector2(1, 0));
        double angle = ((Double) t.y);

        //Each motor goes this many rotations I THINK- doing the math would help
        double rots = angle / 1.0471975333333333333333333333333d;
        BL.setPower(0.5);
        BR.setPower(-0.5);

        Console.log("Rots per motor: " + rots + "\n Turning total rad: " + angle);

        Console.log("Issue?");

        //while (Converter.clicksToRad(BL.getCurrentPosition()) * 2 * Math.PI < rots) {

        //BL.setPower(1);
        //BR.setPower(1);

        //while(Converter.clicksToRad(BL.getCurrentPosition()) * 2 * Math.PI < 5){
        //    Console.log("Moving");
        //}

        BL.setPower(0);
        BR.setPower(0);
    }

    @Override
    public void stop() {
        super.stop();
        try {
            failWriter.flush();
            failWriter.close();
        } catch (IOException e) {
            ErrorHandle.handle(e);
        }
    }
}