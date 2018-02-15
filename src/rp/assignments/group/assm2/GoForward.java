package rp.assignments.group.assm2;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import rp.config.RobotConfigs;
import rp.config.WheeledRobotConfiguration;
import rp.systems.WheeledRobotSystem;

public class GoForward {

	private static final WheeledRobotConfiguration TAYYAB_CONFIG = RobotConfigs.EXPRESS_BOT;
	
	public static void main(String[] args) {
		Button.waitForAnyPress();
		NXTRegulatedMotor left = Motor.A;
		NXTRegulatedMotor right = Motor.B;
		left.forward();
		right.forward();
		Button.waitForAnyPress();
		left.stop();
		right.stop();
		
	}

}
