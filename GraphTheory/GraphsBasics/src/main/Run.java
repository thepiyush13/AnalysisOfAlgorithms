package main;
import java.util.*;
import java.io.*;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;


public class Run {
	
	public static void main(String[] args) {	
		int maxVertices = 5000;
		int maxDegree = 1000;
		//genDenseGraph(maxDegree,maxVertices);
		genSparseGraphOld(100,5000);
		/* */
			
	}
	private static void genDenseGraph(int maxDegree, int maxVertices){
		//Get random elements		
		Graph MyGraph   = new Graph(maxVertices);		
		Random randomGenerator = new Random();	
		boolean getOut = false;		
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
					while(rndNode==node || MyGraph.degree(rndNode)>=maxDegree || MyGraph.isConnected(node, rndNode) ){
						rndNode = randomGenerator.nextInt(maxVertices);						
					}
					//add edge for this node
					//if(getOut==false)
						//MyGraph.addEdge(node, rndNode);
				}
			}
			
		}
		//System.out.println(MyGraph.toString());
		//saveGraphToFile(MyGraph,"Vertices"+maxVertices+"Degree"+maxDegree+"Graph.txt")  ;
		fileName = "Vertices"+maxVertices+"Degree"+maxDegree+"Graph.txt";
		File file;
		BufferedWriter bfr = null;
		try{
			file = new File("./res/"+fileName);
			bfr = new BufferedWriter(new FileWriter(file));
			int V = MyGraph.getV();
			for (int v = 0; v < V; v++) {
				StringBuilder s = new StringBuilder();
				s.append(v + ": ");
				for (int w : MyGraph.adj(v))
					s.append( w + " ");
				s.append("\n");				
				bfr.write(s.toString());
				s=null;
			}					
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());						
		}finally{
			try {
				bfr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	private static void genSparseGraph(int maxDegree, int maxVertices){
		//Get random elements		
		Graph MyGraph   = new Graph(maxVertices);		
		Random randomGenerator = new Random();	
		boolean getOut = false;		
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
					while(rndNode==node || MyGraph.degree(rndNode)>=maxDegree || MyGraph.isConnected(node, rndNode) ){
						rndNode = randomGenerator.nextInt(maxVertices);						
					}
					//add edge for this node
					//if(getOut==false)
					//	MyGraph.addEdge(node, rndNode);
				}
			}
			
		}
		//System.out.println(MyGraph.toString());
		//saveGraphToFile(MyGraph,"Vertices"+maxVertices+"Degree"+maxDegree+"Graph.txt")  ;
		fileName = "Vertices"+maxVertices+"Degree"+maxDegree+"Graph.txt";
		File file;
		BufferedWriter bfr = null;
		try{
			file = new File("./res/"+fileName);
			bfr = new BufferedWriter(new FileWriter(file));
			int V = MyGraph.getV();
			for (int v = 0; v < V; v++) {
				StringBuilder s = new StringBuilder();
				s.append(v + ": ");
				for (int w : MyGraph.adj(v))
					s.append( w + " ");
				s.append("\n");				
				bfr.write(s.toString());
				s=null;
			}					
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());						
		}finally{
			try {
				bfr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	private static void genSparseGraphOld(int maxDegree, int maxVertices){
		//Get random elements		
		Graph MyGraph   = new Graph(maxVertices);		
		Random randomGenerator = new Random();	
		boolean getOut = false;		
		List<Integer> pool = new ArrayList<Integer>();
		SingleGraph graph = new SingleGraph("Tutorial 1");
		int rndNode = 0;
		for(int i=0;i<maxVertices;i++){
			pool.add((Integer)i);
			graph.addNode(i+"n");
		}
		for(int i=0;i<maxVertices;i++){			
			//pick any one and get it`s degree
			int node = i;//randomGenerator.nextInt(maxVertices);			
			//if degree is less then required find number of edges to add
			int nodeDegree  = MyGraph.degree(node);
			
			String extra = rndNode+"";//(pool.size()<10) ? rndNode+"" : "NA";
			
			System.out.println("node:"+node+"pool.size():"+pool.size()+"Extra:"+extra);
			if(nodeDegree < maxDegree){
				int reqEdges = maxDegree-nodeDegree ;
				for(int j=1;j<=reqEdges;j++){					
					//to be added node should not be same, already connected or filled with edges					
					while(rndNode==node  || MyGraph.isConnected(node, rndNode) ){						
						rndNode = pool.get(randomGenerator.nextInt(pool.size()));						
					}
					//add edge for this node
					//if(getOut==false)
						//MyGraph.addEdge(node, rndNode);
						//graph.addEdge(node+"n"+rndNode+"n", node+"n", rndNode+"n");
				}
				//redefine pool size
				
			}
			pool = removeFromPool(pool, node);
			
		}
		//System.out.println(MyGraph.toString());
		//saveGraphToFile(MyGraph,"Vertices"+maxVertices+"Degree"+maxDegree+"Graph.txt")  ;
		//System.out.println("Completed!"+"pool:"+pool.size());
		 graph.display();
		 
		
	}
	private static boolean saveGraphToFile(Graph G, String fileName) {
		String result = G.toString();
		return saveTextToFile(result, fileName);
		
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
	private static void createBasicGraph(){
		try{
			File file = new File("./res/tinyG.txt");					
			Scanner scan = new Scanner(file);		
			int V = Integer.parseInt(scan.nextLine());
			int E = Integer.parseInt(scan.nextLine());
			Graph MyGraph   = new Graph(V);				
			for(int i=0;i<E;i++){
				int v = scan.nextInt();
				int w = scan.nextInt();
				//MyGraph.addEdge(v, w);
			}
			System.out.println(MyGraph.toString());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	private static List<Integer> removeFromPool(List<Integer> pool , int remove){
		ListIterator lt = pool.listIterator();
		while(lt.hasNext()){
			if(lt.next().equals(remove)){
				lt.remove();
			}
		}
		return pool;
	}

}
