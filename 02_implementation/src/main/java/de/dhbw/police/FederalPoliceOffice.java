package de.dhbw.police;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.RandomUtils;
import de.dhbw.card.CardType;
import de.dhbw.card.IDCard;
import de.dhbw.card.ProfileType;

public class FederalPoliceOffice {
	
	private Roboter[] roboter;
	private List<FederalPoliceOfficer> federalPoliceOfficers;

	public FederalPoliceOffice() {
		this.roboter = new Roboter[] { new Roboter(), new Roboter(), new Roboter() };
		this.federalPoliceOfficers = new ArrayList<>();
		
		this.federalPoliceOfficers.add(new FederalPoliceOfficer("Wesley Snipes", this));
		this.federalPoliceOfficers.add(new FederalPoliceOfficer("Toto", this));
		this.federalPoliceOfficers.add(new FederalPoliceOfficer("Harry", this));
	}
	
	public Roboter getRoboter() {
		int idx = RandomUtils.nextInt(this.roboter.length);
		return roboter[idx];
	}

	public List<FederalPoliceOfficer> getOfficers() {
		return this.federalPoliceOfficers;
	}
}
