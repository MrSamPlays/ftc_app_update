package com.qualcomm.ftcrobotcontroller;

import com.qualcomm.ftcrobotcontroller.console.ConsoleType;
import com.qualcomm.ftcrobotcontroller.console.JavaConsole;
import com.qualcomm.ftcrobotcontroller.errorHandle.ErrorHandleType;
import com.qualcomm.ftcrobotcontroller.errorHandle.JavaErrorHandler;

public class ApiHandler {
    public static void init(){
        consoles[0]=new JavaConsole();

        errorHandlers[0]=new JavaErrorHandler();
    }

    //////////////////////////////////
    //Error Handlers                //
    //////////////////////////////////
    private static ErrorHandleType[] errorHandlers=new ErrorHandleType[2];

    public static ErrorHandleType getErrorHandler(){
        return errorHandlers[0];
    }

    //////////////////////////////////
    //Consoles                      //
    //////////////////////////////////
    private static ConsoleType[] consoles=new ConsoleType[2];

    public static ConsoleType getConsole(){
        return consoles[0];
    }
}