package de.dhbw.station;

import de.dhbw.baggage.HandBaggage;

public class Tray {

	private HandBaggage handBaggage;

	public Tray() { }

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
