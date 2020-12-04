package de.dhbw.card;

import java.time.LocalDateTime;

import de.dhbw.employee.Employee;

public class IDCard {
	
	private static int idCounter = 1;
	
	private MagnetStripe magnetStripe;
	private Employee employee;
	private CardType cardType;
	private int id;
	private LocalDateTime validUntil;
	private boolean isLocked;

	public IDCard(CardType cardType, ProfileType profileType, String pin) {
		this.magnetStripe = new MagnetStripe(this, profileType, pin);
		this.cardType = cardType;
		this.validUntil = LocalDateTime.now().plusDays(30);
		this.id = idCounter++;
	}

	public boolean isLocked() {
		return this.isLocked || LocalDateTime.now().isAfter(validUntil);
	}

	public MagnetStripe getMagnetStripe() {
		return this.magnetStripe;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void lock() {
		this.isLocked = true;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getId() {
		return id;
	}
	
	public static IDCard createOfficerCard(String pin) {
		return new IDCard(CardType.EXTERNAL, ProfileType.O, pin);
	}

	public static IDCard createInspectorCard(String pin) {
		return new IDCard(CardType.STAFF, ProfileType.I, pin);
	}

	public static IDCard createSupervisorCard(String pin) {
		return new IDCard(CardType.STAFF, ProfileType.S, pin);
	}

	public static IDCard createTechnicianCard(String pin) {
		return new IDCard(CardType.STAFF, ProfileType.T, pin);
	}

	public static IDCard createHouseKeeperCard(String pin) {
		return new IDCard(CardType.STAFF, ProfileType.K, pin);
	}
}
