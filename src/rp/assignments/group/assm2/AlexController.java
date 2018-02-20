package rp.assignments.individual.ex2;

import java.time.Duration;
import java.time.Instant;

import lejos.robotics.RangeFinder;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import rp.config.RangeFinderDescription;
import rp.robotics.DifferentialDriveRobot;
import rp.systems.StoppableRunnable;
import rp.robotics.simulation.SimulationSteppable;

public class RangeController implements StoppableRunnable {//, SimulationSteppable {

	private boolean m_run = false;
	private DifferentialDriveRobot robot;
	private DifferentialPilot pilot;
	private RangeFinderDescription desc;
	private RangeFinder ranger;
	private Float maxDistance;
	
	public RangeController(DifferentialDriveRobot _robot, RangeFinderDescription _desc, RangeFinder _ranger,
			Float _maxDistance) {
		this.robot = _robot;
		this.pilot = this.robot.getDifferentialPilot();
		this.desc = _desc;
		this.ranger = _ranger;
		this.maxDistance = _maxDistance;		
	}

	@Override
	public void run() {
		m_run = true;
		pilot.forward();
		pilot.setTravelSpeed(pilot.getMaxTravelSpeed());
		while(m_run) {
			Delay.msDelay(50);
			if(ranger.getRange()-maxDistance<0) {
				pilot.setTravelSpeed(0.01);
			}
			else {
				pilot.setTravelSpeed(pilot.getMaxTravelSpeed());
			}	
		}
	}

	@Override
	public void stop() {
		m_run = false;
		pilot.stop();
	}

}
