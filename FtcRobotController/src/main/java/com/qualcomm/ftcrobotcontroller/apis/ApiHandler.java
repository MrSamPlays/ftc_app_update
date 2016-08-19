package com.qualcomm.ftcrobotcontroller.apis;

import com.qualcomm.ftcrobotcontroller.apis.console.ConsoleType;
import com.qualcomm.ftcrobotcontroller.apis.console.CombinedConsole;
import com.qualcomm.ftcrobotcontroller.apis.console.JavaConsole;
import com.qualcomm.ftcrobotcontroller.apis.errorHandle.ErrorHandleType;
import com.qualcomm.ftcrobotcontroller.apis.errorHandle.JavaErrorHandler;

public class ApiHandler {
    //////////////////////////////////
    //Error Handlers                //
    //////////////////////////////////
    private static ErrorHandleType errorHandler = new JavaErrorHandler();

    public static ErrorHandleType getErrorHandler(){
        return errorHandler;
    }

    //////////////////////////////////
    //Consoles                      //
    //////////////////////////////////
    private static ConsoleType console[] = { new CombinedConsole(), new JavaConsole() };

    public static ConsoleType getConsole(){
        return console[0];
    }
}