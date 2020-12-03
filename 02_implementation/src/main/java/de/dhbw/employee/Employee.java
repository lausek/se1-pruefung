package de.dhbw.employee;

import java.time.LocalDateTime;

import de.dhbw.card.IDCard;

public abstract class Employee {

	private IDCard idCard;
	protected int id;
	protected String name;
	protected LocalDateTime birthDate;

	public Employee(String name, IDCard idCard) {
		this.name = name;
		this.idCard = idCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IDCard getIDCard() {
		return idCard;
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getBirthDate() {
		return birthDate;
	}
}
