import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApplication {
    @BeforeEach
    public void setup() {
        //Application application = new Application();
        //application.init();
    }

    @Order(1)
    @DisplayName("Die Simulation mit 568 Passagieren und 609 Gepäckstücken wird korrekt initialisiert.")
    @ParameterizedTest(name = "{index} => passenger={0}, handBaggage={1}")
    @CsvSource({
            "Mark Fillingham, 3",
            "Steven Darby, 1"
    })
    public void init(String passenger, String handBaggage) {
        // Parametrized mit Sollwerten
        List<Passenger> passengerList = application.getPassengers();
        List<Passenger> passengerListFiltered = application.getPassengers();

        if (passenger != null && !passenger.isEmpty()) {
            passengerListFiltered = passengerList.stream().filter(result -> result.getName().equals(passenger)).collect(Collectors.toList());
        }

        for (Passenger passenger : passengerListFiltered) {
            assertEquals(passenger.getHandBaggage(), handBaggage);
        }
    }

    @Test
    @Order(2)
    @DisplayName("Die Positionen an dem Gepäckscanner werden korrekt mit Mitarbeitern besetzt.")
    public void scannerEmployees() {
        // AssertEquals für alle Mitarbeiter (nicht kompliziertes)
        BaggageScanner baggageScanner = application.getBaggageScanner();
        assertEquals(baggageScanner.getRollerConveyer().getInspector().getName(), "Clint Eastwood");
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
        IDCard idCard = baggageScanner.getFederalPoliceOfficer().getIDCard();
        String feedback = cardReader.swipe(idCard, 0000, 0000, 0000);
        assertTrue(idCard.isLocked());
    }

    @Test
    @Order(4)
    @DisplayName("Ein Mitarbeiter mit dem Profil K oder O kann sich an einem Gepäckscanner nicht anmelden.")
    public void scannerAccess() {
        // AssertTrue oder False, keine TestFactory
        IDCard idCard = new IDCard();
        idCard.getMagnetStripe().setProfileType("K");
        BaggageScanner baggageScanner = application.getBaggageScanner();
        CardReader cardReader = baggageScanner.getOperatingStation().getCardReader();
        String feedback = cardReader.swipe(idCard, 0000, 0000, 0000);
        assertEquals("Error - Not authorized", feedback);
    }

    @Test
    @Order(5)
    @DisplayName("Ein Mitarbeiter kann nur die - gemäß Profil - zugeordneten Funktionalitäten ausführen.")
    public void employeeFunctionalities() {
        // Kombinierter Test mit AssertEquals
        IDCard idCard = new IDCard();
        BaggageScanner baggageScanner = application.getBaggageScanner();

        boolean random = new Random().nextBoolean();
        if (random) {
            idCard.getMagnetStripe().setProfileType("O");
        } else {
            idCard.getMagnetStripe().setProfileType("K");
        }
        String feedback1 = baggageScanner.alarm(idCard);
        assertEquals("Error - Not authorized", feedback1);
        String feedback2 = baggageScanner.report(idCard);
        assertEquals("Error - Not authorized", feedback2);
        String feedback3 = baggageScanner.maintenance(idCard);
        assertEquals("Error - Not authorized", feedback3);
        String feedback4 = baggageScanner.scan(idCard);
        assertEquals("Error - Not authorized", feedback4);
        String feedback5 = baggageScanner.moveBeltForwards(idCard);
        assertEquals("Error - Not authorized", feedback5);
        String feedback6 = baggageScanner.moveBeltBackwards(idCard);
        assertEquals("Error - Not authorized", feedback6);

        idCard.getMagnetStripe().setProfileType("I");
        String feedback1 = baggageScanner.alarm(idCard);
        assertEquals("Success", feedback1);
        String feedback2 = baggageScanner.report(idCard);
        assertEquals("Error - Not authorized", feedback2);
        String feedback3 = baggageScanner.maintenance(idCard);
        assertEquals("Error - Not authorized", feedback3);
        String feedback4 = baggageScanner.scan(idCard);
        assertEquals("Success", feedback4);
        String feedback5 = baggageScanner.moveBeltForwards(idCard);
        assertEquals("Success", feedback5);
        String feedback6 = baggageScanner.moveBeltBackwards(idCard);
        assertEquals("Success", feedback6);

        idCard.getMagnetStripe().setProfileType("S");
        String feedback1 = baggageScanner.alarm(idCard);
        assertEquals("Error - Not authorized", feedback1);
        String feedback2 = baggageScanner.report(idCard);
        assertEquals("Success", feedback2);
        String feedback3 = baggageScanner.maintenance(idCard);
        assertEquals("Error - Not authorized", feedback3);
        String feedback4 = baggageScanner.scan(idCard);
        assertEquals("Error - Not authorized", feedback4);
        String feedback5 = baggageScanner.moveBeltForwards(idCard);
        assertEquals("Error - Not authorized", feedback5);
        String feedback6 = baggageScanner.moveBeltBackwards(idCard);
        assertEquals("Error - Not authorized", feedback6);

        idCard.getMagnetStripe().setProfileType("T");
        String feedback1 = baggageScanner.alarm(idCard);
        assertEquals("Error - Not authorized", feedback1);
        String feedback2 = baggageScanner.report(idCard);
        assertEquals("Error - Not authorized", feedback2);
        String feedback3 = baggageScanner.maintenance(idCard);
        assertEquals("Success", feedback3);
        String feedback4 = baggageScanner.scan(idCard);
        assertEquals("Error - Not authorized", feedback4);
        String feedback5 = baggageScanner.moveBeltForwards(idCard);
        assertEquals("Error - Not authorized", feedback5);
        String feedback6 = baggageScanner.moveBeltBackwards(idCard);
        assertEquals("Error - Not authorized", feedback6);
    }

    @Test
    @Order(6)
    @DisplayName("Nur ein Supervisor kann einen Gepäckscanner im Status locked entsperren.")
    public void scannerEnable() {
        // AssertEquals auf Rückgabewert
        BaggageScanner baggageScanner = application.getBaggageScanner();

        String feedback = baggageScanner.unlock(baggageScanner.getSupervision().getSupervisor());
        assertEquals("Success", feedback);

        String feedback2 = baggageScanner.unlock(baggageScanner.getRollerConveyer().getInspector());
        assertEquals("Error - Not authorized", feedback2);

        String feedback3 = baggageScanner.unlock(baggageScanner.getManualPostControl().getInspector());
        assertEquals("Error - Not authorized", feedback3);

        String feedback4 = baggageScanner.unlock(baggageScanner.getOperatingStation().getInspector());
        assertEquals("Error - Not authorized", feedback4);

        String feedback5 = baggageScanner.unlock(baggageScanner.getFederalPoliceOfficer());
        assertEquals("Error - Not authorized", feedback5);
    }

    @Test
    @Order(7)
    @DisplayName("Es wird der verbotene Gegenstand Messer in einem Handgepäckstück erkannt.")
    public void knife() {
        // TestUtility-Klasse (einmal erkennen langt, nicht für alle)
        TestUtility testUtility = new TestUtility();
        boolean found = testUtility.scan("Knife", application.getBaggageScanner());
        assertTrue(found);
    }

    @Test
    @Order(8)
    @DisplayName("Es wird der verbotene Gegenstand Waffe in einem Handgepäckstück erkannt.")
    public void gun() {
        // TestUtility-Klasse (einmal erkennen langt, nicht für alle)
        TestUtility testUtility = new TestUtility();
        boolean found = testUtility.scan("Gun", application.getBaggageScanner());
        assertTrue(found);
    }

    @Test
    @Order(9)
    @DisplayName("Es wird der verbotene Gegenstand Explosivstoff in einem Handgepäckstück erkannt.")
    public void explosive() {
        // TestUtility-Klasse (einmal erkennen langt, nicht für alle)
        TestUtility testUtility = new TestUtility();
        boolean found = testUtility.scan("Explosive", application.getBaggageScanner());
        assertTrue(found);
    }

    @Test
    @Order(10)
    @DisplayName("Jeder Scanvorgang wird ordnungsgemäß mit einem Datensatz protokolliert.")
    public void logging() {
        // TestFactory
    }

    @Test
    @Order(11)
    @DisplayName("Ordnungsgemäßer Ablauf, wenn keine verbotenen Gegenstände gefunden wurden.")
    public void standardNonIllegal() {
        // Code für Ablauf
        // Auf Track02 geleitet
        assertEquals(handBaggage, application.getBaggageScanner().getTray02().getItem(handBaggage));
    }

    @Test
    @Order(12)
    @DisplayName("Ordnungsgemäßer Ablauf, wenn der verbotene Gegenstand Messer gefunden wurde.")
    public void standardKnife() {
        // Code für Ablauf
        // Durch Inspektor auf Bahn 01 leiten
        assertEquals(handBaggage, application.getBaggageScanner().getTray01().getItem(handBaggage));
        // Messer entnommen
        assertFalse(handBaggage.getLayer().getContent().contains("kn!fe"));
        // Schale mit Handgepäckstück am Ende des Förderbands abgelegt
        assertEquals(handBaggage, belt.getBack());
        // Button Pfeil nach links und Gepäck fährt wieder an Anfang
        assertEquals(handBaggage, belt.getFront());
    }

    @Test
    @Order(13)
    @DisplayName("Ordnungsgemäßer Ablauf, wenn der verbotene Gegenstand Waffe gefunden wurde.")
    public void standardWeapon() {
        // Code für Ablauf
        // Durch Inspektor auf Bahn 01 leiten
        assertEquals(handBaggage, application.getBaggageScanner().getTray01().getItem(handBaggage));
        // Alarm ausgelöst
        assertTrue(baggageScanner.isAlarm());
        // BaggageScanner Zustand locked
        assertEquals("LOCKED", baggageScanner.getStatus);
        // Festnahme
        assertTrue(passenger.isArrested());
        // Supervisor entnimmt Waffe und übergibt Bundespolizist
        assertFalse(handBaggage.getLayer().getContent().contains("glock|7"));
        // Betrieb fortsetzen (Alarm aus & Zustand unlocked)
        assertFalse(baggageScanner.isAlarm());
        assertEquals("ACTIVE", baggageScanner.getStatus);
    }

    @Test
    @Order(14)
    @DisplayName("Ordnungsgemäßer Ablauf, wenn der verbotene Gegenstand Sprengstoff gefunden wurde.")
    public void standardExplosive() {
        // Code für Ablauf
        // Durch Inspektor auf Bahn 01 leiten
        assertEquals(handBaggage, application.getBaggageScanner().getTray01().getItem(handBaggage));
        // Alarm ausgelöst
        assertTrue(baggageScanner.isAlarm());
        // BaggageScanner Zustand locked
        assertEquals("LOCKED", baggageScanner.getStatus);
        // Inspektor swiped mit Teststreifen über Gepäck und liest in ExplosivesTraceDetector einlesen
        // Code boolean found = explosiveTraceDetector.test();
        assertTrue(found);
        // Zerstörung in 1000 Teile mit Länge 50
        char[][] handBaggageContent = handBaggage.getLayer().getContent();
        assertEquals(handBaggageContent.length, 1000);
        if (handBaggageContent.length == 1000) {
            for (int i = 0; i <= 1000; i++) {
                assertEquals(handBaggageContent[i].length, 50);
            }
        }
        // Betrieb fortsetzen (Alarm aus & Zustand unlocked)
        assertFalse(baggageScanner.isAlarm());
        assertEquals("ACTIVE", baggageScanner.getStatus);
    }
}