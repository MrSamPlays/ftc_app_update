package com.qualcomm.ftcrobotcontroller.math;

public class Converter {
   public static double clicksToRad(int clicks) {
       double rotations = clicks/1120;
       double rad = rotations/(2 * Math.PI);
       return rad;
   }

    public static double degreesToRad(double degrees) {
        return Math.toRadians(degrees);
    }
}
