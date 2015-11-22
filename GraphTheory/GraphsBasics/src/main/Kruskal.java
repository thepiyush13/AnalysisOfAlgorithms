package main;

import java.util.*;
public class Kruskal {
	public static int heapSize=0;
	public  Kruskal(Graph graph, Vertex s, Vertex t) 
	{
		int source = s.name;
		int destination = t.name;
		int countVertices=graph.getV();
		
		Edge[] edgeList = new Edge[graph.getE()];
		graph.getEdgeList().toArray(edgeList);
		kruskalSort(edgeList);
		
		int[] parent = new int[countVertices];
		int[] rank = new int[countVertices];
		
		for(int i=0;i<countVertices;i++)
		{
			parent[i]=i;  
			rank[i]=0;
		}
		
		List<Edge> vertexTree=new ArrayList<Edge>();
		int u,v,v1,v2;
		Edge edge;
		
		for(int i=0;i<edgeList.length;i++)
		{
			edge=edgeList[i];
			u=edge.getFrom();
			v=edge.getTo();
			v1=find(u,parent);
			v2=find(v,parent);
			if(v1!=v2)
			{
				vertexTree.add(edge);
				Union(v1,v2,parent,rank);
				if(vertexTree.size()==countVertices-1)
					break;
			}
		}
		

	}
	public static void kruskalSort(Edge[] edgeList)
	{	 
		  kruskalHeapify(edgeList);        
	        for (int i = heapSize; i > 0; i--)
	        {
	            kruskalSwap(edgeList,0, i);
	            heapSize = heapSize-1;
	            kruskalMaxHeap(edgeList, 0);
	        }
	}
	public static void kruskalHeapify(Edge[] edgeList)
    {
        heapSize = edgeList.length-1;
        for (int i = heapSize/2; i >= 0; i--)
            kruskalMaxHeap(edgeList, i);        
    }
	public static void kruskalSwap(Edge[] edgeList, int left, int right)
    {

        Edge tmp = edgeList[left];
        edgeList[left] = edgeList[right]; 
        edgeList[right] = tmp;
    }
	public static void kruskalMaxHeap(Edge[] edgeList, int i)
    { 
    	int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= heapSize && edgeList[left].getWeight() < edgeList[i].getWeight())
            max = left;
        if (right <= heapSize && edgeList[right].getWeight() < edgeList[max].getWeight() )        
            max = right;
 
        if (max != i)
        {
            kruskalSwap(edgeList, i, max);
            kruskalMaxHeap(edgeList, max);
        }
    }  
	
	public static int find(int vertex, int[] parent)
	{
	    while (vertex != parent[vertex]) {
            parent[vertex] = parent[parent[vertex]]; 
            vertex = parent[vertex];
        }
        return vertex;
		
 	}
	public static void Union(int v1, int v2,int[] parent, int[] rank)
	{
		if(rank[v1]>rank[v2])
			parent[v1]=v2;
		else if(rank[v1]<rank[v2])
			parent[v2]=v1;
		else
		{
			parent[v1]=v2;
			rank[v1]=rank[v1]+1;
		}
	}


}
