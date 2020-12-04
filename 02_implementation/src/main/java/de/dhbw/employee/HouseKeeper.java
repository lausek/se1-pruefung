package de.dhbw.employee;

import java.time.LocalDateTime;

import de.dhbw.card.IDCard;

public class HouseKeeper extends Employee {
	public HouseKeeper(String name, String birthDate) {
		super(name, birthDate, IDCard.createHouseKeeperCard("1234"));
	}

	public HouseKeeper(String name, String birthDate, IDCard idCard) {
		super(name, birthDate, idCard);
	}
}
