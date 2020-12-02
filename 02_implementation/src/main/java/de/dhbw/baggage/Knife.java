package de.dhbw.baggage;

import de.dhbw.station.result.KnifeFound;
import de.dhbw.station.result.ScanResult;

public class Knife extends ProhibitedItem {

	public Knife() {
	}

	@Override
	public String getPattern() {
		return "knife";
	}

	@Override
	public ScanResult createResult(int position) {
		return new KnifeFound(position);
	}
}
