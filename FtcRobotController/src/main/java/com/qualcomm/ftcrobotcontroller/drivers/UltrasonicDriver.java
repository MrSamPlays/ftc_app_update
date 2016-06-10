package com.qualcomm.ftcrobotcontroller.drivers;

import com.qualcomm.robotcore.hardware.I2cController;
import com.qualcomm.robotcore.hardware.I2cController.I2cPortReadyCallback;
import com.qualcomm.robotcore.hardware.I2cControllerPortDeviceImpl;
import com.qualcomm.robotcore.util.TypeConversion;

import java.util.concurrent.locks.Lock;

public class UltrasonicDriver extends I2cControllerPortDeviceImpl implements I2cPortReadyCallback {
    I2cController control;
    private byte[] readCache;
    private Lock readLock;
    private byte[] writeCache;
    private Lock writeLock;
    private int f;

    public UltrasonicDriver(I2cController controller, int physicalPort) {
        super(controller, physicalPort);
        this.f = 0;
        control = controller;
        if (controller == null) {
            System.exit(-1);
        }
        this.finishConstruction();
    }

    @Override
    protected void controllerNowArmedOrPretending() {
        // IO configuration
        this.readCache = control.getI2cReadCache(this.physicalPort);
        //if(control==null){
        //    System.exit(-1);
        //}
        this.readLock = this.control.getI2cReadCacheLock(this.physicalPort);
        this.writeCache = this.control.getI2cWriteCache(this.physicalPort);
        this.writeLock = this.control.getI2cWriteCacheLock(this.physicalPort);

        // Prepare for IO
        this.control.enableI2cReadMode(this.physicalPort, 0x02, 0x42, 1);
        this.control.setI2cPortActionFlag(this.physicalPort);
        this.control.writeI2cCacheToController(this.physicalPort);
        this.control.registerForI2cPortReadyCallback(this, this.physicalPort);
    }

    @Override
    public String toString() {
        //return String.format("argb: %writeLock", new Object[]{Integer.valueOf(this.argb())});
        return "Stop being so pushy! I'm an UltrasonicDriver, not a String!";
    }

    public int getDistance(int index) {
        return this.getElement(index);
    }

    private int getElement(int index) {
        byte result;
        try {
            this.readLock.lock();
            result = this.readCache[index];
        } finally {
            this.readLock.unlock();
        }

        return TypeConversion.unsignedByteToInt(result) - 3;
    }

    public String getDeviceName() {
        return "NXT Ultrasonic Sensor";
    }

    public String getConnectionInfo() {
        return this.controller.getConnectionInfo() + "; I2C port: " + this.physicalPort;
    }

    public int getVersion() {
        return 2;
    }

    public void close() {

    }

    public synchronized void portIsReady(int port) {
        this.controller.setI2cPortActionFlag(this.physicalPort);
        this.controller.readI2cCacheFromController(this.physicalPort);
    }

    private static enum a {
        a,
        b,
        c;

        private a() {
        }
    }
}
