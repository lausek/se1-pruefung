package de.dhbw.baggage;

import de.dhbw.station.result.ScanResult;

public abstract class ProhibitedItem {

	public ProhibitedItem() { }
	
	public abstract String getPattern();

	public abstract ScanResult createResult(int position);
}
