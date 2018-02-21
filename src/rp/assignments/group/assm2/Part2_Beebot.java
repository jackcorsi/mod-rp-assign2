package rp.assignments.group.assm2;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.robotics.navigation.DifferentialPilot;
import rp.config.RobotConfigs;
import rp.config.WheeledRobotConfiguration;
import rp.systems.WheeledRobotSystem;

public class Part2_Beebot {

	private static final SensorPort RIGHT_SENSOR_PORT = SensorPort.S4;
	private static final SensorPort LEFT_SENSOR_PORT = SensorPort.S1;
	private static final WheeledRobotConfiguration TAYYAB_CONFIG = RobotConfigs.EXPRESS_BOT;
	private static final int LEFT_HIGH = 400;
	private static final int LEFT_LOW = 320;
	private static final int RIGHT_HIGH = 410;
	private static final int RIGHT_LOW = 320;

	private static final int LEFT_ID = Button.LEFT.getId();
	private static final int RIGHT_ID = Button.RIGHT.getId();
	private static final int ENTER_ID = Button.ENTER.getId();
	private static final int ESCAPE_ID = Button.ESCAPE.getId();

	public static void main(String[] args) {
		System.out.println("Assignment 2 part 1 of part 2");
		System.out.println("Press any button");
		Button.waitForAnyPress();
		DifferentialPilot pilot = (new WheeledRobotSystem(TAYYAB_CONFIG)).getPilot();
		LightSensor rightSensor = new LightSensor(RIGHT_SENSOR_PORT);
		LightSensor leftSensor = new LightSensor(LEFT_SENSOR_PORT);
		
		List <Integer> moves = programInstructions();

		
		leftSensor.setFloodlight(true);
		rightSensor.setFloodlight(true);

		// Manual calibration values
		rightSensor.setHigh(RIGHT_HIGH);
		rightSensor.setLow(RIGHT_LOW);
		leftSensor.setHigh(LEFT_HIGH);
		leftSensor.setLow(LEFT_LOW);

		BeebotController controller = new BeebotController(pilot, rightSensor, leftSensor, moves);
		controller.setDaemon(true); // Just in case it goes rogue and doesn't
									// die for some reason
		controller.start();
		Button.waitForAnyPress();
		controller.stopController();
		
	}

	public static List <Integer> programInstructions() {
		ArrayList<Integer> list = new ArrayList<Integer>();

		int num = 0;

		while (true) {
			int id = Button.waitForAnyPress();
			if (id == LEFT_ID) {
				num++;
				Sound.beep();
			}
			else if (id == RIGHT_ID) {
				num--;
				Sound.buzz();
			}	
			else if (id == ENTER_ID) {
				list.add(num);
				num = 0;
				Sound.twoBeeps();
			} else if (id == ESCAPE_ID) {
				Sound.beepSequence();
				break;
			}
		}
		System.out.println(list.size());
		Button.waitForAnyPress();
		return list;
	}

}
