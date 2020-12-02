package de.dhbw.police;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FederalPoliceOffice {
	
	private static Random random = new Random();

	private Roboter[] roboter;
	private List<FederalPoliceOfficer> federalPoliceOfficers;

	public FederalPoliceOffice() {
		this.roboter = new Roboter[] { new Roboter(), new Roboter(), new Roboter() };
		this.federalPoliceOfficers = new ArrayList<>();
		
		for(int i = 0; i < 3; i++) {
			this.federalPoliceOfficers.add(new FederalPoliceOfficer(this));
		}
	}
	
	public Roboter getRoboter() {
		int idx = random.nextInt(this.roboter.length);
		return roboter[idx];
	}

	public List<FederalPoliceOfficer> getOfficers() {
		return this.federalPoliceOfficers;
	}
}
