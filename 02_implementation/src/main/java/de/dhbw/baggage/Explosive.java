package de.dhbw.baggage;

import de.dhbw.station.result.ExplosiveFound;
import de.dhbw.station.result.ScanResult;

public class Explosive extends ProhibitedItem {

	public Explosive() {
	}

	@Override
	public String getPattern() {
		return "exp|os!ve";
	}

	@Override
	public ScanResult createResult(int position) {
		return new ExplosiveFound(position);
	}
}
