package analyze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import serialize.Serializable;

public class ComplexityAnalyzer implements Serializable{
	
	// Here N is the average number of nodes in a single graph
	// Here m denotes the number of gaphs in the database
	// start, end and difference denotes the query to be counted from the start location to end location
	// with the given difference. 
	int n, m, start, end, diff;
	public static final int DEFAULT_NUMBER_OF_QUERIES = 100;
	private ArrayList<String> cost;
	public ComplexityAnalyzer(int n, int m, int start, int end, int diff) {
		this.n = n;
		this.m = m;
		this.start = start;
		this.end = end;
		this.diff = diff;
		this.cost = new ArrayList<String>();
	}
	
	public ComplexityAnalyzer(int n, int m) {
		this.n = n;
		this.m = m;
		this.start = DEFAULT_NUMBER_OF_QUERIES;
		this.end = DEFAULT_NUMBER_OF_QUERIES;
		this.diff = DEFAULT_NUMBER_OF_QUERIES;
		this.cost = new ArrayList<String>();
	}
	
	private double log(double d, double n) {
		return Math.log(d) / Math.log(n);
	}
	
	@Override
	public boolean serailizeContent(File file) {
		try {
			PrintWriter pw = new PrintWriter(file);
			for(int i=start; i<=end; i += diff ) {
				// here I gives the query value
				Integer Query = i;
				Integer staticCost = (int) (n*m*log(n*m, 2)+ (Query * log(n*m, 4)));
				pw.println("Static cost for " + Query + " queries is : " + staticCost);
				for(int j=m; j>=1; j-- ) {
					Integer dynamicCost = (int)( (Query * (n*j*log(n*j,2))) + Query*log(n*j, 4));
					pw.println("\tDynamic Cost for m as " + j + " : " + dynamicCost);
					if(staticCost > dynamicCost) {
						cost.add("Static Cost : " + staticCost + ", Dynamic Cost : " + dynamicCost + " is better for " + Query + " number of queries and " + n*j + " number of data points"); 
					}
				}
			}
			for(String s : cost) {
				pw.println(s);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
