package main;
import java.util.*;

import main.Vertex;


public class Graph {	
	private Map<Vertex,List<Vertex>> adj ; //adjacency list
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
		this.adj = new HashMap<Vertex,List<Vertex>>();
		for(int i=0;i<V;i++){	
			Vertex v  = new Vertex("vertex"+i,i);
			this.adj.put(v,new ArrayList<Vertex>());
		}
		//init edge matrix
		this.edgeMatrix = new int[V][V];
		for(int i=0;i<V;i++){
			for(int j=0;j<V;j++){
				this.edgeMatrix[i][j]=0;
			}
		}
	}					
	
	public void addEdge(Vertex v, Vertex w, int weight){
		
		this.adj.get(v).add(w);
		this.adj.get(w).add(v);
		this.edgeMatrix[v.value][w.value] = weight;
		this.edgeMatrix[w.value][v.value] = weight;
		E++;
	}
	public int degree(Vertex v){
		int degree = 0;		;
		for(Vertex w : adj(v)){
			degree++;			
		}		
		return degree;
	}
	
	public List<Vertex> adj(Vertex v){		
		 List<Vertex> out =   this.adj.get(v);
		 out = (out==null) ? new ArrayList<Vertex>() : out;
		 return out;
	}
	public boolean isConnected(Vertex v, Vertex w){
		for(Vertex x : adj(v)){
			if(x.equals(w)){
				return true;
			}
		}
		return false;
	}
	public int getEdgeWeight(int from, int to){
		return this.edgeMatrix[from][to];
	}
	public Map<Vertex,List<Vertex>> getAdj(){
		return this.adj;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges\n" );
		for(Vertex v : this.adj.keySet()){
			s.append(v + ": ");
			for (Vertex w : this.adj(v))
				s.append( w + " ");
			s.append("\n");
		}
		
		return s.toString();
	}
}
