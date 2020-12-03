package de.dhbw.employee;

import de.dhbw.card.IDCard;
import de.dhbw.station.Supervision;

public class Supervisor extends Employee {

	private Supervision supervision;
	private boolean isSenior;
	private boolean isExecutive;

	public Supervisor(String name, IDCard idCard) {
		super(name, idCard);
	}
}
