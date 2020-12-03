package de.dhbw.police;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.RandomUtils;
import de.dhbw.card.IDCard;

public class FederalPoliceOffice {
	
	private Roboter[] roboter;
	private List<FederalPoliceOfficer> federalPoliceOfficers;

	public FederalPoliceOffice() {
		this.roboter = new Roboter[] { new Roboter(), new Roboter(), new Roboter() };
		this.federalPoliceOfficers = new ArrayList<>();
		
		this.federalPoliceOfficers.add(new FederalPoliceOfficer("Wesley Snipes", new IDCard(), this));
		this.federalPoliceOfficers.add(new FederalPoliceOfficer("Toto", new IDCard(), this));
		this.federalPoliceOfficers.add(new FederalPoliceOfficer("Harry", new IDCard(), this));
	}
	
	public Roboter getRoboter() {
		int idx = RandomUtils.nextInt(this.roboter.length);
		return roboter[idx];
	}

	public List<FederalPoliceOfficer> getOfficers() {
		return this.federalPoliceOfficers;
	}
}
