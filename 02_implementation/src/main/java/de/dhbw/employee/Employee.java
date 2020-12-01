package de.dhbw.employee;

import java.time.LocalDateTime;

import de.dhbw.card.IDCard;

public abstract class Employee {

	private IDCard iDCard;
	protected int id;
	protected String name;
	protected LocalDateTime birthDate;

	public Employee() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IDCard getIDCard() {
		return iDCard;
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getBirthDate() {
		return birthDate;
	}
}
