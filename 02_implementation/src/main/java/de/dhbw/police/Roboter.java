package de.dhbw.police;

import de.dhbw.baggage.HandBaggage;

public class Roboter {

	private FederalPoliceOfficer federalPoliceOfficer;

	public Roboter() {
	}

	public String[] destroy(HandBaggage handBaggage) {
		String[] parts = new String[1000];
		StringBuilder s = new StringBuilder();

		for (int i = 0; i < 50; i++) {
			s.append(" ");
		}

		for (int i = 0; i < 1000; i++) {
			parts[i] = s.toString();
		}

		return parts;
	}

	public FederalPoliceOfficer getFederalPoliceOfficer() {
		return this.federalPoliceOfficer;
	}

	public void setFederalPoliceOfficer(FederalPoliceOfficer officerO2) {
		this.federalPoliceOfficer = officerO2;
	}
}
