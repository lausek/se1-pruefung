import de.dhbw.RandomUtils;
import de.dhbw.baggage.HandBaggage;
import de.dhbw.baggage.ProhibitedItem;
import de.dhbw.employee.Inspector;
import de.dhbw.station.BaggageScanner;
import de.dhbw.station.UnauthorizedException;
import de.dhbw.station.result.Clean;
import de.dhbw.station.result.ScanResult;

public class TestUtility {
	public static boolean scan(ProhibitedItem prohibitedItem, BaggageScanner baggageScanner) {
		HandBaggage handBaggage = new HandBaggage(null);
		String content = prohibitedItem.getPattern();
		int layerIdx = RandomUtils.nextInt(5);

		handBaggage.getLayers()[layerIdx].setContent(content);

		Inspector inspector = baggageScanner.getOperatingStation().getInspector();
		ScanResult feedback;

		try {
			feedback = baggageScanner.scan(inspector, handBaggage);
		} catch (UnauthorizedException e) {
			return false;
		}

		return !(feedback instanceof Clean);
	}
}
