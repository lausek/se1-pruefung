package de.dhbw.station.result;

public abstract class ScanResult {
	protected int position;
	protected String itemFound;

	public ScanResult() {
	}

	public String getResultMessage() {
		return String.format("%s detected at position %d", this.itemFound, this.position);
	}
}
