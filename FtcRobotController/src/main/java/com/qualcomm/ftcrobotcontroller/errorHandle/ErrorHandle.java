package com.qualcomm.ftcrobotcontroller.errorHandle;

import com.qualcomm.ftcrobotcontroller.ApiHandler;

public class ErrorHandle {
    private static ErrorHandleType errorObject=ApiHandler.getErrorHandler();

    public static void handle(Throwable throwable){
        errorObject.handle(throwable, "Error!", true);
    }

    public static void handle(Throwable throwable, String message, boolean showError){
        errorObject.handle(throwable, message, showError);
    }

    public static void handle(String message){
        errorObject.handle(new Throwable(), message, false);
    }
}