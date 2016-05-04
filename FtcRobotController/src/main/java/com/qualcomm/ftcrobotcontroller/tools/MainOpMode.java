package com.qualcomm.ftcrobotcontroller.tools;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;
import com.qualcomm.robotcore.util.Util;

import java.util.concurrent.TimeUnit;

/**
 * Created by nathan on 5/3/16.
 */
public abstract class MainOpMode {
    /*///////////////////////////////////
    /                                   /
    /       VARIABLE DECLARATIONS       /
    /                                   /
    ///////////////////////////////////*/

        //Controller gamepads
        public Gamepad gamepad1 = null;
        public Gamepad gamepad2 = null;

        //The way to talk back to the controller's phone
        public Telemetry telemetry = new Telemetry();

        //The way to get all the hardware
        public HardwareMap hardwareMap = null;

        //Runtime in nanoseconds
        private long runtime = 0L;

        //Runner object
        private Runner runner = null;

        //Helper thread
        private Thread thread = null;


        private ElapsedTime runlength = new ElapsedTime();

        //Has it started
        private volatile boolean running = false;

    /*/////////////////////
    /                     /
    /       Methods       /
    /                     /
    /////////////////////*/

        //Constructor
        public MainOpMode() {
            this.runtime = System.nanoTime();
        }

        //Get length of time that it has run for
        public double getRuntime() {
            double var1 = (double) TimeUnit.SECONDS.toNanos(1L);
            return (double)(System.nanoTime() - this.runtime) / var1;
        }

        //Reset the length of runtime
        public void resetStartTime() {
            this.runtime = System.nanoTime();
        }

        public abstract void runOpMode() throws InterruptedException;

        public synchronized void waitForStart() throws InterruptedException {
            while(!running) {
                synchronized(this) {
                    this.wait();
                }
            }

        }

        public void waitOneFullHardwareCycle() throws InterruptedException {
            this.waitForNextHardwareCycle();
            Thread.sleep(1L);
            this.waitForNextHardwareCycle();
        }

        public void waitForNextHardwareCycle() throws InterruptedException {
            synchronized(this) {
                this.wait();
            }
        }

        public void sleep(long milliseconds) throws InterruptedException {
            Thread.sleep(milliseconds);
        }

        public boolean opModeIsActive() {
            return running;
        }

        public final void init() {
            runner = new Runner(this);
            thread = new Thread(runner, "Linear OpMode Helper");
            thread.start();
        }

        public final void init_loop() {
            this.a();
        }

        public final void start() {
            running = true;
            synchronized(this) {
                this.notifyAll();
            }
        }

        public final void loop() {
            this.a();
        }

        public final void stop() {
            running = false;
            if(!runner.isEnded()) {
                thread.interrupt();
            }

            runlength.reset();

            while(!runner.isEnded() && runlength.time() < 0.5D) {
                Thread.yield();
            }

            if(!runner.isEnded()) {
                RobotLog.e("*****************************************************************");
                RobotLog.e("User Linear Op Mode took too long to exit; emergency killing app.");
                RobotLog.e("Possible infinite loop in user code?");
                RobotLog.e("*****************************************************************");
                System.exit(-1);
            }

        }

        private void a() {
            if(runner.isException()) {
                throw runner.getException();
            } else {
                synchronized(this) {
                    this.notifyAll();
                }
            }
        }

        private static class Runner implements Runnable {
            private RuntimeException exception = null;
            private boolean isEnded = false;
            private MainOpMode opMode;

            public Runner(MainOpMode opMode) {
                opMode = opMode;
            }

            public void run() {
                Util.logThreadLifeCycle("LinearOpModeHelper.run()", new Runnable() {
                    public void run() {
                        exception = null;
                        isEnded = false;

                        try {
                            opMode.runOpMode();
                        } catch (InterruptedException var6) {
                            RobotLog.d("LinearOpMode received an Interrupted Exception; shutting down this linear op mode");
                        } catch (RuntimeException var7) {
                            exception = var7;
                        } finally {
                            isEnded = true;
                        }

                    }
                });
            }

            public boolean isException() {
                return exception!=null;
            }

            public RuntimeException getException() {
                return exception;
            }

            public boolean isEnded() {
                return this.isEnded;
            }
        }
}
