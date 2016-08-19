package com.qualcomm.ftcrobotcontroller.apis.errorHandle;

public interface ErrorHandleType {
    public void handle(Throwable throwable, String message, boolean showError);
}