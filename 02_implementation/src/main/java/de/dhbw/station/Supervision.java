package de.dhbw.station;

import de.dhbw.employee.Supervisor;

public class Supervision {

	private BaggageScanner baggageScanner;
	private Supervisor supervisor;

	public Supervision(BaggageScanner baggageScanner) {
		this.baggageScanner = baggageScanner;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public BaggageScanner getBaggageScanner() {
		return baggageScanner;
	}
}
