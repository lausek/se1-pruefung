package de.dhbw.card;

import de.dhbw.aes.AesCryptor;

public class MagnetStripe {

	private final String secretKey = "dhbw$20^20_";

	private IDCard iDCard;
	private String pin;
	private ProfileType profileType;

	public MagnetStripe() {
	}

	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}
	
	public String getPin() {
		return new AesCryptor().decrypt(this.pin, this.secretKey);
	}

	public ProfileType getProfileType() {
		return this.profileType;
	}
}
