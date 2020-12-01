package de.dhbw.station;

import java.util.LinkedList;

import de.dhbw.baggage.HandBaggage;

public class Belt {

	private BaggageScanner baggageScanner;
	private LinkedList<Tray> queue;

	public Belt(BaggageScanner baggageScanner) {
		this.baggageScanner = baggageScanner;
		this.queue = new LinkedList<>();
	}
	
	public void push(Tray tray) {
		this.queue.add(tray);
	}

	public Tray getBack() {
		return this.queue.getLast();
	}

	public Tray getFront() {
		return this.queue.getFirst();
	}
	
	public Tray getItem(HandBaggage handBaggage) {
		for(Tray tray : this.queue) {
			if(tray.getHandBaggage() == handBaggage) {
				return tray;
			}
		}
		return null;
	}
}
