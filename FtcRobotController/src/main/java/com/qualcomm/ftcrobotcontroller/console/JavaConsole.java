package com.qualcomm.ftcrobotcontroller.console;

import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.util.RobotLog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JavaConsole implements ConsoleType {
    private Telemetry telemetry;

    @Override
    public void log(String message) {
        telemetry.addData("Log message", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'");
        RobotLog.e(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'");
    }

    @Override
    public void error(String message) {
        telemetry.addData("ERROR!!!", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " '" + message + "'");
        RobotLog.e("ERROR!!!"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " '" + message + "'");
    }

    @Override
    public void warn(String message) {
        telemetry.addData("Warning", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " '" + message + "'");
        RobotLog.e("Warning"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " '" + message + "'");
    }

    @Override
    public void addTelemetry(Telemetry telemetry){
        this.telemetry=telemetry;
    }
}