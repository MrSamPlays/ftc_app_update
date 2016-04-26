package com.qualcomm.ftcrobotcontroller.drivers;

import com.qualcomm.ftcrobotcontroller.opmodes.TestOp;
import com.qualcomm.robotcore.hardware.LegacyModule;

/**
 * Created by sam on 09-Apr-16.
 */
public class TouchSensor {
    private static LegacyModule l;
    private static int port=1;

    public static void init(int tport){
        l = TestOp.getLegMod();
        port=tport;
    }

    public static void init(){
        l = TestOp.getLegMod();
    }

    public static boolean getPressed(){
        int value = l.readAnalog(port)[0];
        if (value <= -50) {
            return true;
        }
        return false;
    }
}
