package core;

public class Hasher {
	
	public static Integer rollingHash(String input) {
		int result = 0;
		long power = (long)1;
		for(int i=0; i<input.length(); i++ ) {
			char ch = input.charAt(i);
			Long temp = new Long((int)ch);
			temp = temp * power;
			power *= Constants.HASH_A;
			if(power >= Constants.MOD) {
				power %= Constants.MOD;
			}
			result += temp;
			if(result >= Constants.MOD) {
				result %= Constants.MOD;
			}
		}
		return result;
	}
}
