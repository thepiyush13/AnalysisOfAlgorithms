package main;

public class Vertex{
	String name;
	int value;
	public Vertex(String name, int value){
		this.name = name;
		this.value = value;
	}
	public String toString(){
		return (this.name+"("+this.value+")");
	}
}
