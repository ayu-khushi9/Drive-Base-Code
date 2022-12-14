package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "TeleOp Test")
public class TeleTestDrve extends LinearOpMode {
    private DcMotor motorFrontLeft = null;
    private DcMotor motorBackLeft = null;
    private DcMotor motorFrontRight = null;
    private DcMotor motorBackRight = null;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontL");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackL");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontR");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackR");

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            boolean dpad_up = gamepad1.dpad_up;
            if(dpad_up) {
            motorFrontLeft.setPower(1);
            motorFrontRight.setPower(1);
                }
            }
        }
    }

