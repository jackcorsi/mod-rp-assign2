package rp.assignments.group.assm2;

import lejos.nxt.LightSensor;
import lejos.nxt.Sound;
import lejos.robotics.navigation.DifferentialPilot;
import rp.util.Rate;

public class LineController extends Thread {
	
	private static final int PROCESSING_RATE = 40;
	private static final double TRAVEL_SPEED = 0.1;
	private static final int TRIGGER_VALUE = 0; //TRIGGERED
	private static final double TURN_RATE = 70.0;
	
	private DifferentialPilot pilot;
	private LightSensor rightSensor;
	private LightSensor leftSensor;
	private boolean stopped = false;
	
	public LineController (DifferentialPilot pilot, LightSensor rightSensor, LightSensor leftSensor) {
		this.pilot = pilot;
		this.rightSensor = rightSensor;
		this.leftSensor = leftSensor;
		assert(TRAVEL_SPEED <= pilot.getMaxTravelSpeed());
	}
	
	@Override
	public void run() {
		stopped = false;
		Rate rate = new Rate(PROCESSING_RATE);
		pilot.steer(0.0);
		pilot.setTravelSpeed(TRAVEL_SPEED);
		
		while ( !(isInterrupted() || stopped) ) {
			boolean leftTrigger = (leftSensor.readValue() <= TRIGGER_VALUE);
			boolean rightTrigger = (rightSensor.readValue() <= TRIGGER_VALUE);
			
			System.out.println(leftSensor.readNormalizedValue() + " --- " + rightSensor.readNormalizedValue());
			Sound.setVolume(80);
			
			if (leftTrigger) {
				if (!rightTrigger) {
					//pilot.steer(TURN_RATE); //Turn right
					Sound.playTone(500, 1000 / PROCESSING_RATE);
				}
			} else if (rightTrigger) {
				//pilot.steer(-TURN_RATE); //Turn left
				Sound.playTone(1500, 1000 / PROCESSING_RATE);
			} else {
				//pilot.steer(0.0);
			}
			rate.sleep();
		}
	}
	
	public void stopController() {
		stopped = true;
	}
}
