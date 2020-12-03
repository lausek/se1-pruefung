package de.dhbw;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.baggage.Explosive;
import de.dhbw.baggage.Gun;
import de.dhbw.baggage.HandBaggage;
import de.dhbw.baggage.Knife;
import de.dhbw.baggage.ProhibitedItem;

public class Passenger {

	private List<HandBaggage> handBaggages;
	private String name;
	private boolean isArrested = false;

	public Passenger() {
		this.handBaggages = new ArrayList<>(3);
	}

	public List<HandBaggage> getHandBaggage() {
		return this.handBaggages;
	}

	public String getName() {
		return this.name;
	}

	public void arrest() {
		this.isArrested = true;
	}

	public boolean isArrested() {
		return this.isArrested;
	}

	public static Passenger fromString(String raw) {
		Passenger self = new Passenger();
		String[] parts = raw.split(Configuration.CSV_FIELD_SEPARATOR);

		self.name = parts[0].strip();
		int handBaggages = Integer.parseInt(parts[1]);

		for (int i = 0; i < handBaggages; i++) {
			HandBaggage handBaggage = new HandBaggage(self);
			self.handBaggages.add(handBaggage);
		}

		String prohibitedPattern = parts[2].strip();
		if (!prohibitedPattern.equals("-")) {
			String unbracket = prohibitedPattern.replace("]", "").replace("[", "");
			String[] prohibitedItems = unbracket.split(Configuration.CSV_FIELD_SEPARATOR);
			for (String patternItemsPattern : prohibitedItems) {
				addProhibitedItem(self.handBaggages, patternItemsPattern);
			}
		}

		return self;
	}

	private static void addProhibitedItem(List<HandBaggage> handBaggages, String pattern) {
		String[] prohibitedItemLocation = pattern.split(",");
		int baggage = Integer.parseInt(prohibitedItemLocation[1]) - 1;
		int layer = Integer.parseInt(prohibitedItemLocation[2]) - 1;

		ProhibitedItem prohibitedItem = null;
		switch (prohibitedItemLocation[0]) {
		case "W":
			prohibitedItem = new Gun();
			break;
		case "K":
			prohibitedItem = new Knife();
			break;
		case "E":
			prohibitedItem = new Explosive();
			break;
		}

		handBaggages.get(baggage).getLayers()[layer].setContent(prohibitedItem.getPattern());
	}
}
