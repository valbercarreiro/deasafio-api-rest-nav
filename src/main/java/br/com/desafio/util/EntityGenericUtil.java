/**
 * 
 */
package br.com.desafio.util;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author valbercarreiro
 *
 */
public class EntityGenericUtil {

	public static String getString(Integer size) {
		return RandomStringUtils.randomAlphanumeric(size);
	}
	
	public static String getString() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static Long getLong() {
		return RandomUtils.nextLong(0, 999999);
	}
	
	public static Long getLong(int length) {
		long value = RandomUtils.nextLong(1111111111111111111L, 9219999999999999999L);
		return Long.parseLong(String.valueOf(value).substring(0, length));
	}
}
