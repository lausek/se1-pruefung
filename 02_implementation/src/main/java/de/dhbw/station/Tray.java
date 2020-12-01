package de.dhbw.station;

import de.dhbw.baggage.HandBaggage;

public class Tray {

	private HandBaggage handBaggage;
	private BaggageScanner baggageScanner;

	public Tray(BaggageScanner baggageScanner) {
		this.baggageScanner = baggageScanner;
	}

	public HandBaggage getHandBaggage() {
		return handBaggage;
	}

	public void setHandBaggage(HandBaggage handBaggage) {
		this.handBaggage = handBaggage;
	}
	
	public HandBaggage removeHandBaggage() {
		HandBaggage handBaggage = this.handBaggage;
		this.handBaggage = null;
		return handBaggage;
	}
}
