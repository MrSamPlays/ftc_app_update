package com.qualcomm.ftcrobotcontroller;

import com.qualcomm.ftcrobotcontroller.opmodes.CustomOpMode;

public class ShutDownIn extends Thread {
    private long startTime;
    private float seconds;

    CustomOpMode t;
    public ShutDownIn(CustomOpMode e, float seconds) {
        this.t = e;
        this.seconds = seconds;
    }
    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        System.out.println("Shutdown Sequence initialized");
        while (true) {
            if (System.currentTimeMillis() > startTime + seconds*1000) {
                System.out.println("SHUTTING DOWN");
                break;
            }
        }
        t.stop();
    }
}