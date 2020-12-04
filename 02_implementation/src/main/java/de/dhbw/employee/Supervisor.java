package de.dhbw.employee;

import java.time.LocalDateTime;

import de.dhbw.card.IDCard;
import de.dhbw.station.Supervision;

public class Supervisor extends Employee {

	private Supervision supervision;
	private boolean isSenior;
	private boolean isExecutive;

	public Supervisor(String name, String birthDate) {
		super(name, birthDate, IDCard.createSupervisorCard("1234"));
	}

	public Supervisor(String name, String birthDate, IDCard idCard) {
		super(name, birthDate, idCard);
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
