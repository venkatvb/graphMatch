package graph;

public class Edge implements Comparable<Edge>{
	public Integer sourceNodeId;
	public Integer destinationNodeId;
	public Double distance;
	
	public Edge(int source, int destination, double distance) {
		this.sourceNodeId = source;
		this.destinationNodeId = destination;
		this.distance = distance;
	}
	
	public void setSourceNodeId(Integer source) {
		this.sourceNodeId = source;
	}
	
	public void setDestinationNodeId (Integer destination ) {
		this.destinationNodeId = destination;
	}
	
	public void setDistance (Double distance) {
		this.distance = distance;
	}
	
	public Integer getSourceNodeId() {
		return this.sourceNodeId;
	}
	
	public Integer getDestinationNodeId() {
		return this.destinationNodeId;
	}
	
	public Double getDistance() {
		return this.distance;
	}

	@Override
	public int compareTo(Edge edge) {
		if(this.getDistance() < edge.getDistance()) {
			return -1;
		}
		if(this.getDistance() == edge.getDistance()) {
			return 0;
		}
		return 1;
	}
}
