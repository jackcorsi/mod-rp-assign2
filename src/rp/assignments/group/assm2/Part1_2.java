package rp.assignments.group.assm2;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import rp.config.RobotConfigs;
import rp.config.WheeledRobotConfiguration;
import rp.systems.WheeledRobotSystem;

public class Part1_2 {
	
	private static final SensorPort RIGHT_SENSOR_PORT = SensorPort.S4;
	private static final SensorPort LEFT_SENSOR_PORT = SensorPort.S1;
	private static final WheeledRobotConfiguration TAYYAB_CONFIG = RobotConfigs.EXPRESS_BOT;

	public static void main(String[] args) {
		System.out.println("Assignment 2 part 2 of part 1");
		System.out.println("Press any button");
		Button.waitForAnyPress();
		DifferentialPilot pilot = (new WheeledRobotSystem(TAYYAB_CONFIG)).getPilot();
		LightSensor rightSensor = new LightSensor(RIGHT_SENSOR_PORT);
		LightSensor leftSensor = new LightSensor(LEFT_SENSOR_PORT);
		
		leftSensor.setFloodlight(false);
		rightSensor.setFloodlight(false);
		
		//Perform calibration
//		System.out.println("Press to calibrate high left value");
//		Button.waitForAnyPress();
//		leftSensor.calibrateHigh();
//		System.out.println("Press to calibrate low left value");
//		Button.waitForAnyPress();
//		leftSensor.calibrateLow();
//		System.out.println("Press to calibrate high right value");
//		Button.waitForAnyPress();
//		rightSensor.calibrateHigh();
//		System.out.println("Press to calibrate low right value");
//		Button.waitForAnyPress();
//		rightSensor.calibrateLow();
		
		//Manual calibration values
		rightSensor.setHigh(250);
		rightSensor.setLow(230);
		leftSensor.setHigh(200);
		leftSensor.setLow(200);
		
		System.out.println("Press to start");
		Button.waitForAnyPress();
		
		LineController controller = new LineController(pilot, rightSensor, leftSensor);
		controller.setDaemon(true); //Just in case it goes rogue and doesn't die for some reason
		controller.start();
		Button.waitForAnyPress();
		controller.stopController();
	}

}
