package de.dhbw.employee;

import de.dhbw.baggage.HandBaggage;
import de.dhbw.card.IDCard;
import de.dhbw.station.ManualPostControl;
import de.dhbw.station.OperatingStation;
import de.dhbw.station.RollerConveyor;

public class Inspector extends Employee {

	private ManualPostControl manualPostControl;
	private RollerConveyor rollerConveyor;
	private OperatingStation operatingStation;
	private boolean isSenior;

	public Inspector(String name, IDCard idCard, boolean isSenior) {
		super(name, idCard);
		this.isSenior = isSenior;
	}

	public char[] swipe(HandBaggage handBaggage) {
		return null;
	}

	public void duPeddaPassUffDieTypeIsNichSauberIkHabDaNMesserJefunden() { }
}
