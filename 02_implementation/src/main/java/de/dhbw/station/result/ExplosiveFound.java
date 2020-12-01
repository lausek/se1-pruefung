package de.dhbw.station.result;

public class ExplosiveFound extends ScanResult {

	public ExplosiveFound(int position) {
		this.position = position;
		this.itemFound = "explosive";
	}
}
