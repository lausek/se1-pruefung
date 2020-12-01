package de.dhbw.card;

public class MagnetStripe {

	private IDCard iDCard;
	private String PIN;
	private ProfileType profileType;

	public MagnetStripe() {
	}

	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}
}
