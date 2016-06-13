package com.qualcomm.ftcrobotcontroller.errorHandle;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.qualcomm.ftcrobotcontroller.console.Console;

public class JavaErrorHandler implements ErrorHandleType {
    @Override
    public void handle(Throwable throwable, String message, boolean doShowError){
        StringWriter errorS=new StringWriter();
        PrintWriter errorThing=new PrintWriter(errorS);
        String error;

        throwable.printStackTrace(errorThing);
        error=errorS.toString();

        if(doShowError){
            Console.error(message+"\n"+error);
        }else{
            Console.error(message);
        }

        System.exit(-1);
    }
}