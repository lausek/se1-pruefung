import de.dhbw.baggage.HandBaggage;
import de.dhbw.station.BaggageScanner;
import de.dhbw.station.result.Clean;
import de.dhbw.station.result.ScanResult;

public class TestUtility {
    public boolean scan(String item, BaggageScanner baggageScanner) {
        HandBaggage handBaggage = new HandBaggage();
        switch (item) {
            case "Knife":
                handBaggage.setContent("kn!fe");
                break;
            case "Explosive":
                handBaggage.setContent("exp|os!ve");
                break;
            default:
                handBaggage.setContent("glock|7");
                break;
        }

        ScanResult feedback = baggageScanner.scan(handBaggage);
        return !(feedback instanceof Clean);
    }
}
