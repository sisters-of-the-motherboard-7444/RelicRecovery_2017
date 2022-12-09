package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;



@Autonomous(name= "ONLYColorSensorBLUE", group="Autonomous")
@Disabled
public class ONLYColorSensorBlue extends LinearOpMode {

    HardwareOurRobot Sebastian = new HardwareOurRobot();

    ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {

        Sebastian.initializeRobot(hardwareMap);
        telemetry.addData("robot:", "initialized");
        telemetry.update();
        Sebastian.initializeColorSensor();
        telemetry.addData("sensor:", "initialized");
        telemetry.update();


        waitForStart();

        //Sebastian.grabAndLiftGlyph();

        Sebastian.lowerColorSensor();

        String returnValues = "NONE";

        runtime.reset();
        while (returnValues.equals("NONE") && runtime.milliseconds() <= 3000 && opModeIsActive()) {

            returnValues = Sebastian.senseColor(returnValues);
            telemetry.update();
        }

        sleep(1500);


        if (returnValues.equals("RED")) {

            telemetry.addData("Color: ", "Red");
            telemetry.update();

            sleep(500);

            Sebastian.driveByTime(0.5, -0.5, 750);
            Sebastian.colorServo.setPosition(0.5);
            Sebastian.driveByTime(-0.5, 0.5, 750);

            Sebastian.colorServo.setPosition(0.5);

            sleep(1000);

        } else if (returnValues.equals("BLUE")) {

            telemetry.addData("Color: ", "Blue");
            telemetry.update();

            Sebastian.driveByTime(0.5, 0.5, 950);
            Sebastian.colorServo.setPosition(0.5);
            //Sebastian.driveByTime(-0.5, -0.5, 750);

            Sebastian.colorServo.setPosition(0.5);

            sleep(1000);

        } else if (returnValues.equals("NONE")) {

            telemetry.addData("Color: ", "Not Read");
            telemetry.update();

            sleep(1000);

            Sebastian.colorServo.setPosition(0.5);

            sleep(1000);
        }

        Sebastian.driveByTime(-0.5, -0.5, 1000);

    }

}
