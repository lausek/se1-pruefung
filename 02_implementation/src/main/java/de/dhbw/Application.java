package de.dhbw;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.station.BaggageScanner;

public class Application {
	
	private BaggageScanner baggageScanner;
	private List<Passenger> passengers;
	
	public static void main(String[] args) {
		Application application = new Application();
		application.init();
	}

	public void init() {
		this.baggageScanner = new BaggageScanner();
		this.passengers = new ArrayList<>();
	}
	
	public BaggageScanner getBaggageScanner() {
		return this.baggageScanner;
	}
	
	public List<Passenger> getPassengers() {
		return this.passengers;
	}
}
