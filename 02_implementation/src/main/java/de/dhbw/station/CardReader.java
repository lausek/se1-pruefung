package de.dhbw.station;

import de.dhbw.card.IDCard;
import de.dhbw.card.ProfileType;

public class CardReader {

	private OperatingStation operatingStation;

	public CardReader(OperatingStation operatingStation) {
		this.operatingStation = operatingStation;
	}

	public boolean doAuthentication(IDCard card, String[] pins) {
		if (card.isLocked() || !this.swipeCard(card)) {
			return false;
		}

		int tries = 1;
		for (String pin : pins) {
			if (this.inputPin(card, pin)) {
				this.operatingStation.getBaggageScanner().activate();
				return true;
			}

			if (3 <= tries) {
				card.lock();
				break;
			}

			tries += 1;
		}

		return false;
	}

	public boolean swipeCard(IDCard card) {
		ProfileType profileType = card.getMagnetStripe().getProfileType();
		if (profileType == ProfileType.K || profileType == ProfileType.O) {
			return false;
		}
		return true;
	}

	public boolean inputPin(IDCard card, String pin) {
		return card.getMagnetStripe().getPin().equals(pin);
	}

	public OperatingStation getOperatingStation() {
		return operatingStation;
	}
}
