package com.qualcomm.ftcrobotcontroller;

import com.qualcomm.ftcrobotcontroller.compass.Compass;

public class Robot {
    public static final double wheel_radius_cm = 5;
    public static final double length_cm = 45.4;
    public static final double width_cm = 43.1;
    public static  final double wheel_rot_for_full_turn_point = 6;
    public static  final double wheel_rot_for_full_turn_pivot = 10;

    public static double angle_difference_rad;

    public static double getAngle() {
        return Compass.currentDegreeRad - angle_difference_rad;
    }
}