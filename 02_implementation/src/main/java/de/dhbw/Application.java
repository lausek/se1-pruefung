package de.dhbw;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.baggage.HandBaggage;
import de.dhbw.station.BaggageScanner;
import de.dhbw.station.Tray;
import de.dhbw.station.UnauthorizedException;

public class Application {
	
	private BaggageScanner baggageScanner;
	private List<Passenger> passengers;
	
	public static void main(String[] args) {
		Application application = new Application();
		application.init();
		application.runSimulation();
	}

	public void init() {
		this.baggageScanner = new BaggageScanner();
		this.passengers = new ArrayList<>();
	}
	
	public void runSimulation() {
		for(Passenger passenger : passengers) {
			// take baggage from passenger and put it onto belt
			List<HandBaggage> passengerBaggage = passenger.getHandBaggage();
			while (!passengerBaggage.isEmpty()) {
				HandBaggage handBaggage = passengerBaggage.remove(0);

				Tray tray = this.baggageScanner.takeTray();
				tray.setHandBaggage(handBaggage);
				
				this.baggageScanner.getRollerConveyor().push(tray);
				
				try {
					this.baggageScanner.getOperatingStation().processNext();
				} catch(UnauthorizedException e) {
					 e.printStackTrace();
				}
			}
		}
	}
	
	public BaggageScanner getBaggageScanner() {
		return this.baggageScanner;
	}
	
	public List<Passenger> getPassengers() {
		return this.passengers;
	}
}
