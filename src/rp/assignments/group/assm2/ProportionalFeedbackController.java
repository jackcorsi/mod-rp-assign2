package rp.assignments.group.assm2;

import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import rp.util.Rate;

public class ProportionalFeedbackController extends Thread {

	private DifferentialPilot pilot;
	private OpticalDistanceSensor sensor;
	private int targetDistance;
	private boolean running;

	public ProportionalFeedbackController(DifferentialPilot pilot, OpticalDistanceSensor sensor, int targetDistance) {
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
		pilot.forward();
		pilot.setTravelSpeed(pilot.getMaxTravelSpeed());
		while (running) {
			Delay.msDelay(50);
			System.out.println(sensor.getDistance());
			if (sensor.getDistance() - targetDistance < 0) {
				pilot.setTravelSpeed(0.01);
			} else {
				pilot.setTravelSpeed(pilot.getMaxTravelSpeed());
			}
		}
		
		
		pilot.stop();

	}
}
