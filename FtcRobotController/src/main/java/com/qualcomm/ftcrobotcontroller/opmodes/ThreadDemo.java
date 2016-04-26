package com.qualcomm.ftcrobotcontroller.opmodes;


// Main class go!
public class ThreadDemo {
    public static void main(String[] args) {
        Thread1 t = new Thread1();
        Thread2 w = new Thread2();
        Thread xxx = new Thread(w);
        Thread xx = new Thread(t);
        xx.start();
        xxx.start();
        System.err.println("You are going to destroy the whole factory");
    }

    /**
     * Created by sam on 16-Sep-15.
     */
    // Thread 1
    static class Thread1 implements Runnable{
        public void run() {
            for (int i = 1; i <= 10; i += 1) {
                System.out.println("CANCEL SELF DESTRUCT!!!!!");
            }
        }
    }

    // Thread 2
    static class Thread2 implements Runnable {
        @Override
        public void run() {
            int[] x = new int[] {
                 10,9,8,7,6,5,4,3,2,1,0
            };
            System.out.println("Blowing up in:");
            for (int i = 0; i < x.length; i++) {
                System.out.println(x[i]);
            }
            System.out.println("BOOM!!");
        }
    }
}