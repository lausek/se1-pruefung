package de.dhbw.employee;

import de.dhbw.baggage.HandBaggage;
import de.dhbw.station.ManualPostControl;
import de.dhbw.station.OperatingStation;
import de.dhbw.station.RollerConveyor;
import de.dhbw.station.Tray;

public class Inspector extends Employee {

	private ManualPostControl manualPostControl;
	private RollerConveyor rollerConveyor;
	private OperatingStation operatingStation;
	private boolean isSenior;

	public Inspector() {
	}

	public void push(Tray tray) {}
	public void swipe(HandBaggage handBaggage) {}
}
