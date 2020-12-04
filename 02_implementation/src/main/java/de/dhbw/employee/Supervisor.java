package de.dhbw.employee;

import de.dhbw.card.IDCard;
import de.dhbw.station.Supervision;

public class Supervisor extends Employee {

	private Supervision supervision;
	private boolean isSenior;
	private boolean isExecutive;

	public Supervisor(String name) {
		super(name, IDCard.createSupervisorCard("1234"));
	}

	public Supervisor(String name, IDCard idCard) {
		super(name, idCard);
	}

	public boolean isExecutive() {
		return isExecutive;
	}

	public boolean isSenior() {
		return isSenior;
	}

	public Supervision getSupervision() {
		return supervision;
	}
}
