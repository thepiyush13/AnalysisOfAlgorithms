package main;
public class Edge implements Comparable<Edge>{
	int from;
	int to;
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	int weight;
	public Edge(Vertex v,Vertex w ,  int weight){
		this.from = v.name;
		this.to = w.name;
		this.weight = weight;
		
	}
	public int compareTo(Edge edge)
    {
        if (this.getWeight() > edge.getWeight())
            return -1;
        if (this.getWeight() < edge.getWeight())
            return 1;
        return 0;
    }
	 
}