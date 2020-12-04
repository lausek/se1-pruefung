package de.dhbw.baggage;

import de.dhbw.Passenger;

public class HandBaggage {

	private Layer[] layers;
	private Passenger passenger;

	public HandBaggage(Passenger passenger) {
		this.passenger = passenger;
		this.layers = new Layer[5];
		for (int i = 0; i < this.layers.length; i++) {
			this.layers[i] = new Layer(this);
		}
	}

	public Layer[] getLayers() {
		return this.layers;
	}

	public Passenger getPassenger() {
		return this.passenger;
	}

	public ProhibitedItem removeProhibitedItem() {
		ProhibitedItem prohibitedItem = null;
		for (Layer layer : this.getLayers()) {
			String pattern = layer.getContent().strip();

			if (pattern.contains(new Knife().getPattern())) {
				prohibitedItem = new Knife();
			} else if (pattern.contains(new Explosive().getPattern())) {
				prohibitedItem = new Explosive();
			} else if (pattern.contains(new Gun().getPattern())) {
				prohibitedItem = new Gun();
			}

			layer.setContent("                           ");
		}
		return prohibitedItem;
	}
}
