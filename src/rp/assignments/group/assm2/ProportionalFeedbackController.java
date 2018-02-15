package rp.assignments.group.assm2;

import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import rp.util.Rate;

public class ProportionalFeedbackController extends Thread {
	
	private static final int SLEEP_DURATION = 2;
	private static final float SPEED_MULTIPLIER = 100.0f;
	
	private DifferentialPilot pilot;
	private UltrasonicSensor sensor;
	private int targetDistance;
	private boolean running;
	
	public ProportionalFeedbackController(DifferentialPilot pilot, UltrasonicSensor sensor, int targetDistance) {
		this.pilot = pilot;
		this.sensor = sensor;
		this.targetDistance = targetDistance;
	}
	
	public void stopRunning() {
		running = false;
	}
	
	
	@Override
	public void run() {
		running = true;
		Rate rate = new Rate(SLEEP_DURATION);
		pilot.setTravelSpeed(10.0);
		pilot.forward();
		
		while (running && !isInterrupted()) {
			double speed = (sensor.getDistance() - targetDistance) * SPEED_MULTIPLIER;
			if (speed < 0)
				speed = 0;
			else if (speed > pilot.getMaxTravelSpeed())
				speed = pilot.getMaxTravelSpeed();
			
			pilot.setTravelSpeed(speed);
			
			System.out.println(pilot.getTravelSpeed());
			rate.sleep();
		}
		pilot.stop();
	}
}
