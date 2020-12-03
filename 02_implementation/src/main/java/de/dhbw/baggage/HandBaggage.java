package de.dhbw.baggage;

import de.dhbw.Passenger;
import de.dhbw.station.Tray;

public class HandBaggage {

	private Layer[] layers;
	private Passenger passenger;
	private Tray tray;

	public HandBaggage(Passenger passenger) {
		this.passenger = passenger;
		this.layers = new Layer[5];
		for(int i = 0; i < this.layers.length; i++) {
			this.layers[i] = new Layer();
		}
	}

	public Layer[] getLayers() {
		return this.layers;
	}

	public Passenger getPassenger() {
		return this.passenger;
	}

	public ProhibitedItem removeProhibitedItem() {
		return null;
	}
}
