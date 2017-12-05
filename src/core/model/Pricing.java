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
		String rowK = "";
		for (int i = 0; i < Settings.SLOT_DEFAULT_COLUMNS; ++i) {
			rowK += "K";
			priceMap.put(rowK, 300);
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
