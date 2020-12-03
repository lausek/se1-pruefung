package de.dhbw;

import java.util.Random;

public class RandomUtils {

	private static Random random = new Random();

	public static int nextInt(int limit) {
		return random.nextInt(limit);
	}
}
