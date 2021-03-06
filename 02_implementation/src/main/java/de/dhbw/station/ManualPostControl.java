package de.dhbw.station;

import de.dhbw.employee.Inspector;

public class ManualPostControl {

	private BaggageScanner baggageScanner;
	private Inspector inspector;

	public ManualPostControl(BaggageScanner baggageScanner) {
		this.baggageScanner = baggageScanner;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}

	public BaggageScanner getBaggageScanner() {
		return baggageScanner;
	}
}
