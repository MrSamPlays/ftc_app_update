package com.qualcomm.ftcrobotcontroller.apis.console;

import com.qualcomm.ftcrobotcontroller.apis.errorHandle.ErrorHandle;
import com.qualcomm.robotcore.robocol.Telemetry;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CombinedConsole implements ConsoleType {
    private Telemetry telemetry;
    private FileWriter writer;

    @Override
    public void log(String message) {
        String fullMessage = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'";
        telemetry.addData("Log message", fullMessage);
        System.out.println(fullMessage);

        try {
            writer.write(message + "\n");
        } catch (Throwable e) {
            ErrorHandle.handle(e);
        }
    }

    @Override
    public void error(String message) {
        String fullMessage = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'";
        telemetry.addData("ERROR!!!", fullMessage);
        System.err.println("Error: " + fullMessage);

        try {
            writer.write("ERROR: " + message + "\n");
        } catch (Throwable e) {
            ErrorHandle.handle(e);
        }
    }

    @Override
    public void warn(String message) {
        String fullMessage = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'";
        telemetry.addData("Warning", fullMessage);
        System.out.println("Warning" + fullMessage);

        try {
            writer.write("Warning: " + message + "\n");
        } catch (Throwable e) {
            ErrorHandle.handle(e);
        }
    }

    @Override
    public void addTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public void addFile(FileWriter writer) {
        this.writer = writer;
    }
}