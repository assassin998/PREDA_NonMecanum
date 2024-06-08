package org.firstinspires.ftc.teamcode.Roaryx7.opmode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class drive extends LinearOpMode {
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;

    @Override
    public void runOpMode() throws InterruptedException {

        fl = hardwareMap.get(DcMotor.class, "FLM");
        fr = hardwareMap.get(DcMotor.class, "FRM");
        bl = hardwareMap.get(DcMotor.class, "BLM");
        br = hardwareMap.get(DcMotor.class, "BRM");
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while(opModeIsActive()) {
            float x; float r; float l;
            x = gamepad1.left_stick_x;
            r = gamepad1.right_stick_y;

            if(r > 0.5) {
                r = 0.5f;
            } else if (r < -0.5) {
                r = -0.5f;
            }

            if(!gamepad1.right_bumper) {
                l = 0.6f;
            } else {
                l = 1;
            }

            float d; d = Math.max( Math.max(x-r, x+r), Math.max(-x+r, -x-r));
            fl.setPower(((x+r)/d)*l);
            fr.setPower(((x-r)/d)*l);
            bl.setPower(((x+r)/d)*l);
            br.setPower(((x-r)/d)*l);

        }
    }
}
