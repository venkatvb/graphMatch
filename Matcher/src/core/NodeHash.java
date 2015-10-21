package core;

public class NodeHash implements Comparable<NodeHash> {
	private String nodeHash;
	private String edgeHash;
	
	public NodeHash() {
		nodeHash = "";
		edgeHash = "";
	}
	
	public void setNodeHash(String nodeHash) {
		this.nodeHash = nodeHash;
	}
	
	public void setEdgeHash(String edgeHash) {
		this.edgeHash = edgeHash;
	}
	
	public String getNodeHash() {
		return this.nodeHash;
	}
	
	public String getEdgeHash() {
		return this.edgeHash;
	}
	
	@Override
	public int compareTo(NodeHash obj) {
		if(this.getNodeHash().equals(obj.getNodeHash()) && this.getEdgeHash().equals(obj.getEdgeHash())) {
			return 0;
		}
		if(this.getNodeHash().compareTo(obj.getNodeHash()) == 0) {
			return this.getEdgeHash().compareTo(obj.getEdgeHash());
		}
		return this.getNodeHash().compareTo(obj.getNodeHash());
	}
	
	public boolean equals(NodeHash obj) {
		return (this.getNodeHash().equals(obj.getNodeHash()) && this.getEdgeHash().equals(obj.getEdgeHash()));
	}
}
