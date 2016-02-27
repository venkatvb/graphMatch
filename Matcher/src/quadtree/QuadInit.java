package quadtree;

import helper.FileHelper;
import helper.FileNotFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import serialize.Serializable;

public class QuadInit implements Serializable{
	public static final String LOCATION = "F:\\Graph Databases\\nodevalues\\mapped.txt";
	public static final String GXL_BASE_LOCATION = "F:\\Graph Databases\\gxl\\";
	public static final String COMPARISION_RESULT_LOCATION = "F:\\Graph Databases\\comparision_results\\";
	String serailizeString;
	
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
	
	public void serializeStringInit() {
		serailizeString = new String();
	}
	
	public void serializeCurrentString(Integer node) throws FileNotFoundException, Exception {
		serailizeContent(FileHelper.createFile(COMPARISION_RESULT_LOCATION + node.toString() + ".txt"), serailizeString);
	}
	
	public Set<Integer> getIndexesOfSequence(String sequence) {
		Set<Integer> result = new HashSet<Integer>();
		String s = mp.get(sequence);
		if(s == null) {
			return result;
		}
		s = s.substring(1, s.length()-1);
		String fileIds[] = s.split(",");
		for(String item : fileIds) {
			Integer value = Integer.parseInt(item);
			result.add(value);
		}
		return result;
	}
	
	public void printIndexesOfSequence(String sequence, Integer node) throws Exception {
		Set<Integer> result = new HashSet<Integer>();
		File file = FileHelper.createFile(COMPARISION_RESULT_LOCATION + node.toString() + ".txt");
		String s = mp.get(sequence);
		System.out.println("Sequence : " + sequence );
		serailizeString += "Sequence : " + sequence + "\n";
		if(s == null) {
			System.out.println("Not found");
			serailizeString += "Not Found \n";
			throw new Exception();
		}
		s = s.substring(1, s.length()-1);
		String fileIds[] = s.split(",");
		for(String item : fileIds) {
//			System.out.println("String : " + item);
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
		serailizeString += res + "\n";
	}

	@Override
	public boolean serailizeContent(File file, String content) throws Exception {
		PrintWriter pw = new PrintWriter(new FileOutputStream(file), true);
		pw.write(content);
		pw.close();
		return true;
	}	
}
