package analyze;

public class SequenceCountPair implements Comparable<SequenceCountPair>{
	
	private String sequence;
	private int count;
	
	public SequenceCountPair(String sequence, int count) {
		this.sequence = sequence;
		this.count = count;
	}
	
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getSequence() {
		return sequence;
	}
	
	public int getCount() {
		return count;
	}

	@Override
	public int compareTo(SequenceCountPair o) {
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
