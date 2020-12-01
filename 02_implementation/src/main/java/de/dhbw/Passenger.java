package de.dhbw;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.baggage.HandBaggage;

public class Passenger {

	private List<HandBaggage> handBaggages;
	private String name;

	public Passenger() {
		this.handBaggages = new ArrayList<>(3);
	}
	
	public List<HandBaggage> getHandBaggage() {
		return this.handBaggages;
	}
	
	public String getName() {
		return this.name;
	}
}
