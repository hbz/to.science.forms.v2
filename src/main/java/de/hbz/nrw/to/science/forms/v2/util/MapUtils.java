package de.hbz.nrw.to.science.forms.v2.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapUtils {
	
	// Methode zum Abrufen der Map, sortiert nach den Werten
	/**
	 * Order map alphabetically
	 * @param map 
	 * @return odered Map
	 */
    public static Map<String, String> getSortedMapByValues(Map<String, String> map) {
        return map.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new));
    }
}
