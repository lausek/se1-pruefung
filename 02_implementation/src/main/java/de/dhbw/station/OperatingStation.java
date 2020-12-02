package de.dhbw.station;

import de.dhbw.baggage.HandBaggage;
import de.dhbw.employee.Inspector;
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
		Tray currentTray = this.baggageScanner.getBelt().getBack();
		HandBaggage handBaggage = currentTray.getHandBaggage();
		ScanResult scanResult = this.baggageScanner.scan(this.inspector, handBaggage);
		
		if (scanResult instanceof Clean) {
			this.baggageScanner.getTrack1().push(currentTray);
		} else {
			if (scanResult instanceof WeaponFound || scanResult instanceof ExplosiveFound) {
				this.baggageScanner.alarm(this.inspector);
			}
			this.baggageScanner.getTrack2().push(currentTray);
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
