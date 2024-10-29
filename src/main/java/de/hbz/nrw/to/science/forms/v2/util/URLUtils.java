package de.hbz.nrw.to.science.forms.v2.util;

import java.util.Base64;

public class URLUtils {
	
	public static String encode(String encodeMe) {
		return Base64.getEncoder().encodeToString(encodeMe.getBytes())
				.replaceAll("/", "-").replaceAll("\\+", "_");
	}

	public static String decode(String decodeMe) {
		return new String(Base64.getDecoder().decode(decodeMe));
	}

}
