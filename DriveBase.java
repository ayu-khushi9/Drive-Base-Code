package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class DriveBase extends LinearOpMode {
    
    private DcMotor motorFrontLeft = null;
    private DcMotor motorBackLeft = null;
    private DcMotor motorFrontRight = null;
    private DcMotor motorFrontRight = null;
    
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontL");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackL");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontR");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackR");

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
//For joystick, NA right now
           /* double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;
            double lt = gamepad1.left_trigger;
            double rt = gamepad1.right_trigger;
           */
           //Initializes power variable, which is set to 1 throughout but can be changed if necessary in code
           double power = 1;
           double power2;
           //booleans for if dpad or bumper is being pressed
           boolean dpad_up = gamepad1.dpad_up;
           boolean dpad_down = gamepad1.dpad_down;
           boolean dpad_left = gamepad1.dpad_left;
           boolean dpad_right = gamepad1.dpad_right;
           boolean bumper_left = gamepad1.left_bumper;
           boolean bumper_right = gamepad1.right_bumper;
           
           double frontLeftPower;
           double frontRightPower;
           double backLeftPower;
           double backRightPower;
           
           //sets power values based on what is being pressed
           if (dpad_up) {
           power = 1;
           frontLeftPower = power;
           frontRightPower = power;
           backLeftPower = power;
           backRightPower = power;
           }
           else if (dpad_down) {
           power = 1;
           frontLeftPower = -power;
           frontRightPower = power;
           backLeftPower = -power;
           backRightPower = power;
           }
           else if (dpad_left) {
           power = 1;
           frontLeftPower = -power;
           frontRightPower = power;
           backLeftPower = power;
           backRightPower = -power;
           }
           else if (dpad_right) {
           power = 1;
           frontLeftPower = power;
           frontRightPower = -power;
           backLeftPower = -power;
           backRightPower = power;
           } else if (bumper_left) {
           power = 1;
           frontLeftPower = -power;
           frontRightPower = power;
           backLeftPower = -power;
           backRightPower = power;
           } else if (bumper_right) {
           power = 1;
           frontLeftPower = power;
           frontRightPower = -power;
           backLeftPower = power;
           backRightPower = -power;
           } else {
           quit;
           }
            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when    < this sections is for joystick
            // at least one is out of the range [-1, 1]
           
           //joystick
           /* double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            */
            
            //sets powers of motors
            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
        }
    }
}
