package de.dhbw.employee;

import de.dhbw.baggage.HandBaggage;
import de.dhbw.station.ManualPostControl;
import de.dhbw.station.Tray;

public class Inspector extends Employee {

	private ManualPostControl manualPostControl;
	private boolean isSenior;

	public Inspector() {
	}

	public void push(Tray tray) {}
	public void swipe(HandBaggage handBaggage) {}
}
