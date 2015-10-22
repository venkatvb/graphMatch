package helper;

import java.util.Arrays;

import core.Constants;

public class MathHelper {
	
	public static final int MAXS = 1000009;
	static int[] primes = new int[MAXS];
	public static boolean primeSieved = false;
	
	public static int getHypoteneousDistance(int adjacent, int opposite) {
		int hypoteneous = ( adjacent * adjacent ) + ( opposite * opposite );
		return (int) Math.sqrt(hypoteneous);
	}
	
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
	
	public static void sieve() {
		Arrays.fill(primes, 0);
		primes[0] = 1;
		primes[1] = 1;
		for(int i=2; i<MAXS; ++i ) { 
			if(primes[i] == 1) {
				continue;
			}
			for(int j=i+i; j<MAXS; j+=i ) {
				primes[j] = 1;
			}
		}
		primeSieved = true;
	}
	
	public static Integer getLargePrime() throws Exception {
		if(primeSieved == false) {
			sieve();
		}
		for(int i=MAXS-1; i>=0; --i ) {
			if(primes[i] == 0) {
				return new Integer(i);
			}
		}
		throw new SieveException("Sieve is not generated.");
	}
}
