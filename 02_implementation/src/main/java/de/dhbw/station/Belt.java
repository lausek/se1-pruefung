package de.dhbw.station;

import java.util.LinkedList;

import de.dhbw.baggage.HandBaggage;

public class Belt {

	private LinkedList<Tray> queue;

	public Belt() {
		this.queue = new LinkedList<>();
	}
	
	public void push(Tray tray) {
		this.queue.add(tray);
	}

	public Tray takeNext() {
		return this.queue.poll();
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
	
	public int size() {
		return this.queue.size();
	}
}
