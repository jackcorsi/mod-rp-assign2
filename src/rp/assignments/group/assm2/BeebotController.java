package rp.assignments.group.assm2;

import java.util.List;
import java.util.Random;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Sound;
import lejos.robotics.navigation.DifferentialPilot;
import rp.util.Rate;

public class BeebotController extends Thread {

	private static final int PROCESSING_RATE = 40;
	private static final double TRAVEL_SPEED = 0.1;
	private static final int TRIGGER_VALUE = 20; // TRIGGERED
	private static final double TURN_RATE = 200.0;
	private static final double OVERSHOOT = 0.1;

	private DifferentialPilot pilot;
	private LightSensor rightSensor;
	private LightSensor leftSensor;
	private boolean stopped = false;
	
	private List <Integer> moves;

	public BeebotController(DifferentialPilot pilot, LightSensor rightSensor, LightSensor leftSensor, List <Integer> moves) {
		this.pilot = pilot;
		this.rightSensor = rightSensor;
		this.leftSensor = leftSensor;
		this.moves = moves;
	}

	@Override
	public void run() {
		stopped = false;
		int currentMove = 0;
		Rate rate = new Rate(PROCESSING_RATE);
		pilot.setTravelSpeed(TRAVEL_SPEED);
		pilot.forward();
		
		while (!stopped) {
			
			boolean leftTrigger = (leftSensor.readValue() <= TRIGGER_VALUE);
			boolean rightTrigger = (rightSensor.readValue() <= TRIGGER_VALUE);

			System.out.println(leftSensor.readNormalizedValue() + " --- " + rightSensor.readNormalizedValue());
			Sound.setVolume(100);

			if (leftTrigger && !rightTrigger) {
				pilot.steer(TURN_RATE); // Turn right
				Sound.playTone(500, 1000 / PROCESSING_RATE);
			} else if (rightTrigger && !leftTrigger) {
				pilot.steer(-TURN_RATE); // Turn left
				Sound.playTone(1500, 1000 / PROCESSING_RATE);
			} else if (rightTrigger && leftTrigger) {
				if (currentMove >= moves.size()) {
					pilot.stop();
					pilot.travel(OVERSHOOT);
					Sound.beepSequence();
					
					Button.waitForAnyPress();
					return;
				}
				pilot.stop();
				pilot.travel(OVERSHOOT);
				pilot.rotate(90.0 * moves.get(currentMove));
				pilot.forward();
				currentMove++;
			} else {
				pilot.steer(0.0);
			}
			rate.sleep();
			
		}
		
	}

	public void stopController() {
		stopped = true;
	}
}
