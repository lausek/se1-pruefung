package de.dhbw;

import de.dhbw.baggage.Explosive;
import de.dhbw.baggage.Gun;
import de.dhbw.baggage.Knife;
import de.dhbw.baggage.ProhibitedItem;

public class Configuration {
	public final static String CSV_FIELD_SEPARATOR = ";";
	public final static ProhibitedItem[] PROHIBITED_ITEMS = new ProhibitedItem[] { new Gun(), new Knife(), new Explosive() };
	public final static String SEARCH_ALGORITHM = "BoyerMoore";
}
