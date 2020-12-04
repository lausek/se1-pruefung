package de.dhbw.employee;

import de.dhbw.card.IDCard;

public class HouseKeeper extends Employee {
	public HouseKeeper(String name) {
		super(name, IDCard.createHouseKeeperCard("1234"));
	}

	public HouseKeeper(String name, IDCard idCard) {
		super(name, idCard);
	}
}
