package de.dhbw.station;

import java.util.LinkedList;
import java.util.Queue;

public class Belt {

	private BaggageScanner baggageScanner;
	private LinkedList<Tray> queue;

	public Belt() {
		this.queue = new LinkedList<>();
	}

	public Tray getBack() {
		return this.queue.getLast();
	}

	public Tray getFront() {
		return this.queue.getFirst();
	}
}
