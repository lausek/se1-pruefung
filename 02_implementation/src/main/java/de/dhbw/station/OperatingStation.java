package de.dhbw.station;

import java.util.List;

import de.dhbw.Passenger;
import de.dhbw.baggage.HandBaggage;
import de.dhbw.baggage.ProhibitedItem;
import de.dhbw.employee.Inspector;
import de.dhbw.police.FederalPoliceOfficer;
import de.dhbw.police.Roboter;
import de.dhbw.station.result.Clean;
import de.dhbw.station.result.ExplosiveFound;
import de.dhbw.station.result.ScanResult;
import de.dhbw.station.result.WeaponFound;

public class OperatingStation {

	private CardReader cardReader;
	private Inspector inspector;
	private BaggageScanner baggageScanner;

	public OperatingStation(BaggageScanner baggageScanner) {
		this.baggageScanner = baggageScanner;
		this.cardReader = new CardReader(this);
	}

	public void processNext() throws UnauthorizedException {
		this.baggageScanner.moveBeltForward(this.inspector);
		Tray currentTray = this.baggageScanner.getBelt().takeNext();
		HandBaggage handBaggage = currentTray.getHandBaggage();

		ScanResult scanResult = this.baggageScanner.scan(this.inspector, handBaggage);

		System.out.println("baggage of passenger " + handBaggage.getPassenger().getName() + " is "
				+ scanResult.getClass().getSimpleName());

		if (scanResult instanceof Clean) {
			this.baggageScanner.getTrack2().push(currentTray);
		} else {
			// passenger joins in
			Passenger passenger = handBaggage.getPassenger();

			FederalPoliceOfficer officer = this.baggageScanner.getFederalPoliceOfficer();
			officer.arrest(passenger);

			if (scanResult instanceof WeaponFound || scanResult instanceof ExplosiveFound) {
				this.baggageScanner.alarm(this.inspector);

				List<FederalPoliceOfficer> support = officer.callSupport();
				FederalPoliceOfficer officerO2 = support.get(0);
				FederalPoliceOfficer officerO3 = support.get(1);

				if (scanResult instanceof ExplosiveFound) {
					Roboter roboter = this.baggageScanner.getFederalPoliceOfficer().getFederalPoliceOffice()
							.getRoboter();
					roboter.setFederalPoliceOfficer(officerO2);

					char[][] sample = this.inspector.swipe(handBaggage);
					assert (this.baggageScanner.getExplosiveTraceDetector().test(sample));
					
					roboter.destroy(handBaggage);

					roboter.setFederalPoliceOfficer(null);
				} else {
					ProhibitedItem prohibitedItem = handBaggage.removeProhibitedItem();
					officerO3.takeProhibitedItem(prohibitedItem);
				}

				this.baggageScanner.unlock(this.baggageScanner.getSupervision().getSupervisor());
			} else {
				// knife was found
				this.baggageScanner.getManualPostControl().getInspector()
						.duPeddaPassUffDieTypeIsNichSauberIkHabDaNMesserJefunden();
			}

			this.baggageScanner.getTrack1().push(currentTray);
		}
	}

	public Inspector getInspector() {
		return inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}

	public CardReader getCardReader() {
		return cardReader;
	}
}
