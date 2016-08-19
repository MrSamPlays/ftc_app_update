package com.qualcomm.ftcrobotcontroller.apis.console;

import com.qualcomm.robotcore.robocol.Telemetry;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JavaConsole implements ConsoleType {
    private Telemetry telemetry;

    @Override
    public void log(String message) {
        String fullMessage = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'";
        System.out.println(fullMessage);
    }

    @Override
    public void error(String message) {
        String fullMessage = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'";
        System.out.println("Error: " + fullMessage);
    }

    @Override
    public void warn(String message) {
        String fullMessage = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'";
        System.out.println("Warning" + fullMessage);
    }

    @Override
    public void addTelemetry(Telemetry telemetry){
        this.telemetry=telemetry;
    }

    @Override
    public void addFile(FileWriter writer) {}
}