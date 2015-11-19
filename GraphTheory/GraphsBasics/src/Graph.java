import java.util.*;
import java.io.*;

public class Graph {
	
	private static final int vertices = 100;
	private static final int edges = 10;
	private Map<Integer,List<Integer>> adj ; //adjacency list
	private final int V;  //vertex	
	private int E;  //edges
	
	
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
	}					
	
	public void addEdge(int v, int w){
		adj.get(v).add(w);
		adj.get(w).add(v);
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
