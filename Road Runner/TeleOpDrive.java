package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "TeleOp_Mech")
public class TeleOpDrive extends LinearOpMode {

    private double ticksperRotation;
    private Servo clawServo = null;
    private DcMotor motorFrontLeft = null;
    private DcMotor motorBackLeft = null;
    private DcMotor motorFrontRight = null;
    private DcMotor motorBackRight = null;
    private DcMotor armMotor = null;

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontL");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackL");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontR");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackR");
        DcMotor armMotor = hardwareMap.dcMotor.get("armMotor");
        Servo clawServo = hardwareMap.servo.get("clawServo");
        ticksperRotation = armMotor.getMotorType().getTicksPerRev();
        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            //
            /* boolean dpad_up = gamepad1.dpad_up;
            boolean dpad_down = gamepad1.dpad_down;
            boolean dpad_left = gamepad1.dpad_left;
            boolean dpad_right = gamepad1.dpad_right;
            boolean bumper_left = gamepad1.left_bumper;
            boolean bumper_right = gamepad1.right_bumper;
            */

            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;
            boolean dpad_up = gamepad2.dpad_up;
            boolean dpad_down = gamepad2.dpad_down;
            boolean dpad_left = gamepad2.dpad_left;
            boolean dpad_right = gamepad2.dpad_right;

            double limit1 = 2;
            double limit2 = 1.2;
            double limit3 = 3;
            double frontLeftPower = (y + x + (rx/limit2)) / limit1;
            double backLeftPower = (y - x + (rx/limit2)) / limit1;
            double frontRightPower = (y - x - (rx/limit2)) / limit1;
            double backRightPower = (y + x - (rx/limit2)) / limit1;

            if(Math.abs(y) < 0.2 && Math.abs(x) < 0.2) {
                frontLeftPower = (y + x + (rx/limit2)) / limit3;
                backLeftPower = (y - x + (rx/limit2)) / limit3;
                frontRightPower = (y - x - (rx/limit2)) / limit3;
                backRightPower = (y + x - (rx/limit2)) / limit3;
            }

            if(dpad_up) {
                moveArm(.5, 800);
            }
            if(dpad_down) {
                moveArm(-.5, -800);
            }
            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);

            /*boolean blue = gamepad1.x;
            boolean yellow = gamepad1.y;
            boolean green = gamepad1.a;
            boolean red = gamepad1.b;
            if(blue){
                frontLeftPower = .5;
            }
            if(yellow){
                frontRightPower = .5;
            }
            if(green){
                backLeftPower= .5;
            }
            if(red){
                backRightPower = .5;
            }*/


            //sets powers of motors
            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
        }
    }
    public void moveArm(double power, int distance) {
        armMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
        armMotor.setTargetPosition(distance);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(power);
        while(armMotor.isBusy()) {

        }

        armMotor.setPower(0);
    }
}
