package main;
import java.util.*;
import java.io.*;

public class GraphGenerator {
	
	public static void main(String[] args) {	
		int maxVertices = 5000;
		int maxDegree = 1000;
		int minDegree = 6;
		genDenseGraph(maxDegree,maxVertices);
		//genSparseGraph(minDegree,maxVertices);
		
			
	}
	private static void genDenseGraph(int maxDegree, int maxVertices){
		//Get random elements		
		Graph MyGraph   = new Graph(maxVertices);		
		Random randomGenerator = new Random();	
		Random rndWeight = new Random();
		int weight = 0;
		int maxWeight = 100;
		boolean getOut = false;	
		int maxEdges = maxVertices*10;
		boolean validEdge = true;	
		String fileName = "";
		int rndNode = 0;
		for(int i=0;i<maxVertices;i++){			
			//pick any one and get it`s degree
			int node = i;//randomGenerator.nextInt(maxVertices);			
			//if degree is less then required find number of edges to add
			int nodeDegree  = MyGraph.degree(node);
			System.out.println("node:"+node);
			if(nodeDegree < maxDegree){
				int reqEdges = maxDegree-nodeDegree ;
				for(int j=1;j<=reqEdges;j++){					
					//to be added node should not be same, already connected or filled with edges	
					rndNode = randomGenerator.nextInt(maxVertices);
					int count=0;
					validEdge = true;
					while(rndNode==node || MyGraph.degree(rndNode)>=maxDegree || MyGraph.isConnected(node, rndNode) ){
						rndNode = randomGenerator.nextInt(maxVertices);							
						if(count >= maxEdges){
							validEdge = false;
							break;
						}
						count++;
						
					}
					//add edge for this node
					if(validEdge==true)
						weight = rndWeight.nextInt(maxWeight);
						MyGraph.addEdge(node, rndNode,weight);
				}
			}
			
		}
		//System.out.println(MyGraph.toString());
		//saveGraphToFile(MyGraph,"Vertices"+maxVertices+"Degree"+maxDegree+"Graph.txt")  ;		
		fileName = "Vertices"+maxVertices+"Degree"+maxDegree+"Graph.txt";
		if(saveGraphToFile(MyGraph,fileName)){
			System.out.println("File:"+fileName+" saved successfully!");
		};		
		
		
		
	}
	private static void genSparseGraph(int maxDegree, int maxVertices){
		//Get random elements		
		Graph MyGraph   = new Graph(maxVertices);		
		Random randomGenerator = new Random();	
		Random rndWeight = new Random();
		int weight = 0;
		int maxWeight = 100;
		boolean validEdge = true;		
		String fileName = "";
		int rndNode = 0;
		int maxEdges = maxVertices*maxVertices;
		for(int i=0;i<maxVertices;i++){			
			//pick any one and get it`s degree
			int node = i;//randomGenerator.nextInt(maxVertices);			
			//if degree is less then required find number of edges to add
			int nodeDegree  = MyGraph.degree(node);
			System.out.println("node:"+node);
			if(nodeDegree < maxDegree){
				int reqEdges = maxDegree-nodeDegree ;
				for(int j=1;j<=reqEdges;j++){					
					//to be added node should not be same, already connected or filled with edges	
					rndNode = randomGenerator.nextInt(maxVertices);
					int count=0;
					while(rndNode==node || MyGraph.degree(rndNode)>=maxDegree || MyGraph.isConnected(node, rndNode) ){
						rndNode = randomGenerator.nextInt(maxVertices);							
						if(count >= maxEdges){
							validEdge = false;
							break;
						}
						count++;
						
					}
					//add edge for this node
					if(validEdge==true)
						weight = rndWeight.nextInt(maxWeight);
						MyGraph.addEdge(node, rndNode,weight);
				}
			}
			
		}
		//System.out.println(MyGraph.toString());
		//saveGraphToFile(MyGraph,"Vertices"+maxVertices+"Degree"+maxDegree+"Graph.txt")  ;
		fileName = "Vertices"+maxVertices+"Degree"+maxDegree+"Graph.txt";
		if(saveGraphToFile(MyGraph,fileName)){
			System.out.println("File:"+fileName+" saved successfully!");
		};
		
		
	}
	public static boolean saveGraphToFile(Graph MyGraph, String fileName){
		
		File file;
		BufferedWriter bfr = null;
		try{
			file = new File("./res/"+fileName);
			bfr = new BufferedWriter(new FileWriter(file));
			int V = MyGraph.getV();
			for (int v = 0; v < V; v++) {
				StringBuilder s = new StringBuilder();
				s.append(v + ":");
				for (int w : MyGraph.adj(v))
					s.append( w + ",");
				s.append("\n");				
				bfr.write(s.toString());
				s=null;
			}
			
			return true;
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());	
			return false;
		}finally{
			try {
				bfr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static boolean saveTextToFile(String text, String fileName){
		try{
			File file = new File("./res/"+fileName);
			BufferedWriter bfr = new BufferedWriter(new FileWriter(file));
			bfr.write(text);
			bfr.close();
			return true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	

}
