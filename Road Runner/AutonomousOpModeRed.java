package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Autonomous_Red")
public class AutonomousOpModeRed extends LinearOpMode {

    private DcMotor motorFrontLeft = null;
    private DcMotor motorBackLeft = null;
    private DcMotor motorFrontRight = null;
    private DcMotor motorBackRight = null;

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontL");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackL");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontR");
        motorBackRight = hardwareMap.dcMotor.get("motorBackR");

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();

        LeftTime(0.8, 1000);
    }

//    public void MoveLeft(double power){
//        motorFrontRight.setPower(power);
//        motorBackLeft.setPower(power);
//    }
//    public void MoveLeftTime(double power, long time) throws InterruptedException {
//        MoveLeft(power);
//        Thread.sleep(time);
//    }
  public void DriveForward(double power)  {motorFrontLeft.setPower(power);

       motorFrontRight.setPower(power);
        motorBackRight.setPower(power);
        motorBackLeft.setPower(power);
    }
    public void DriveForwardTime(double power, long time) throws InterruptedException {
        DriveForward(power);
        Thread.sleep(time);

    }

    public void TurnLeft(double power) {
        motorFrontRight.setPower(power);
        motorBackLeft.setPower(power);
    }

    public void TurnLeftTime(double power, long time) throws InterruptedException {
        TurnLeft(power);
        Thread.sleep(time);

    }

    public void TurnRight(double power) {
        motorFrontLeft.setPower(power);
        motorBackRight.setPower(power);
    }

    public void TurnRightTime(double power, long time) throws InterruptedException {
        TurnRight(power);
        Thread.sleep(time);

    }

    public void Left(double power) {
        motorFrontRight.setPower(power);
        motorBackLeft.setPower(power);
        motorFrontLeft.setPower(-power);
        motorBackRight.setPower(-power);
    }

    public void LeftTime(double power, long time) throws InterruptedException {
        Left(power);
        Thread.sleep(time);
    }

    public void Right(double power) {
        motorFrontRight.setPower(-power);
        motorBackLeft.setPower(-power);
        motorFrontLeft.setPower(power);
        motorBackRight.setPower(power);
    }

    public void RightTime(double power, long time) throws InterruptedException {
        Right(power);
        Thread.sleep(time);
    }

}
