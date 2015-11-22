package main;

import java.util.*;
import java.io.*;

public class Run {

	public static void main(String[] args) throws IOException {

		Vertex source = new Vertex(0, 0);
		int t = 0;
		Vertex destination = new Vertex(0, 0);
		Graph denseGraph = null;
		Graph sparseGraph = null;
		int vertexMaxDegree = 10;
		double startTime = 0, endTime = 0, duration = 0;
		Random rn = new Random();

		double[][] denseGraphData = new double[50][3];
		double[][] sparseGraphData = new double[50][3];
		int denseLopp = 0, sparseLoop = 0;
		System.out.print("Processing...");
		
		for (int outerLoop = 0; outerLoop < 5; outerLoop++) {

			int maxVertices = 5000;
			int maxDegree = 1000;
			int minDegree = 6;
			GraphGenerator gn = new GraphGenerator();
			sparseGraph = GraphGenerator.genSparseGraph(minDegree, maxVertices);

			for (int innerLoop = 0; innerLoop < 5; innerLoop++) {
				t = rn.nextInt(maxVertices);
				source = new Vertex(t, t);
				t = rn.nextInt(maxVertices);
				destination = new Vertex(t, t);
				GraphGenerator.connectSourceDestination(sparseGraph, source, destination);

//				System.out.print("iteration:" + outerLoop + " loop:" + innerLoop + "  for sparse graph ");
				sparseGraphData[sparseLoop][0] = calcTime("dijkstra", sparseGraph, source, destination);

//				System.out.print("iteration:" + outerLoop + " loop:" + innerLoop + "  for sparse graph ");
				sparseGraphData[sparseLoop][1] = calcTime("dijkstraHeap", sparseGraph, source, destination);

//				System.out.print("iteration:" + outerLoop + " loop:" + innerLoop + "  for sparse graph ");
				sparseGraphData[sparseLoop][2] = calcTime("kruskal", sparseGraph, source, destination);

				sparseLoop++;
				System.out.print(".");
			}

			denseGraph = GraphGenerator.genDenseGraph(maxDegree, maxVertices);

			for (int innerIteration = 0; innerIteration < 5; innerIteration++) {
				t = rn.nextInt(maxVertices);
				source = new Vertex(t, t);
				t = rn.nextInt(maxVertices);
				destination = new Vertex(t, t);
				GraphGenerator.connectSourceDestination(denseGraph, source, destination);

//				System.out.print("iteration:" + outerLoop + " loop:" + innerIteration + " for dense graph ");
				denseGraphData[denseLopp][0] = calcTime("dijkstra", denseGraph, source, destination);

//				System.out.print("iteration:" + outerLoop + " loop:" + innerIteration + "  for dense graph ");
				denseGraphData[denseLopp][1] = calcTime("dijkstraHeap", denseGraph, source, destination);

//				System.out.print("iteration:" + outerLoop + " loop:" + innerIteration + "  for dense graph ");
				denseGraphData[denseLopp][2] = calcTime("kruskal", denseGraph, source, destination);

				denseLopp++;
				System.out.print(".");
			}

		}

		// Printing the Dense Graph Statistics
		System.out.println();
		double[] avgDense = new double[3];
		double[] avgSparse = new double[3];
		System.out.println("GraphType,LoopCount,Dijkstra,DijkstraWithHeap,Kruskal");
		for (int i = 0; i < denseGraphData.length; i++) {

			
			System.out.println(String.format("Dense,%2d,%10.5f milliseconds,%10.5f milliseconds,%10.5f milliseconds", (i + 1),
					denseGraphData[i][0], denseGraphData[i][1], denseGraphData[i][2]));

			avgDense[0] += denseGraphData[i][0];
			avgDense[1] += denseGraphData[i][1];
			avgDense[2] += denseGraphData[i][2];

		}
		// printing average values
		for (int i = 0; i < 3; i++) {
			avgDense[i] /= 25;
		}
		System.out.println(String.format("Average of Dense,%2d,%10.5f milliseconds,%10.5f milliseconds,%10.5f milliseconds", 0, avgDense[0],
				avgDense[1], avgDense[2]));

		// sparse
		for (int i = 0; i < sparseGraphData.length; i++) {

			
			System.out.println(String.format("Sparse,%2d,%10.5f milliseconds,%10.5f milliseconds,%10.5f milliseconds", (i + 1),
					sparseGraphData[i][0], sparseGraphData[i][1], sparseGraphData[i][2]));

			avgSparse[0] += sparseGraphData[i][0];
			avgSparse[1] += sparseGraphData[i][1];
			avgSparse[2] += sparseGraphData[i][2];

		}
		// printing average values
		for (int i = 0; i < 3; i++) {
			avgSparse[i] /= 25;
		}
		System.out.println(String.format("Average of Sparse,%2d,%10.5f milliseconds,%10.5f milliseconds,%10.5f milliseconds", 0, avgSparse[0],
				avgSparse[1], avgSparse[2]));
		// Printing the Sparse Graph Statistics
		System.out.println("Completed");

	}

	private static double calcTime(String algoType, Graph G, Vertex source, Vertex destination) {
		double startTime = System.nanoTime();
		switch (algoType) {
		case "dijkstra":
			new Dijkstra(G, source, destination);
			break;
		case "kruskal":
			new Kruskal(G, source, destination);
			break;
		case "dijkstraHeap":
			new DijkstraHeap(G, source, destination);
			break;
		default:
			break;
		}
		double endTime = System.nanoTime();
		double duration = endTime - startTime;
		duration /= 1000000; // Getting Time in Millisecond
//		System.out.println(algoType + " completed");
		return duration;
	}
}
