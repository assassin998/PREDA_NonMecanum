package org.firstinspires.ftc.teamcode.Roaryx7.opmode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class drive extends LinearOpMode {
    private DcMotor FL;
    private DcMotor FR;
    private DcMotor BL;
    private DcMotor BR;

    @Override
    public void runOpMode() throws InterruptedException {

        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while(opModeIsActive()) {
            float front; float rotate; float limiter;
            front = gamepad1.left_stick_y;
            rotate = gamepad1.right_stick_x;

            /*if(rotate > 0.5) {
                rotate = 0.5f;
            } else if (rotate < -0.5) {
                rotate = -0.5f;
            } */

            rotate /= 2;

            if(!gamepad1.right_bumper) {
                limiter = 0.6f;
            } else {
                limiter = 1;
            }

            float divider; divider = Math.max( Math.max(front - rotate, front + rotate), Math.max(-front + rotate, -front - rotate));
            
            if(divider <= 1) {
                divider = 1;
            }

            if(!gamepad1.left_bumper) {
                FL.setPower(((front + rotate) / divider) * limiter);
                FR.setPower(((front - rotate) / divider) * limiter);
                BL.setPower(((front + rotate) / divider) * limiter);
                BR.setPower(((front - rotate) / divider) * limiter);
            } else {
                FL.setPower(((front + rotate) / divider) * limiter);
                FR.setPower(((front - rotate) / divider) * limiter);
                BL.setPower(limiter);
                BR.setPower(limiter);

            }
        }
    }
}
