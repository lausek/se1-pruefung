package de.dhbw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import de.dhbw.baggage.HandBaggage;
import de.dhbw.employee.HouseKeeper;
import de.dhbw.employee.Inspector;
import de.dhbw.employee.Supervisor;
import de.dhbw.employee.Technician;
import de.dhbw.police.FederalPoliceOffice;
import de.dhbw.station.BaggageScanner;
import de.dhbw.station.Tray;
import de.dhbw.station.UnauthorizedException;

public class Application {

	private BaggageScanner baggageScanner;
	private FederalPoliceOffice federalPoliceOffice;
	private List<Passenger> passengers;

	public static void main(String[] args) {
		Application application = new Application();
		application.init();
		application.runSimulation();
	}

	public void init() {
		this.baggageScanner = new BaggageScanner();
		this.federalPoliceOffice = new FederalPoliceOffice();
		this.passengers = new ArrayList<>();

		this.baggageScanner.getRollerConveyor().setInspector(new Inspector("Clint Eastwood", true));
		this.baggageScanner.getOperatingStation().setInspector(new Inspector("Natalie Portman", false));
		this.baggageScanner.getManualPostControl().setInspector(new Inspector("Bruce Willis", true));
		this.baggageScanner.getSupervision().setSupervisor(new Supervisor("Jodie Foster"));
		this.baggageScanner.setFederalPoliceOfficer(this.federalPoliceOffice.getOfficers().get(0));
		this.baggageScanner.setTechnician(new Technician("Jason Statham"));
		this.baggageScanner.setHouseKeeper(new HouseKeeper("Jason Clarke"));

		System.out.println("starting baggage scanner...");
		try {
			this.baggageScanner.start(this.baggageScanner.getSupervision().getSupervisor());
		} catch (UnauthorizedException e1) {
			e1.printStackTrace();
		}

		// load passengers from resource file
		try (InputStream passengerStream = this.getClass().getResourceAsStream("/passengers.csv")) {
			try (BufferedReader buffer = new BufferedReader(new InputStreamReader(passengerStream))) {
				String line = null;
				while (null != (line = buffer.readLine())) {
					Passenger passenger = Passenger.fromString(line.strip());
					this.passengers.add(passenger);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("loaded " + this.passengers.size() + " passengers...");
	}

	public void runSimulation() {
		System.out.println("starting simulation ...");

		for (Passenger passenger : passengers) {
			this.processPassenger(passenger);

			this.postprocessPassenger();
		}

		System.out.println("finalizing simulation ...");
	}

	public void pushHandBaggageThrough(HandBaggage handBaggage) {
		Tray tray = this.baggageScanner.takeTray();
		tray.setHandBaggage(handBaggage);

		this.baggageScanner.getRollerConveyor().push(tray);

		try {
			this.baggageScanner.getOperatingStation().processNext();
		} catch (UnauthorizedException e) {
			e.printStackTrace();
		}
	}

	public void postprocessPassenger() {
		if (this.baggageScanner.isAlarmActive()) {
			try {
				this.baggageScanner.unlock(this.baggageScanner.getSupervision().getSupervisor());
			} catch (UnauthorizedException e) {
				e.printStackTrace();
			}
		}
	}

	public void processPassenger(Passenger passenger) {
		// take baggage from passenger and put it onto belt
		List<HandBaggage> passengerBaggage = passenger.getHandBaggage();
		List<HandBaggage> scannedPassengerBaggage = new ArrayList<>(3);

		while (!passengerBaggage.isEmpty()) {
			HandBaggage handBaggage = passengerBaggage.remove(0);

			this.pushHandBaggageThrough(handBaggage);

			// items on this track are clean
			Tray tray = passenger.isArrested() ? this.baggageScanner.getTrack1().takeNext()
					: this.baggageScanner.getTrack2().takeNext();
			handBaggage = tray.removeHandBaggage();

			// return tray to baggage scanner
			this.baggageScanner.addTray(tray);

			// if the passenger was arrested, stop processing the baggages
			if (passenger.isArrested()) {
				return;
			}

			// return handbaggage to passenger
			scannedPassengerBaggage.add(handBaggage);
		}

		passenger.getHandBaggage().addAll(scannedPassengerBaggage);
	}

	public BaggageScanner getBaggageScanner() {
		return this.baggageScanner;
	}

	public List<Passenger> getPassengers() {
		return this.passengers;
	}
}
