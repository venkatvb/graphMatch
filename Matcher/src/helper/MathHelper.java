package helper;

import core.Constants;

public class MathHelper {
	public static Long modularExponentiation(Long power) {
		if(power == 0) {
			return (long) 1;
		}
		if(power == 1) {
			return Constants.HASH_A;
		}
		long value = modularExponentiation(power / 2);
		if(value >= Constants.MOD) {
			value %= Constants.MOD;
		}
		value *= value;
		if(value >= Constants.MOD) {
			value %= Constants.MOD;
		}
		if(power%2 == 0) {
			value *= Constants.HASH_A;
		}
		if(value >= Constants.MOD) {
			value %= Constants.MOD;
		}
		return value;
	}
}
