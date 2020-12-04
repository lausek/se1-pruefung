package de.dhbw.employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import de.dhbw.card.IDCard;

public abstract class Employee {

	private IDCard idCard;
	protected int id;
	protected String name;
	protected LocalDate birthDate;

	public Employee(String name, String birthDate, IDCard idCard) {
		this.name = name;
		this.idCard = idCard;

		if (birthDate == null) {
			birthDate = "2020-01-01";
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.birthDate = LocalDate.parse(birthDate, formatter);
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

	public LocalDate getBirthDate() {
		return birthDate;
	}
}
