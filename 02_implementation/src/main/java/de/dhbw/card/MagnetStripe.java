package de.dhbw.card;

import de.dhbw.aes.AesCryptor;

public class MagnetStripe {

	private final String SECRET_KEY = "dhbw$20^20_";

	private IDCard idCard;
	private String pin;
	private ProfileType profileType;

	public MagnetStripe(IDCard idCard, ProfileType profileType, String pin) {
		this.idCard = idCard;
		this.profileType = profileType;
		this.pin = new AesCryptor().encrypt(pin, this.SECRET_KEY);
	}

	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}
	
	public String getPin() {
		return new AesCryptor().decrypt(this.pin, this.SECRET_KEY);
	}

	public ProfileType getProfileType() {
		return this.profileType;
	}
}
