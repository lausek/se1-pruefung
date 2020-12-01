package de.dhbw.station;

import de.dhbw.employee.Inspector;

public class OperatingStation {

	private CardReader cardReader;
	private Inspector inspector;
	private BaggageScanner baggageScanner;

	public OperatingStation() {
	}

	public Inspector getInspector() {
		return inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}

	public CardReader getCardReader() {
		return cardReader;
	}
}
