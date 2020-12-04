package de.dhbw.station;

public class Scanner {

	private BaggageScanner baggageScanner;

	public Scanner(BaggageScanner baggageScanner) {
		this.baggageScanner = baggageScanner;
	}

	public BaggageScanner getBaggageScanner() {
		return baggageScanner;
	}
}
