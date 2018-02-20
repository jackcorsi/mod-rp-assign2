package rp.assignments.group.assm2;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import rp.config.RobotConfigs;
import rp.config.WheeledRobotConfiguration;
import rp.systems.WheeledRobotSystem;

public class Part1_Turn {
	
	private static final SensorPort RIGHT_SENSOR_PORT = SensorPort.S4;
	private static final SensorPort LEFT_SENSOR_PORT = SensorPort.S1;
	private static final WheeledRobotConfiguration TAYYAB_CONFIG = RobotConfigs.EXPRESS_BOT;
	private static final int LEFT_HIGH = 400;
	private static final int LEFT_LOW = 320;
	private static final int RIGHT_HIGH = 410;
	private static final int RIGHT_LOW = 320;

	public static void main(String[] args) {
		System.out.println("Assignment 2 part 2 of part 1");
		System.out.println("Press any button");
		Button.waitForAnyPress();
		DifferentialPilot pilot = (new WheeledRobotSystem(TAYYAB_CONFIG)).getPilot();
		LightSensor rightSensor = new LightSensor(RIGHT_SENSOR_PORT);
		LightSensor leftSensor = new LightSensor(LEFT_SENSOR_PORT);
		
		leftSensor.setFloodlight(true);
		rightSensor.setFloodlight(true);
		
		//Manual calibration values
		rightSensor.setHigh(RIGHT_HIGH);
		rightSensor.setLow(RIGHT_LOW);
		leftSensor.setHigh(LEFT_HIGH);
		leftSensor.setLow(LEFT_LOW);
		
		LineController controller = new LineController(pilot, rightSensor, leftSensor);
		controller.setDaemon(true); //Just in case it goes rogue and doesn't die for some reason
		controller.start();
		Button.waitForAnyPress();
		controller.stopController();
	}

}
