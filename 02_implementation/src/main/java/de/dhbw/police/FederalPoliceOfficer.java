package de.dhbw.police;

import de.dhbw.Passenger;
import de.dhbw.employee.Employee;
import de.dhbw.station.BaggageScanner;

public class FederalPoliceOfficer extends Employee {

	private FederalPoliceOffice federalPoliceOffice;
	private BaggageScanner baggageScanner;
	private int grade;

	public FederalPoliceOfficer() { }
	
	public void arrest(Passenger passenger) {
		passenger.arrest();
	}
}
