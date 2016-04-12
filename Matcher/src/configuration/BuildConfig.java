package configuration;

public class BuildConfig {
	// TRESHOLD must be an integer from 0 to 100
	public static final int TRESHOLD = 50;
	public static int QUERY_ENZYME_ID = 1;
	
	public static void setQueryEnzymeId(int id) {
		QUERY_ENZYME_ID = id;
	}	
}
