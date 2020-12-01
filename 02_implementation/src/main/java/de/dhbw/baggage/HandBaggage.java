package de.dhbw.baggage;

import de.dhbw.Passenger;
import de.dhbw.station.Tray;

public class HandBaggage {

	private Layer[] layer;
	private Passenger passenger;
	private Tray tray;

	public HandBaggage() {
		this.layer = new Layer[5];
	}
}
