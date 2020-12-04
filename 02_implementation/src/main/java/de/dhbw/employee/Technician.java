package de.dhbw.employee;

import java.time.LocalDateTime;

import de.dhbw.card.IDCard;

public class Technician extends Employee {

	public Technician(String name, String birthDate) {
		super(name, birthDate, IDCard.createTechnicianCard("1234"));
	}

	public Technician(String name, String birthDate, IDCard idCard) {
		super(name, birthDate, idCard);
	}
}
