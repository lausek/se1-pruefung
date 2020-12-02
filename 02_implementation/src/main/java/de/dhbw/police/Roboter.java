package de.dhbw.police;

import de.dhbw.baggage.HandBaggage;

public class Roboter {

	private FederalPoliceOfficer federalPoliceOfficer;

	public Roboter() { }
	
	public void destroy(HandBaggage handBaggage) { }

	public FederalPoliceOfficer getFederalPoliceOfficer() {
		return this.federalPoliceOfficer;
	}

	public void setFederalPoliceOfficer(FederalPoliceOfficer officerO2) {
		this.federalPoliceOfficer = officerO2;
	}
}
