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
}
