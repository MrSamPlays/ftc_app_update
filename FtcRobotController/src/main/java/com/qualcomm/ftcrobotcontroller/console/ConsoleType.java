package com.qualcomm.ftcrobotcontroller.console;

import com.qualcomm.robotcore.robocol.Telemetry;

public interface ConsoleType {
    public void log(String message);

    public void error(String message);

    public void warn(String message);

    public void addTelemetry(Telemetry telemetry);
}