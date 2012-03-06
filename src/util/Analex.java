package util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Analex {
	private static Pattern miPattern;
	private static Matcher matcher;

	public static String get(String regex, String texto) {
		String temp = "";

		miPattern = Pattern.compile(regex, Pattern.CANON_EQ
				| Pattern.UNICODE_CASE | Pattern.DOTALL);

		matcher = miPattern.matcher(texto);

		while (matcher.find()) {
			temp += matcher.group();
		}
		return temp;
	}
}
