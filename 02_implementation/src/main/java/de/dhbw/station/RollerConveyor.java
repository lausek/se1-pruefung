package de.dhbw.station;

import de.dhbw.employee.Inspector;

public class RollerConveyor {

	private Inspector inspector;
	private BaggageScanner baggageScanner;

	public RollerConveyor() {
	}

	public Inspector getInspector() {
		return inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}
}
