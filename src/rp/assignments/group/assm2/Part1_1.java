package rp.assignments.group.assm2;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import rp.config.RobotConfigs;
import rp.config.WheeledRobotConfiguration;
import rp.systems.WheeledRobotSystem;

public class Part1_1 {
	
	private static final int TARGET_DISTANCE = 70;
	private static final WheeledRobotConfiguration TAYYAB_CONFIG = RobotConfigs.EXPRESS_BOT;
	private static final SensorPort sensorPort = SensorPort.S2;

	public static void main(String[] args) {
		System.out.println("Assignment 2 part 1 of part 1");
		System.out.println("Press any button");
		Button.waitForAnyPress();
		DifferentialPilot pilot = (new WheeledRobotSystem(TAYYAB_CONFIG)).getPilot();
		UltrasonicSensor sensor = new UltrasonicSensor(sensorPort);
		ProportionalFeedbackController controller = new ProportionalFeedbackController(pilot, sensor, TARGET_DISTANCE);
		controller.setDaemon(true); //Just in case it goes rogue and doesn't die for some reason
		controller.start();
		Button.waitForAnyPress();
		controller.stopRunning();
	}

}
