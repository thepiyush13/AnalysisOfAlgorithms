package main;

import java.util.*;
public class Kruskal {
	public static int heapSize=0;
	public  Kruskal(Graph graph, Vertex s, Vertex t) 
	{
		int countVertices = graph.getV();
		Edge[] edgeList = new Edge[graph.getE()];
		graph.getEdgeList().toArray(edgeList);
		heapSize = edgeList.length;
		heapSize--;
		int heapCount = heapSize / 2;
		while (heapCount >= 0) {
			kruskalMaxHeap(edgeList, heapCount);
			heapCount--;
		}

		heapCount = heapSize;
		Edge tmpEdge;
		while (heapCount > 0) {
			
			tmpEdge = edgeList[0];
			edgeList[0] = edgeList[heapCount];
			edgeList[heapCount] = tmpEdge;			
			heapSize--;
			kruskalMaxHeap(edgeList, 0);
			//kruskalMaxHeap(edgeList, heapCount);
			heapCount--;
		}
		
		Map<Integer, Integer> rank = new HashMap<Integer, Integer>();
		Map<Integer, Integer> parent = new HashMap<Integer, Integer>();

		for (int i = 0; i < graph.getV(); i++) {
			rank.put(i, 0);
			parent.put(i, i);
		}

		List<Edge> vertexTree = new ArrayList<Edge>();
		int v1, v2;

		int count = 0;
		int length = edgeList.length;
		while (count < length) {
			v1 = edgeList[count].getFrom();
			while (v1 != parent.get(v1)) {
				parent.put(v1, parent.get(parent.get(v1)));
				v1 = parent.get(v1);
			}
			v2 = edgeList[count].getTo();
			while (v2 != parent.get(v2)) {
				parent.put(v2, parent.get(parent.get(v2)));
				v2 = parent.get(v2);
			}
			if (v1 != v2) {
				vertexTree.add(edgeList[count]);
				Union(v1, v2, parent, rank);
				if (vertexTree.size() + 1 == countVertices) {
					break;
				}
			}
			count++;
		}
		

	}

	
	public static void kruskalMaxHeap(Edge[] edgeList, int count) {

		int maxVal = count;
		int rightVal = 2 * count + 1;
		int leftVal = 2 * count;
				
		 if(validWeight(edgeList, count, leftVal)) {
				maxVal = leftVal;
			} 
			if (validWeight(edgeList, maxVal, rightVal)) {
				maxVal = rightVal;
			}
			
		
		if (count != maxVal) {
			
			Edge tmpEdge = edgeList[count];
			edgeList[count] = edgeList[(maxVal)];
			edgeList[(maxVal)] = tmpEdge;
			kruskalMaxHeap(edgeList, maxVal);
		}
		//
		
	}


	private static boolean validWeight(Edge[] edgeList, int count, int val) {
		return ( (val <= heapSize) && (edgeList[val].getWeight() < edgeList[count].getWeight() )  );
	}

	
	
	public static void Union(int v1, int v2, Map<Integer, Integer> parent, Map<Integer, Integer> rank) {
		if (rank.get(v1) < rank.get(v2))
			parent.put(v2, v1);
		else if (rank.get(v1) > rank.get(v2))
			parent.put(v1, v2);
		else {
			rank.put(v1, rank.get(v1) + 1);
			parent.put(v1, v2);

		}
	}


}