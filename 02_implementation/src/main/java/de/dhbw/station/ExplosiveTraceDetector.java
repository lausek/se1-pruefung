package de.dhbw.station;

public class ExplosiveTraceDetector {

	public ExplosiveTraceDetector() {
	}

	public boolean test(char[][] sample) {
		for (char[] trace : sample) {
			String s = new String(trace);
			if (s.contains("exp")) {
				return true;
			}
		}
		return false;
	}
}
