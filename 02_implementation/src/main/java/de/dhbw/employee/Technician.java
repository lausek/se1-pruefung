package de.dhbw.employee;

import de.dhbw.card.IDCard;

public class Technician extends Employee {

	public Technician(String name) {
		super(name, IDCard.createTechnicianCard("1234"));
	}

	public Technician(String name, IDCard idCard) {
		super(name, idCard);
	}
}
