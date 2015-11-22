package main;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Dijkstra{
	public  Dijkstra(Graph G, Vertex source, Vertex destination) 
	{
		int s = source.value;
		int t = destination.value;
		int V=G.getV();
		if( !isValid(s, t, V) ){
			return;
		}		
		Map<Integer,String> vertexStatus = new HashMap<Integer, String>(V);
		Integer [] distance= new Integer[V];
		int[] parent = new int[V];
		
		for(int i=0;i<V;i++)
		{
			vertexStatus.put(i,"Unmarked");
			distance[i]=Integer.MIN_VALUE;  
		}
		
		vertexStatus.put(s,"Marked");
		parent[s]=-1; distance[s]=0;		
		
		int edgeWeight=-1;
		SortedSet<Vertex> externalVertex = new TreeSet<Vertex>();
		for(int i=0;i<V;i++)
		{	
			edgeWeight=G.getEdgeWeight(s, i);
			if(edgeWeight>0) 
			{
				vertexStatus.put(i,"Out");
				externalVertex.add(new Vertex(i,i));
				parent[i]=s;
				distance[i]=edgeWeight;
			}
		}
		
		
	
		while(externalVertex.size()>0)
		{
			Vertex v=externalVertex.first();
			externalVertex.remove(v);
			int vNumber=v.name;
			vertexStatus.put(vNumber,"Marked");
			for(int i=0;i<V;i++)
			{	
				edgeWeight=G.getEdgeWeight(vNumber, i);
				if(edgeWeight>0) 
				{	
					if (vertexStatus.get(i).equals("Unmarked")) {
						vertexStatus.put(i,"Out");
						distance[i]=Math.min(distance[vNumber], edgeWeight);
						parent[i]=vNumber;
						externalVertex.add(new Vertex(i,distance[i]));
						
						}
					else if(vertexStatus.get(i).equals("Out") && distance[i]<Math.min(distance[vNumber], edgeWeight)){
						updateVertex(externalVertex,i);
						distance[i]=Math.min(distance[vNumber],edgeWeight);
						parent[i]=vNumber; 
						externalVertex.add(new Vertex(i,distance[i]));
					}
						
				}
			}
		}
		int j = 0;
		return;
		
	}
	
	private boolean isValid(int s, int t, int V) {
		if(s<0 || t<0 || s>V-1 || t>V-1){
			return false;
		}else{
			return true;
		}
	}
	public static void updateVertex(SortedSet<Vertex> externalVertex, int vertexNumber)
	{
		Vertex vertex=null;
		Iterator<Vertex> it = externalVertex.iterator();
	    while(it.hasNext()) {
	    		vertex = it.next();
	            if(vertex.value == vertexNumber)             
	            {
	            	externalVertex.remove(vertex); break;
	            }
	        }
		
	}
}


