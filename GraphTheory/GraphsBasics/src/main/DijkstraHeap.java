package main;

import java.util.*;


public class DijkstraHeap {
	public static int heapCount = 0;
	public static int [] heapMatrix;
	public  DijkstraHeap(Graph G, Vertex source, Vertex destination) 
	{
		int s = source.value;
		int t = destination.value;
		int count =0;
		int V=G.getV();
		heapMatrix = new int[V];
		if( !isValid(s, t, V) ){
			return;
		}
		
		Map<Integer,String> vertexStatus = new HashMap<Integer, String>(V);
		Integer [] distance= new Integer[V];
		int[] parent = new int[V];
		
		
		while(count < V){
			vertexStatus.put(count,"Unmarked");
			count++;			
		}
		Map<Integer,Vertex> vertexArray=new HashMap<Integer,Vertex>(V);
		vertexStatus.put(s,"Marked");
		parent[s]=0; distance[s]=0;		
		
		int edgeWeight=-1;
		count = 0;
		while(count < V){
			edgeWeight=G.getEdgeWeight(s, count);
			if(edgeWeight!=0) 
			{
				vertexStatus.put(count,"Out");
				parent[count]=s;
				distance[count]=edgeWeight;
				heapMatrix[count] = insertMaxHeap(vertexArray, new Vertex(count,distance[count]));
			}
			count++;
		}
		
		
		while(heapCount>0)
		{
			int vNumber=vertexArray.get(0).name;
			vertexArray.put(heapMatrix[vNumber], vertexArray.get(heapCount-1));
	    	heapMatrix[vertexArray.get(heapCount-1).name]=heapMatrix[vNumber];
			heapCount=heapCount-1;
	    	maxHeapify(vertexArray, heapMatrix[vNumber]);
			vertexStatus.put(vNumber,"Marked");
			for(count=0;count<V;count++)
			{	
				edgeWeight=G.getEdgeWeight(vNumber, count);
				if(edgeWeight>0) 
				{	
					if (vertexStatus.get(count).equals("Unmarked")) {
						vertexStatus.put(count,"Out");
						distance[count]=Math.min(distance[vNumber], edgeWeight);
						parent[count]=vNumber;
						heapMatrix[count] = insertMaxHeap(vertexArray, new Vertex(count,distance[count]));
						
						}
					else if(vertexStatus.get(count).equals("Out") && distance[count]<Math.min(distance[vNumber], edgeWeight)){
						int x =  heapMatrix[count];
						vertexArray.put(x, vertexArray.get(heapCount-1));
				    	heapMatrix[vertexArray.get(heapCount-1).name]=x;
						heapCount=heapCount-1;
				    	maxHeapify(vertexArray, x);					
						distance[count]=Math.min(distance[vNumber],edgeWeight);
						parent[count]=vNumber; 
						heapMatrix[count] = insertMaxHeap(vertexArray, new Vertex(count,distance[count]));
					}
						
				}
			}
		}
		int j = 0;
		return;
		
	}
	
	public static int insertMaxHeap(Map<Integer,Vertex> vertexArray, Vertex x) {  
    	int insertionPoint = heapCount;
    	heapCount++;
    	vertexArray.put(insertionPoint,x);
    	int t = (insertionPoint%2 == 0) ? (insertionPoint/2 -1) : (insertionPoint/2); 
    	Vertex temp;
    	
    	while(goAhead(vertexArray,insertionPoint,t)) {  
    		temp = vertexArray.get(insertionPoint);  
    		vertexArray.put(insertionPoint,vertexArray.get(t));
    		vertexArray.put(t,temp);
    		heapMatrix[vertexArray.get(insertionPoint).name]=insertionPoint;
    		insertionPoint = t;
    		 t = (insertionPoint%2 == 0) ? (insertionPoint/2 -1) : (insertionPoint/2);
    	}	
    	return insertionPoint; 
    }
	
	static void maxHeapify(Map<Integer,Vertex> vertexArray, int count) {
		
    	int rightVal = 2 * count + 2;
		int leftVal = 2 * count + 1;
		int maxVal = count;
		
		if(rightVal < heapCount){
			 if (vertexArray.get(rightVal).value > vertexArray.get(count).value) {
					maxVal = rightVal;
				}
		}
		 if (leftVal < heapCount) {
			if (vertexArray.get(leftVal).value > vertexArray.get(count).value) {
				maxVal = leftVal;
			} 
		}
		if (count != maxVal) {
			Vertex temp = vertexArray.get(maxVal);
    		vertexArray.put(maxVal,vertexArray.get(count));
    		vertexArray.put(count,temp);
    		heapMatrix[vertexArray.get(count).name]=count;
    		heapMatrix[vertexArray.get(maxVal).name]=maxVal;
    		maxHeapify(vertexArray, maxVal);
		}
    }
	private boolean isValid(int s, int t, int V) {
		boolean valid = true;
		if( s>V-1 ){
			valid = false;
		}
		else if(t>V-1){
			valid = false;
		}
		
		return valid;
	}
	public static boolean goAhead(Map<Integer,Vertex> vertexArray, int i, int t){
		if(i > 0 && vertexArray.get(i).value > vertexArray.get(t).value  ){
			return true;
		}
		else{
			return false;
		}
		
	}

}