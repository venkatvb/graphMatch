package quadtree;

import helper.FileHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class QuadInit {
	public static final String LOCATION = "F:\\Graph Databases\\nodevalues\\mapped.txt";
	Map<String, String> mp;
	@SuppressWarnings("unused")
	public QuadInit() throws Exception {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(FileHelper.createFile(LOCATION));
		mp = new HashMap<String, String>();
		while(scanner.hasNextLine()) {
			String sequence = scanner.next();
			int count = scanner.nextInt();
			String values = scanner.next();
			mp.put(sequence, values);
		}
	}
	
	public Set<Integer> getIndexesOfSequence(String sequence) {
		Set<Integer> result = new HashSet<Integer>();
		String s = mp.get(sequence);
		if(s == null) {
			return result;
		}
		s = s.substring(1, s.length()-2);
		String fileIds[] = s.split(",");
		for(String item : fileIds) {
			Integer value = Integer.parseInt(item);
			result.add(value);
		}
		return result;
	}
	
	public void printIndexesOfSequence(String sequence) throws Exception {
		Set<Integer> result = new HashSet<Integer>();
		String s = mp.get(sequence);
		if(s == null) {
			System.out.println("Not found");
			throw new Exception();
		}
		s = s.substring(1, s.length()-2);
		String fileIds[] = s.split(",");
		for(String item : fileIds) {
			Integer value = Integer.parseInt(item);
			result.add(value);
		}
		String res = "{";
		for(Integer item : result ) {
			res = res + item.toString() + ", ";
		}
		res = res.substring(0, res.length()-2);
		res = res + "}";
		System.out.println(res);
	}
}
