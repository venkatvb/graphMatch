package core;

public class Hasher {
	
	public static final Long HASH_A = (long) 55;
	public static final Long MOD = (long) 1000000007;
	
	public static Long modularExponentiation(Long power) {
		if(power == 0) {
			return (long) 1;
		}
		if(power == 1) {
			return HASH_A;
		}
		long value = modularExponentiation(power / 2);
		if(value >= MOD) {
			value %= MOD;
		}
		value *= value;
		if(value >= MOD) {
			value %= MOD;
		}
		if(power%2 == 0) {
			value *= HASH_A;
		}
		if(value >= MOD) {
			value %= MOD;
		}
		return value;
	}
	
	public static Integer rollingHash(String input) {
		int result = 0;
		long power = (long)1;
		for(int i=0; i<input.length(); i++ ) {
			char ch = input.charAt(i);
			Long temp = new Long((int)ch);
			temp = temp * power;
			power *= HASH_A;
			if(power >= MOD) {
				power %= MOD;
			}
			result += temp;
			if(result >= MOD) {
				result %= MOD;
			}
		}
		return result;
	}
}
