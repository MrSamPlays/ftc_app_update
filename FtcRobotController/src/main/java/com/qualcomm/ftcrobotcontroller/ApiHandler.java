package com.qualcomm.ftcrobotcontroller;

import com.qualcomm.ftcrobotcontroller.console.ConsoleType;
import com.qualcomm.ftcrobotcontroller.console.JavaConsole;
import com.qualcomm.ftcrobotcontroller.errorHandle.ErrorHandleType;
import com.qualcomm.ftcrobotcontroller.errorHandle.JavaErrorHandler;

public class ApiHandler {
    //////////////////////////////////
    //Error Handlers                //
    //////////////////////////////////
    private static ErrorHandleType errorHandler=new JavaErrorHandler();

    public static ErrorHandleType getErrorHandler(){
        return errorHandler;
    }

    //////////////////////////////////
    //Consoles                      //
    //////////////////////////////////
    private static ConsoleType console=new JavaConsole();

    public static ConsoleType getConsole(){
        return console;
    }
}