package analyze;

import java.util.ArrayList;

public class SequenceListPair implements Comparable<SequenceListPair>{
	
	private String sequence;
	private ArrayList<Integer> list;
	
	public SequenceListPair(String sequence, ArrayList<Integer> list) {
		this.sequence = sequence;
		this.list = list;
	}
	
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public void setCount(ArrayList<Integer> list) {
		this.list = list;
	}
	
	public String getSequence() {
		return sequence;
	}
	
	public int getCount() {
		return this.list.size();
	}
	
	public ArrayList<Integer> getList() {
		return list;
	}

	public int compareTo(SequenceListPair o) {
		if(this.getCount() == o.getCount()) {
			if ( this.getSequence().compareTo(o.getSequence()) > 0 ) {
				return 1;
			} else {
				return -1;
			}
		}
		if(this.getCount() > o.getCount()) {
			return 1;
		}
		return -1;
	}

}
