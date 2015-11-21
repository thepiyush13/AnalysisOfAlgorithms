package main;
import java.util.*;
import java.io.*;

public class GraphGenerator {
	
	public static void main(String[] args) {	
		int maxVertices = 5000;
		int maxDegree = 1000;
		int minDegree = 6;
		genDenseGraph(maxDegree,maxVertices);
		genSparseGraph(minDegree,maxVertices);
		
			
	}
	private static void genDenseGraph(int maxDegree, int maxVertices){
		//Get random elements		
		Graph MyGraph   = new Graph(maxVertices);		
		Random randomGenerator = new Random();	
		Random rndWeight = new Random();
		int weight = 0;
		int maxWeight = 100;
		boolean getOut = false;	
		int maxEdges = maxVertices*100;
		boolean validEdge = true;	
		String fileName = "";
				int rndInt = 0;
		StringBuilder r = new StringBuilder();
		
		for(int i=0;i<maxVertices;i++){			
			//pick any one and get it`s degree
			//System.gc();
			r.append("vertex");
			r.append(i);
			Vertex node = new Vertex(r.toString(),i);//randomGenerator.nextInt(maxVertices);
			r.setLength(0);
			//if degree is less then required find number of edges to add
			int nodeDegree  = MyGraph.degree(node);
			System.out.println("node:"+node.name);
			if(nodeDegree < maxDegree){
				int reqEdges = maxDegree-nodeDegree ;
				for(int j=1;j<=reqEdges;j++){					
					//to be added node should not be same, already connected or filled with edges	
					/*rndInt =  randomGenerator.nextInt(maxVertices);					
					r.append("vertex");
					r.append(rndInt);
					Vertex rndNode = new Vertex(r.toString(),rndInt);
					r.setLength(0);*/
					int count=0;
					validEdge = true;
					Vertex validNode = null;
					while(true){
						rndInt =  randomGenerator.nextInt(maxVertices);
						r = new StringBuilder();
						r.append("vertex");
						r.append(rndInt);
						Vertex rndNode = new Vertex(r.toString(),rndInt);	
						r.setLength(0);
						if(!rndNode.equals(node) && MyGraph.degree(rndNode)<maxDegree && !MyGraph.isConnected(node, rndNode)  ){
							validNode = rndNode;							
							break;
						}
						rndNode = null;
						if(count >= maxEdges){
							validEdge = false;
							break;
						}
						count++;
						
					}
					//add edge for this node
					if(validEdge==true ){
						weight = rndWeight.nextInt(maxWeight);
						MyGraph.addEdge(node, validNode,weight);
						validNode = null;
					}
						
						
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
		int maxEdges = maxVertices*maxVertices;
		String fileName = "";
		Vertex rndNode ;
		int rndInt = 0;
		for(int i=0;i<maxVertices;i++){			
			//pick any one and get it`s degree
			Vertex node = new Vertex("vertex"+i,i);//randomGenerator.nextInt(maxVertices);			
			//if degree is less then required find number of edges to add
			int nodeDegree  = MyGraph.degree(node);
			System.out.println("node:"+node.toString());
			if(nodeDegree < maxDegree){
				int reqEdges = maxDegree-nodeDegree ;
				for(int j=1;j<=reqEdges;j++){					
					//to be added node should not be same, already connected or filled with edges	
					rndInt =  randomGenerator.nextInt(maxVertices);
					rndNode = new Vertex("vertex"+rndInt,rndInt);
					int count=0;
					validEdge = true;
					while(rndNode.equals(node) || MyGraph.degree(rndNode)>=maxDegree || MyGraph.isConnected(node, rndNode) ){
						rndInt =  randomGenerator.nextInt(maxVertices);
						rndNode = new Vertex("vertex"+rndInt,rndInt);							
						if(count >= maxEdges){
							validEdge = false;
							break;
						}
						count++;
						
					}
					//add edge for this node
					if(validEdge==true){
						weight = rndWeight.nextInt(maxWeight);
						MyGraph.addEdge(node, rndNode,weight);
					}
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
			Map<String,List<Vertex>> x = MyGraph.getAdj();		
			for(String v :x.keySet()){
				StringBuilder s = new StringBuilder();	
				s.append(v.toString() + " ");
				for (Vertex w : MyGraph.adj(v))
					s.append( w.toString() + " ");
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
