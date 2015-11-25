package main;

import java.util.*;
import java.io.*;

public class GraphGenerator {

	public static void main(String[] args) {
/*		int maxVertices = 500;
		int maxDegree = 1000;
		int minDegree = 6;
		Graph G = genSparseGraph(minDegree, maxVertices);
		Vertex s = new Vertex(0, 0);
		Vertex t = new Vertex(0, 300);
		GraphFunctions gFun = new GraphFunctions();
		gFun.createPath(G, s, t);
		Dijkstra djk = new Dijkstra(G, s, t);*/

	}

	public static Graph genDenseGraph(int maxDegree, int maxVertices) {
		// Get random elements
		Graph MyGraph = new Graph(maxVertices);
		for (int i = 0; i < maxVertices; i++) {
			addEdgesToGraph(maxDegree, maxVertices, MyGraph, i);

		}
		// System.out.println(MyGraph.toString());
		return MyGraph;

	}

	public static Graph genSparseGraph(int maxDegree, int maxVertices) {
		// Get random elements
		Graph MyGraph = new Graph(maxVertices);

		for (int i = 0; i < maxVertices; i++) {
			addEdgesToGraph(maxDegree, maxVertices, MyGraph, i);

		}
		return MyGraph;

		// saveGraphToFile(MyGraph,"Vertices"+maxVertices+"Degree"+maxDegree+"Graph.txt")
		// ;
		/*
		 * fileName = "Vertices"+maxVertices+"Degree"+maxDegree+"Graph.txt";
		 * if(saveGraphToFile(MyGraph,fileName)){
		 * System.out.println("File:"+fileName+" saved successfully!"); };
		 */

	}

	private static void addEdgesToGraph(int maxDegree, int maxVertices,
			Graph MyGraph, int counter) {
		// init
		Random randomGenerator = new Random();
		Random rndWeight = new Random();
		int weight = 0;
		int maxWeight = 100;
		boolean validEdge = true;
		int maxEdges = maxVertices * maxVertices;
		Vertex rndNode;
		int rndInt = 0;
		// pick any one and get it`s degree
		Vertex node = new Vertex(counter, counter);// randomGenerator.nextInt(maxVertices);
		// if degree is less then required find number of edges to add
		int nodeDegree = MyGraph.degree(node);
		// System.out.println("node:"+node.toString());
		if (nodeDegree < maxDegree) {
			int reqEdges = maxDegree - nodeDegree;
			for (int j = 1; j <= reqEdges; j++) {
				// to be added node should not be same, already connected or
				// filled with edges
				rndInt = randomGenerator.nextInt(maxVertices);
				rndNode = new Vertex(rndInt, rndInt);
				int count = 0;
				validEdge = true;
				while (rndNode.equals(node)
						|| MyGraph.degree(rndNode) >= maxDegree
						|| MyGraph.isConnected(node, rndNode)) {
					rndInt = randomGenerator.nextInt(maxVertices);
					rndNode = new Vertex(rndInt, rndInt);
					if (count >= maxEdges) {
						validEdge = false;
						break;
					}
					count++;

				}
				// add edge for this node
				if (validEdge == true) {
					weight = rndWeight.nextInt(maxWeight);
					MyGraph.addEdge(node, rndNode, weight);
				}
			}
		}
	}

	public static boolean saveGraphToFile(Graph MyGraph, String fileName) {

		File file;
		BufferedWriter bfr = null;
		try {
			file = new File("./res/" + fileName);
			bfr = new BufferedWriter(new FileWriter(file));
			int V = MyGraph.getV();
			Map<Integer, List<Vertex>> x = MyGraph.getAdj();
			for (Integer v : x.keySet()) {
				StringBuilder s = new StringBuilder();
				s.append(v.toString() + " ");
				for (Vertex w : MyGraph.adj(v))
					s.append(w.toString() + " ");
				s.append("\n");
				bfr.write(s.toString());
				s = null;
			}
			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			try {
				bfr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static boolean saveTextToFile(String text, String fileName) {
		try {
			File file = new File("./res/" + fileName);
			BufferedWriter bfr = new BufferedWriter(new FileWriter(file));
			bfr.write(text);
			bfr.close();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static void connectSourceDestination(Graph graph, Vertex source, Vertex destination)
	{
				List<Integer> unmarkedVertices=new ArrayList<Integer>();
				List<Integer> markedVertices=new ArrayList<Integer>();
				int limit  = 20;
				int initialVertex=0, finalVertex=0;
				for(int i=0; i<graph.getV();i++)
				{	
					if(i!=source.name && i!=destination.name)
					unmarkedVertices.add(i);
				}
						
				Random rn = new Random();  
				int rndUnmarkedVertex=0, rndMarkedVertex=0, rndWeight=0;
				while (unmarkedVertices.size()!=0)
				{
							
					rndMarkedVertex = markedVertices.size()==0 ? -1 : rndUnmarkedVertex;	
					rndUnmarkedVertex = unmarkedVertices.get(rn.nextInt(unmarkedVertices.size()));
							
					if(rndMarkedVertex!=-1)
					{	
						rndWeight=rn.nextInt(limit)+2;
						graph.addEdge(new Vertex(rndMarkedVertex,rndMarkedVertex),
								new Vertex(rndUnmarkedVertex,rndUnmarkedVertex), rndWeight);
						markedVertices.add((Integer)rndUnmarkedVertex);
						unmarkedVertices.remove((Integer)rndUnmarkedVertex);
						
					}
					else if (rndMarkedVertex == -1)
					{	
						initialVertex=rndUnmarkedVertex;
						markedVertices.add(rndUnmarkedVertex); 
						unmarkedVertices.remove((Integer)rndUnmarkedVertex);
								
					}
	
				}
				
				finalVertex=rndUnmarkedVertex;
				rndWeight=rn.nextInt(limit)+2;
				graph.addEdge(source,new Vertex(initialVertex,initialVertex), rndWeight);
				
				rndWeight=rn.nextInt(limit)+2;
				graph.addEdge(destination,new Vertex(finalVertex,finalVertex), rndWeight);
		
	}

}
