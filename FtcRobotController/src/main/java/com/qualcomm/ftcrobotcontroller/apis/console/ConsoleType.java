package com.qualcomm.ftcrobotcontroller.apis.console;

import com.qualcomm.robotcore.robocol.Telemetry;

import java.io.FileWriter;

public interface ConsoleType {
    public void log(String message);

    public void error(String message);

    public void warn(String message);

    public void addTelemetry(Telemetry telemetry);

    public void addFile(FileWriter writer);
}