package main;

import java.util.*;


public class DijkstraHeap {
	public static int heapCount = 0;
	public static int [] heapMatrix;
	public  DijkstraHeap(Graph G, Vertex source, Vertex destination) 
	{
		int s = source.value;
		int t = destination.value;
		int V=G.getV();
		heapMatrix = new int[V];
		if( !isValid(s, t, V) ){
			return;
		}
		
		Map<Integer,String> vertexStatus = new HashMap<Integer, String>(V);
		Integer [] distance= new Integer[V];
		int[] parent = new int[V];
		
		
		for(int i=0;i<V;i++)
		{
			vertexStatus.put(i,"Unmarked");
		}
		Map<Integer,Vertex> vertexArray=new HashMap<Integer,Vertex>(V);
		vertexStatus.put(s,"Marked");
		parent[s]=0; distance[s]=0;		
		
		int edgeWeight=-1;
		for(int i=0;i<V;i++)
		{	
			edgeWeight=G.getEdgeWeight(s, i);
			if(edgeWeight!=0) 
			{
				vertexStatus.put(i,"Out");
				parent[i]=s;
				distance[i]=edgeWeight;
				heapMatrix[i] = insertMaxHeap(vertexArray, new Vertex(i,distance[i]));
			}
		}
		
		
		while(heapCount>0)
		{
			int vNumber=vertexArray.get(0).name;
			deleteMaxHeap(vertexArray, heapMatrix[vNumber]);
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
						heapMatrix[i] = insertMaxHeap(vertexArray, new Vertex(i,distance[i]));
						
						}
					else if(vertexStatus.get(i).equals("Out") && distance[i]<Math.min(distance[vNumber], edgeWeight)){
						deleteMaxHeap(vertexArray, heapMatrix[i]);
						distance[i]=Math.min(distance[vNumber],edgeWeight);
						parent[i]=vNumber; 
						heapMatrix[i] = insertMaxHeap(vertexArray, new Vertex(i,distance[i]));
					}
						
				}
			}
		}
		int j = 0;
		return;
		
	}
	
	public static int insertMaxHeap(Map<Integer,Vertex> vertexArray, Vertex x) {  
    	heapCount=heapCount+1;
    	int insertionPoint = heapCount-1;
    	vertexArray.put(insertionPoint,x);
    	int t; 
    	Vertex temp;
    	if(insertionPoint%2 == 0)
    		t = insertionPoint/2 - 1;
    	else
    		t = insertionPoint/2;
    	while(insertionPoint > 0 && vertexArray.get(insertionPoint).value > vertexArray.get(t).value) {  
    		temp = vertexArray.get(insertionPoint);  
    		vertexArray.put(insertionPoint,vertexArray.get(t));
    		vertexArray.put(t,temp);
    		heapMatrix[vertexArray.get(insertionPoint).name]=insertionPoint;
    		insertionPoint = t;
    		if(insertionPoint%2 == 0)
    			t = insertionPoint/2 - 1;
    		else
    			t = insertionPoint/2;
    	}	
    	return insertionPoint; 
    }
	static void deleteMaxHeap(Map<Integer,Vertex> vertexArray, int x) { 
		vertexArray.put(x, vertexArray.get(heapCount-1));
    	heapMatrix[vertexArray.get(heapCount-1).name]=x;
		heapCount=heapCount-1;
    	maxHeapify(vertexArray, x);
    }
	static void maxHeapify(Map<Integer,Vertex> vertexArray, int i) {
    	int left,right,largest;
    	Vertex temp;
    	left = 2*i + 1;
    	right = 2*i + 2;
    	if(left < heapCount && vertexArray.get(left).value > vertexArray.get(i).value)
    		largest = left;
    	else
    		largest = i;
    	if(right < heapCount && vertexArray.get(right).value > vertexArray.get(largest).value)
    		largest = right;
    	if(largest != i) {
    		temp = vertexArray.get(largest);
    		vertexArray.put(largest,vertexArray.get(i));
    		vertexArray.put(i,temp);
    		heapMatrix[vertexArray.get(i).name]=i;
    		heapMatrix[vertexArray.get(largest).name]=largest;
    		maxHeapify(vertexArray, largest);
    	}
    }
	private boolean isValid(int s, int t, int V) {
		if(s<0 || t<0 || s>V-1 || t>V-1){
			return false;
		}else{
			return true;
		}
	}
}
