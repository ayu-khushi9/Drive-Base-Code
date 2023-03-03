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
    private DcMotor armMotor1 = null;
    private DcMotor armMotor2 = null;

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontL");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackL");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontR");
        motorBackRight = hardwareMap.dcMotor.get("motorBackR");
        armMotor1 = hardwareMap.dcMotor.get("armMotor1");
        armMotor2 = hardwareMap.dcMotor.get("armMotor2");
        clawServo = hardwareMap.servo.get("clawServo");
        ticksperRotation = armMotor1.getMotorType().getTicksPerRev();
        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.FORWARD);

        armMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        armMotor2.setDirection(DcMotorSimple.Direction.REVERSE);

        armMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawServo.setPosition(1);

        clawServo.scaleRange(0, 1);
        int distance = 0;
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            //


            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;
            boolean dpad_up = gamepad1.dpad_up;
            boolean dpad_down = gamepad1.dpad_down;
            boolean dpad_left = gamepad1.dpad_left;
            boolean dpad_right = gamepad1.dpad_right;
            boolean bumper_left = gamepad1.left_bumper;
            boolean bumper_right = gamepad1.right_bumper;

            double limit1 = 1.6;
            double limit2 = 1.1;
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
            if(gamepad1.left_bumper) {
                frontLeftPower = frontLeftPower * .4;
                frontRightPower = frontRightPower * .4;
                backLeftPower = backLeftPower * .4;
                backRightPower = backRightPower * .4;
            }
            if(dpad_up) {
                moveArm(.5, 2400);
                distance = 2400;
            }
            if(dpad_left) {
                moveArm(.5, 1600);
                distance = 1600;
            }
            if(dpad_right) {
                moveArm(.5, 1200);
                distance = 1200;
            }
            if(dpad_down) {
                moveArm(-.5, -distance);
                distance = 300;
            }

            //armMotor1.setPower(.1);
            //armMotor2.setPower(.1);
            if(gamepad1.a){
                clawServo.setPosition(.8);
            }
            if(gamepad1.b){
                clawServo.setPosition(1);
            }

            /*
            boolean blue = gamepad1.x;
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
            }

            */
            //sets powers of motors
            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
        }
    }
    public void moveArm(double power, int distance) {
        armMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor1.setTargetPosition(distance);
        armMotor2.setTargetPosition(distance);
        armMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor1.setPower(power);
        armMotor2.setPower(power);

        while(armMotor1.isBusy() || armMotor2.isBusy()) {
            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            double limit1 = 1.6;
            double limit2 = 1;
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
            if(gamepad1.left_bumper) {
                frontLeftPower = frontLeftPower * .4;
                frontRightPower = frontRightPower * .4;
                backLeftPower = backLeftPower * .4;
                backRightPower = backRightPower * .4;
            }
            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
        }
        armMotor1.setPower(0);
        armMotor2.setPower(0);
    }


}
