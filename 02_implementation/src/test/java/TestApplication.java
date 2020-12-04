import org.junit.jupiter.api.*;
import de.dhbw.Application;
import de.dhbw.Passenger;
import de.dhbw.baggage.Explosive;
import de.dhbw.baggage.Gun;
import de.dhbw.baggage.HandBaggage;
import de.dhbw.baggage.Knife;
import de.dhbw.baggage.Layer;
import de.dhbw.card.IDCard;
import de.dhbw.employee.Inspector;
import de.dhbw.employee.Supervisor;
import de.dhbw.employee.Technician;
import de.dhbw.police.FederalPoliceOfficer;
import de.dhbw.police.Roboter;
import de.dhbw.station.BaggageScanner;
import de.dhbw.station.CardReader;
import de.dhbw.station.Status;
import de.dhbw.station.Tray;
import de.dhbw.station.UnauthorizedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApplication {
	Application application;
	BaggageScanner baggageScanner;
	HandBaggage handBaggage;

	@BeforeEach
	public void setup() {
		application = new Application();
		application.init();

		this.baggageScanner = application.getBaggageScanner();
		this.handBaggage = null;
	}

	@Order(1)
	@DisplayName("Die Simulation mit 568 Passagieren und 609 Gepäckstücken wird korrekt initialisiert.")
	//@ParameterizedTest(name = "{index} => passenger={0}, handBaggage={1}")
	/*
	 * @CsvSource({ "Mark Fillingham, 3", "Steven Darby, 1" })
	 */
	public void init(/* String passengerName, String handBaggageRaw */) {
		assertEquals(568, this.application.getPassengers().size());
		int baggages = this.application.getPassengers().stream().map(passenger -> passenger.getHandBaggage().size())
				.reduce(0, Integer::sum);
		assertEquals(609, baggages);
		/*
		 * // Parameterized mit Sollwerten List<Passenger> passengerList =
		 * application.getPassengers();
		 * 
		 * Optional<Passenger> passenger = passengerList.stream().filter(result ->
		 * result.getName().equals(passengerName)).findFirst();
		 * assertTrue(passenger.get().getHandBaggage().contains(handBaggage));
		 */
	}

	@Test
	@Order(2)
	@DisplayName("Die Positionen an dem Gepäckscanner werden korrekt mit Mitarbeitern besetzt.")
	public void scannerEmployees() {
		// AssertEquals für alle Mitarbeiter (nicht kompliziertes)
		BaggageScanner baggageScanner = application.getBaggageScanner();
		assertEquals(baggageScanner.getRollerConveyor().getInspector().getName(), "Clint Eastwood");
		assertEquals(baggageScanner.getOperatingStation().getInspector().getName(), "Natalie Portman");
		assertEquals(baggageScanner.getManualPostControl().getInspector().getName(), "Bruce Willis");
		assertEquals(baggageScanner.getSupervision().getSupervisor().getName(), "Jodie Foster");
		assertEquals(baggageScanner.getFederalPoliceOfficer().getName(), "Wesley Snipes");
	}

	@Test
	@Order(3)
	@DisplayName("Nach dreimaliger Falschangabe der PIN wird der Ausweis für die weitere Nutzung gesperrt.")
	public void cardDisable() {
		// AssertEquals auf Status von Ausweis
		BaggageScanner baggageScanner = application.getBaggageScanner();
		CardReader cardReader = baggageScanner.getOperatingStation().getCardReader();
		IDCard idCard = baggageScanner.getSupervision().getSupervisor().getIDCard();
		assertFalse(idCard.isLocked());
		String[] tries = new String[] { "0000", "0000", "0000" };
		assertFalse(cardReader.doAuthentication(idCard, tries));
		assertTrue(idCard.isLocked());
	}

	@Test
	@Order(4)
	@DisplayName("Ein Mitarbeiter mit dem Profil K oder O kann sich an einem Gepäckscanner nicht anmelden.")
	public void scannerAccess() {
		// AssertTrue oder False, keine TestFactory
		IDCard idCard = IDCard.createOfficerCard("1234");
		BaggageScanner baggageScanner = application.getBaggageScanner();
		CardReader cardReader = baggageScanner.getOperatingStation().getCardReader();
		boolean feedback = cardReader.swipeCard(idCard);
		assertFalse(feedback);
	}

	@Test
	@Order(5)
	@DisplayName("Ein Mitarbeiter kann nur die - gemäß Profil - zugeordneten Funktionalitäten ausführen.")
	public void employeeFunctionalities() {
		// Kombinierter Test mit AssertEquals
		BaggageScanner baggageScanner = application.getBaggageScanner();

		Supervisor supervisor = new Supervisor(null);
		// this starts the baggageScanner
		assertDoesNotThrow(() -> baggageScanner.start(supervisor));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.alarm(supervisor));
		assertDoesNotThrow(() -> baggageScanner.report(supervisor));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.maintenance(supervisor));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.scan(supervisor, null));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.moveBeltForward(supervisor));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.moveBeltBackwards(supervisor));

		FederalPoliceOfficer officer = new FederalPoliceOfficer(null, null);
		assertThrows(UnauthorizedException.class, () -> baggageScanner.start(officer));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.alarm(officer));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.report(officer));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.maintenance(officer));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.scan(officer, null));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.moveBeltForward(officer));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.moveBeltBackwards(officer));

		Inspector inspector = new Inspector(null, false);
		assertThrows(UnauthorizedException.class, () -> baggageScanner.start(inspector));
		assertDoesNotThrow(() -> baggageScanner.alarm(inspector));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.report(inspector));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.maintenance(inspector));

		assertDoesNotThrow(() -> baggageScanner.unlock(new Supervisor(null)));
		assertDoesNotThrow(() -> baggageScanner.scan(inspector, new HandBaggage(null)));
		assertDoesNotThrow(() -> baggageScanner.moveBeltForward(inspector));
		assertDoesNotThrow(() -> baggageScanner.moveBeltBackwards(inspector));

		Technician technician = new Technician(null);
		assertThrows(UnauthorizedException.class, () -> baggageScanner.alarm(technician));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.report(technician));
		assertDoesNotThrow(() -> baggageScanner.maintenance(technician));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.scan(technician, null));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.moveBeltForward(technician));
		assertThrows(UnauthorizedException.class, () -> baggageScanner.moveBeltBackwards(technician));
	}

	@Test
	@Order(6)
	@DisplayName("Nur ein Supervisor kann einen Gepäckscanner im Status locked entsperren.")
	public void scannerEnable() {
		// AssertEquals auf Rückgabewert
		BaggageScanner baggageScanner = application.getBaggageScanner();

		assertDoesNotThrow(() -> baggageScanner.unlock(baggageScanner.getSupervision().getSupervisor()));

		assertThrows(UnauthorizedException.class,
				() -> baggageScanner.unlock(baggageScanner.getRollerConveyor().getInspector()));

		assertThrows(UnauthorizedException.class,
				() -> baggageScanner.unlock(baggageScanner.getManualPostControl().getInspector()));

		assertThrows(UnauthorizedException.class,
				() -> baggageScanner.unlock(baggageScanner.getOperatingStation().getInspector()));

		assertThrows(UnauthorizedException.class,
				() -> baggageScanner.unlock(baggageScanner.getFederalPoliceOfficer()));
	}

	@Test
	@Order(7)
	@DisplayName("Es wird der verbotene Gegenstand Messer in einem Handgepäckstück erkannt.")
	public void knife() {
		// TestUtility-Klasse (einmal erkennen langt, nicht für alle)
		TestUtility testUtility = new TestUtility();
		boolean found = testUtility.scan(new Knife(), application.getBaggageScanner());
		assertTrue(found);
	}

	@Test
	@Order(8)
	@DisplayName("Es wird der verbotene Gegenstand Waffe in einem Handgepäckstück erkannt.")
	public void gun() {
		// TestUtility-Klasse (einmal erkennen langt, nicht für alle)
		TestUtility testUtility = new TestUtility();
		boolean found = testUtility.scan(new Gun(), application.getBaggageScanner());
		assertTrue(found);
	}

	@Test
	@Order(9)
	@DisplayName("Es wird der verbotene Gegenstand Explosivstoff in einem Handgepäckstück erkannt.")
	public void explosive() {
		// TestUtility-Klasse (einmal erkennen langt, nicht für alle)
		TestUtility testUtility = new TestUtility();
		boolean found = testUtility.scan(new Explosive(), application.getBaggageScanner());
		assertTrue(found);
	}

	/* @TestFactory */
	@Order(10)
	@DisplayName("Jeder Scanvorgang wird ordnungsgemäß mit einem Datensatz protokolliert.")
	// Stream<DynamicTest> logging() {
	public void logging() {
		assertEquals(0, application.getBaggageScanner().getScanLog().size());
		application.runSimulation();
		// passengers who have prohibited items in their baggage will not have
		// the rest of their luggage scanned -> 
		// allBaggages - previousIllegalBaggages = 609 - 3
		assertEquals(606, application.getBaggageScanner().getScanLog().size());

		/*
		 * return null; // TestFactory List<HandBaggage> baggages = new ArrayList<>();
		 * baggages.add(new HandBaggage()); // without forbidden item baggages.add(new
		 * HandBaggage()); // with forbidden item List<Boolean> itemFound = new
		 * ArrayList<>(); itemFound.add(true); itemFound.add(false);
		 * 
		 * return baggages.stream().map(baggage ->
		 * DynamicTest.dynamicTest("Scanvorgang prüfen", () -> { int index =
		 * baggages.indexOf(baggage); assertEquals(itemFound.get(index),
		 * this.baggageScanner.scan(baggage).getResultMessage()); }));
		 */
	}

	@Test
	@Order(11)
	@DisplayName("Ordnungsgemäßer Ablauf, wenn keine verbotenen Gegenstände gefunden wurden.")
	public void standardNonIllegal() {
		// Code für Ablauf
		Passenger passenger = Passenger.fromString("Gutrun Gutrunsdottir;1;-");
		HandBaggage handBaggage = passenger.getHandBaggage().get(0);

		application.pushHandBaggageThrough(handBaggage);

		// Auf Track02 geleitet
		Tray tray = application.getBaggageScanner().getTrack2().takeNext();
		assertEquals(handBaggage, tray.getHandBaggage());
	}

	@Test
	@Order(12)
	@DisplayName("Ordnungsgemäßer Ablauf, wenn der verbotene Gegenstand Messer gefunden wurde.")
	public void standardKnife() {
		// Code für Ablauf
		Passenger passenger = Passenger.fromString("Effi Effidada;2;[K,1,2]");
		HandBaggage handBaggage = passenger.getHandBaggage().get(0);

		application.pushHandBaggageThrough(handBaggage);
		
		for(Layer layer : handBaggage.getLayers()) {
			assertFalse(layer.getContent().contains(new Knife().getPattern()));
		}
		
		// Durch Inspektor auf Bahn 01 leiten
		assertNotNull(application.getBaggageScanner().getTrack1().getItem(handBaggage));
		// Messer entnommen
		Layer[] layers = handBaggage.getLayers();
		assertFalse(Arrays.stream(layers).anyMatch(layer -> layer.getContent().contains("kn!fe")));
	}

	@Test
	@Order(13)
	@DisplayName("Ordnungsgemäßer Ablauf, wenn der verbotene Gegenstand Waffe gefunden wurde.")
	public void standardWeapon() {
		// Code für Ablauf
		Passenger passenger = Passenger.fromString("Gutrun Gutrunsdottir;1;[W,1,1]");
		HandBaggage handBaggage = passenger.getHandBaggage().get(0);
		
		application.pushHandBaggageThrough(handBaggage);
		
		Tray tray = application.getBaggageScanner().getTrack1().takeNext();

		// Durch Inspektor auf Bahn 01 leiten
		assertEquals(handBaggage, tray.getHandBaggage());
		// Alarm ausgelöst
		assertTrue(application.getBaggageScanner().isAlarmActive());
		// BaggageScanner Zustand locked
		assertTrue(application.getBaggageScanner().isLocked());
		// Festnahme
		assertTrue(handBaggage.getPassenger().isArrested());
		// Supervisor entnimmt Waffe und übergibt Bundespolizist
		Layer[] layers = handBaggage.getLayers();
		assertFalse(Arrays.stream(layers).anyMatch(layer -> layer.getContent().contains(new Gun().getPattern())));
		
		application.postprocessPassenger();
		
		// Betrieb fortsetzen (Alarm aus & Zustand unlocked)
		assertFalse(baggageScanner.isAlarmActive());
		assertEquals(Status.ACTIVE, baggageScanner.getStatus());
	}

	@Test
	@Order(14)
	@DisplayName("Ordnungsgemäßer Ablauf, wenn der verbotene Gegenstand Sprengstoff gefunden wurde.")
	public void standardExplosive() {
		// Code für Ablauf
		Passenger passenger = Passenger.fromString("Gutrun Gutrunsdottir;1;[E,1,1]");
		HandBaggage handBaggage = passenger.getHandBaggage().get(0);
		
		application.pushHandBaggageThrough(handBaggage);
		
		Tray tray = application.getBaggageScanner().getTrack1().takeNext();

		// Durch Inspektor auf Bahn 01 leiten
		assertEquals(handBaggage, tray.getHandBaggage());
		// Alarm ausgelöst
		assertTrue(baggageScanner.isAlarmActive());
		// BaggageScanner Zustand locked
		assertTrue(baggageScanner.isLocked());
		// Inspektor swiped mit Teststreifen über Gepäck und liest in
		// ExplosivesTraceDetector einlesen
		char[][] sample = application.getBaggageScanner().getManualPostControl().getInspector().swipe(handBaggage);
		assertTrue(application.getBaggageScanner().getExplosiveTraceDetector().test(sample));
		// Zerstörung in 1000 Teile mit Länge 50
		String[] destroyedParts = new Roboter().destroy(handBaggage);
		assertEquals(1000, destroyedParts.length);
		assertTrue(Arrays.stream(destroyedParts).allMatch(part -> part.length() == 50));
		
		application.postprocessPassenger();

		// Betrieb fortsetzen (Alarm aus & Zustand unlocked)
		assertFalse(baggageScanner.isAlarmActive());
		assertFalse(baggageScanner.isLocked());
	}
}
