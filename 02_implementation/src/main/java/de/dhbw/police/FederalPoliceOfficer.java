package de.dhbw.police;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.Passenger;
import de.dhbw.baggage.ProhibitedItem;
import de.dhbw.card.IDCard;
import de.dhbw.employee.Employee;
import de.dhbw.station.BaggageScanner;

public class FederalPoliceOfficer extends Employee {

	private FederalPoliceOffice federalPoliceOffice;
	private BaggageScanner baggageScanner;
	private List<ProhibitedItem> bigStashes;
	private int grade;

	public FederalPoliceOfficer(String name, FederalPoliceOffice federalPoliceOffice) {
		super(name, IDCard.createOfficerCard("1234"));
		this.federalPoliceOffice = federalPoliceOffice;
		this.bigStashes = new ArrayList<>();
	}

	public FederalPoliceOfficer(String name, IDCard idCard, FederalPoliceOffice federalPoliceOffice) {
		super(name, idCard);
		this.federalPoliceOffice = federalPoliceOffice;
		this.bigStashes = new ArrayList<>();
	}
	
	public void arrest(Passenger passenger) {
		passenger.arrest();
	}

	public FederalPoliceOffice getFederalPoliceOffice() {
		return federalPoliceOffice;
	}

	public List<FederalPoliceOfficer> callSupport() {
		return this.federalPoliceOffice.getOfficers().subList(1, 3);
	}

	public void takeProhibitedItem(ProhibitedItem prohibitedItem) {
		this.bigStashes.add(prohibitedItem);
	}

	public BaggageScanner getBaggageScanner() {
		return baggageScanner;
	}

	public int getGrade() {
		return grade;
	}
}
