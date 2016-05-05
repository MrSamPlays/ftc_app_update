package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;
import com.qualcomm.robotcore.util.Util;

public abstract class CustomOpMode extends OpMode {
    private CustomOpMode.a a = null;
    private Thread b = null;
    private ElapsedTime c = new ElapsedTime();
    private volatile boolean d = false;

    public CustomOpMode() {
    }

    public abstract void runOpMode() throws InterruptedException;

    public synchronized void waitForStart() throws InterruptedException {
        while(!this.d) {
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
        return this.d;
    }

    public final void init() {
        this.a = new CustomOpMode.a(this);
        this.b = new Thread(this.a, "Linear OpMode Helper");
        this.b.start();
    }

    public final void init_loop() {
        this.a();
    }

    public final void start() {
        this.d = true;
        synchronized(this) {
            this.notifyAll();
        }
    }

    public final void loop() {
        this.a();
    }

    public void stop() {
        this.d = false;
        if(!this.a.c()) {
            this.b.interrupt();
        }

        this.c.reset();

        while(!this.a.c() && this.c.time() < 0.5D) {
            Thread.yield();
        }

        if(!this.a.c()) {
            RobotLog.e("*****************************************************************");
            RobotLog.e("User Linear Op Mode took too long to exit; emergency killing app.");
            RobotLog.e("Possible infinite loop in user code?");
            RobotLog.e("*****************************************************************");
            System.exit(-1);
        }

    }

    private void a() {
        if(this.a.a()) {
            throw this.a.b();
        } else {
            synchronized(this) {
                this.notifyAll();
            }
        }
    }

    private static class a implements Runnable {
        private RuntimeException a = null;
        private boolean b = false;
        private final CustomOpMode c;

        public a(CustomOpMode var1) {
            this.c = var1;
        }

        public void run() {
            Util.logThreadLifeCycle("LinearOpModeHelper.run()", new Runnable() {
                public void run() {
                    a.this.a = null;
                    a.this.b = false;

                    try {
                        a.this.c.runOpMode();
                    } catch (InterruptedException var6) {
                        RobotLog.d("CustomOpMode received an Interrupted Exception; shutting down this linear op mode");
                    } catch (RuntimeException var7) {
                        a.this.a = var7;
                    } finally {
                        a.this.b = true;
                    }

                }
            });
        }

        public boolean a() {
            return this.a != null;
        }

        public RuntimeException b() {
            return this.a;
        }

        public boolean c() {
            return this.b;
        }
    }
}
