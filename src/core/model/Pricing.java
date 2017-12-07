package core.model;

import java.util.HashMap;
import java.util.Map;

import core.settings.Settings;

public class Pricing {

	private static Map<String, Integer> priceMap = new HashMap<>();

	public static void initialize() {

		// KOK
		priceMap.put("KOK", 1000);

		// All Ks
		String row = "";
		for (int i = 0; i < Settings.SLOT_DEFAULT_COLUMNS; ++i) {
			row += "K";
			priceMap.put(row, 300);
		}
		// All Os
		row = "";
		for (int i = 0; i < Settings.SLOT_DEFAULT_COLUMNS; ++i) {
			row += "O";
			priceMap.put(row, 300);
		}
		// All cherries
		row = "";
		for (int i = 0; i < Settings.SLOT_DEFAULT_COLUMN_HEIGHT; ++i) {
			row += "cherry";
			priceMap.put(row, 100);
		}
		// All bananas
		row = "";
		for (int i = 0; i < Settings.SLOT_DEFAULT_COLUMN_HEIGHT; ++i) {
			row += "banana";
			priceMap.put(row, 50);
		}
		// All progmeths
		row = "";
		for (int i = 0; i < Settings.SLOT_DEFAULT_COLUMN_HEIGHT; ++i) {
			row += "progmeth";
			priceMap.put(row, 10000);
		}
	}

	public static Map<String, Integer> getMap() {
		return priceMap;
	}

	public static int getPrice(String slotCode) {
		if (!priceMap.containsKey(slotCode))
			return 0;
		return priceMap.get(slotCode);
	}

}
