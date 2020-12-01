package de.dhbw.card;

import java.time.LocalDateTime;

import de.dhbw.employee.Employee;

public class IDCard {

	private MagnetStripe magnetStripe;
	private Employee employee;
	private CardType type;
	private int id;
	private LocalDateTime validUntil;
	private boolean isLocked;

	public IDCard() {
		this.magnetStripe = new MagnetStripe();
	}

	public boolean isLocked() {
		return this.isLocked;
	}

	public MagnetStripe getMagnetStripe() {
		return this.magnetStripe;
	}

	public CardType getCardType() {
		return type;
	}

	public void lock() {
		this.isLocked = true;
	}
}
