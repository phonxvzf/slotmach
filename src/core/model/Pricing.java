package core.model;

import java.util.HashMap;
import java.util.Map;

public class Pricing {
	
	private static Map<String, Integer> priceMap = new HashMap<>();

	public static void initialize() {
		priceMap.put("KOK", 1000);
	}
	
	public static Map<String, Integer> getMap() {
		return priceMap;
	}
	
	public static int getPrice(String slotCode) {
		if (!priceMap.containsKey(slotCode)) return 0;
		return priceMap.get(slotCode);
	}

}
