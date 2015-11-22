package main;
import java.util.*;

import main.Vertex;
import main.Edge;


public class Graph {	
	private Map<Integer,List<Vertex>> adj ; //adjacency list
	private  int V;  //vertex	
	private int E;  //edges
	private  int[][] edgeMatrix   ;
	private List<Edge> edgeList = new ArrayList<Edge>();
	public void setV(int v) {
		V = v;
	}
	public void setE(int e) {
		E = e;
	}
	public int getV() {
		return V;
	}
	public int getE() {
		return E;
	}		
	public Graph(int V){
		this.V = V;
		this.E = 0;
		this.adj = new HashMap<Integer,List<Vertex>>();
		StringBuilder r = new StringBuilder();
		for(int i=0;i<V;i++){				
			Vertex v  = new Vertex(i,i);
			this.adj.put(i,new ArrayList<Vertex>(1000));			
			v=null;
		}
		r=null;
		//init edge matrix
		this.edgeMatrix = new int[V][V];
		for(int i=0;i<V;i++){
			for(int j=0;j<V;j++){
				this.edgeMatrix[i][j]=0;
			}
		}
	}					
	
	public void addEdge(Vertex v, Vertex w, int weight){
		
		List<Vertex> a = this.adj.get(v.name);
		if(a==null){
			a =  new ArrayList<Vertex>(1000);
		}		
		a.add(w);
		this.adj.put(v.name, a);	
		a=null;
		List<Vertex> b = this.adj.get(w.name);
		if(b==null){
			b =  new ArrayList<Vertex>(1000);
		}
		b.add(v);
		this.adj.put(w.name, b);
		b=null;
		
		this.edgeMatrix[v.value][w.value] = weight;
		this.edgeMatrix[w.value][v.value] = weight;
		this.edgeList.add(new Edge(v,w,weight));
		E++;
	}
	public int degree(Vertex v){
		int degree = 0;		;
		degree = adj(v.name).size();	
		return degree;
	}
	
	public List<Vertex> adj(int name){		
		 List<Vertex> out =   this.adj.get(name);
		 out = (out==null) ? new ArrayList<Vertex>() : out;
		 return out;
	}
	public boolean isConnected(Vertex v, Vertex w){
		if(adj(v.name).contains(w)){
			return true;
		}else{
			return false;
		}
	}
	public int getEdgeWeight(int from, int to){
		return this.edgeMatrix[from][to];
	}
	public Map<Integer,List<Vertex>> getAdj(){
		return this.adj;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges\n" );
		for(Integer v : this.adj.keySet()){
			s.append(v + ": ");
			for (Vertex w : this.adj.get(v))
				s.append( w + " ");
			s.append("\n");
		}
		
		return s.toString();
	}
	public List<Edge> getEdgeList() {
		// TODO Auto-generated method stub
		return this.edgeList;
	}
	
}
