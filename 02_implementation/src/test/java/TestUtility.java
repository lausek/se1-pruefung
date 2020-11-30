public class TestUtility {
    public boolean scan(String item, BaggageScanner baggageScanner) {
        Handbaggage handBaggage = new Handbaggage();
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

        String feedback = baggageScanner.scan(handbaggage);
        boolean found = false;
        if (feedback.equals("Item found")) {
            found = true;
        }

        return found;
    }
}
