package de.dhbw.police;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.RandomUtils;

public class FederalPoliceOffice {
	
	private Roboter[] roboter;
	private List<FederalPoliceOfficer> federalPoliceOfficers;

	public FederalPoliceOffice() {
		this.roboter = new Roboter[] { new Roboter(), new Roboter(), new Roboter() };
		this.federalPoliceOfficers = new ArrayList<>();
		
		this.federalPoliceOfficers.add(new FederalPoliceOfficer("Wesley Snipes", "1962-07-31", this));
		this.federalPoliceOfficers.add(new FederalPoliceOfficer("Toto", "1969-01-01", this));
		this.federalPoliceOfficers.add(new FederalPoliceOfficer("Harry", "1969-01-01", this));
	}
	
	public Roboter getRoboter() {
		int idx = RandomUtils.nextInt(this.roboter.length);
		return roboter[idx];
	}

	public List<FederalPoliceOfficer> getOfficers() {
		return this.federalPoliceOfficers;
	}
}
