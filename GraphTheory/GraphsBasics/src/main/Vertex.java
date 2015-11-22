package main;

public class Vertex implements Comparable<Vertex>{
	int name;
	int value;
	public Vertex(int name, int value){
		this.name = name;
		this.value = value;
	}
	public String toString(){
		return (this.name+"("+this.value+")");
	}
	public int compareTo(Vertex vertex)
    {
        if (this.value > vertex.value)
            return -1;
        if (this.value < vertex.value)
            return 1;
        return 0;
    }
}
