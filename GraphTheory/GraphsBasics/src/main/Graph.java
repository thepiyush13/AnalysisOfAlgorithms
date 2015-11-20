package main;
import java.util.*;
import java.io.*;

public class Graph {
	
	private static final int vertices = 100;
	private static final int edges = 10;
	private Map<Integer,List<Integer>> adj ; //adjacency list
	private final int V;  //vertex	
	private int E;  //edges
	private  int[][] edgeMatrix   ;
	
	public int getV() {
		return V;
	}
	public int getE() {
		return E;
	}		
	public Graph(int V){
		this.V = V;
		this.E = 0;
		this.adj = new HashMap<Integer,List<Integer>>();
		for(int i=0;i<V;i++){			
			this.adj.put(i,new ArrayList<Integer>());
		}
		//init edge matrix
		this.edgeMatrix = new int[V][V];
		for(int i=0;i<V;i++){
			for(int j=0;j<V;j++){
				this.edgeMatrix[i][j]=0;
			}
		}
	}					
	
	public void addEdge(int v, int w, int weight){
		adj.get(v).add(w);
		adj.get(w).add(v);
		this.edgeMatrix[v][w] = weight;
		this.edgeMatrix[w][v] = weight;
		E++;
	}
	public int degree(int v){
		int degree = 0;
		for(int w: adj(v)){
			degree++;
		}
		return degree;
	}
	
	public Iterable<Integer> adj(int v){
		return adj.get(v);
	}
	public boolean isConnected(int v, int w){
		for(int x : adj(v)){
			if(x==w){
				return true;
			}
		}
		return false;
	}
	public int getEdgeWeight(int from, int to){
		return this.edgeMatrix[from][to];
	}
	

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges\n" );
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : this.adj(v))
				s.append( w + " ");
			s.append("\n");
		}
		return s.toString();
	}
}
