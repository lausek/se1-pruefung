package de.dhbw;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.baggage.HandBaggage;

public class Passenger {

	private List<HandBaggage> handBaggages;
	private String name;
	private boolean isArrested = false;

	public Passenger() {
		this.handBaggages = new ArrayList<>(3);
	}
	
	public List<HandBaggage> getHandBaggage() {
		return this.handBaggages;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void arrest() {
		this.isArrested = true;
	}

	public boolean isArrested() {
		return this.isArrested;
	}
}
