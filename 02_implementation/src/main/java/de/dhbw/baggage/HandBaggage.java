package de.dhbw.baggage;

import de.dhbw.Passenger;
import de.dhbw.station.Tray;

public class HandBaggage {

	private Layer[] layers;
	private Passenger passenger;
	private Tray tray;

	public HandBaggage() {
		this.layers = new Layer[5];
	}

	public Layer[] getLayers() {
		return this.layers;
	}
}
