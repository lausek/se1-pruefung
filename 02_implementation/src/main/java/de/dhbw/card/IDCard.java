package de.dhbw.card;

import java.time.LocalDateTime;

public class IDCard {

	private MagnetStripe magnetStripe;
	private CardType type;
	private int id;
	private LocalDateTime validUntil;
	private boolean isLocked;

	public IDCard() {
		this.magnetStripe = new MagnetStripe();
	}
}
