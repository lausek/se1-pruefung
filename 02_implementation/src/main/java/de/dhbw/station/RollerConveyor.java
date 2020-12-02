package de.dhbw.station;

import de.dhbw.employee.Inspector;

public class RollerConveyor {

	private Inspector inspector;
	private BaggageScanner baggageScanner;

	public RollerConveyor(BaggageScanner baggageScanner) {
		this.baggageScanner = baggageScanner;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}
	
	public void push(Tray tray) {
		this.baggageScanner.getBelt().push(tray);
	}
}
