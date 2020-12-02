package de.dhbw.baggage;

import de.dhbw.station.result.ScanResult;
import de.dhbw.station.result.WeaponFound;

public class Gun extends ProhibitedItem {

	public Gun() { }

	@Override
	public String getPattern() {
		return "glock7";
	}

	@Override
	public ScanResult createResult(int position) {
		return new WeaponFound(position);
	}
}
