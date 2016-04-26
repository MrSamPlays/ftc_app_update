/**
 * <h2>
 *This is a demo <br> This is also a basic Teleop Program <br>
 * </h2>
 *
 */

package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.*;
// import com.qualcomm.robotcore.util.*;
import com.qualcomm.robotcore.hardware.*;

public class DemoTeleOp extends OpMode {
    public DcMotor Left;
    public DcMotor Right;
    public DcMotorController dcm;
    RobotMethodLibraries rml = new RobotMethodLibraries();
    Servo srv;

    /*
     * Code to run when the op mode is first enabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
    @Override
    public void init() {
/*  startDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    runtime.reset()
      */
        dcm = hardwareMap.dcMotorController.get("Motor_controller_1");
        Left = hardwareMap.dcMotor.get("Motor_1");
        Right = hardwareMap.dcMotor.get("Motor_2");
        Right.setDirection(DcMotor.Direction.REVERSE);
        srv.setPosition(127);
        Left.setPower(0);
        Right.setPower(0);

    }
    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override
    public void loop() {
        double Lthrottle = -gamepad1.left_stick_y;
        double Rthrottle = -gamepad1.right_stick_y;

        Left.setPower(rml.scaleInput(Lthrottle));
        Right.setPower(rml.scaleInput(Rthrottle));
    }
    public void stop() {
        Left.setPower(0);
        Right.setPower(0);
    }

    /**
     * TeleOp Mode
     * <p>
     * Enables control of the robot via the gamepad
     * </p>
     */

}
