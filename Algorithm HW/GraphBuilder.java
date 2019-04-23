package algorithm;

import algorithm.GraphTest.GraphType;

/**
 
* File Name: GraphBuilder.java 
 * All routines that builds Graph
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphBuilder.java
 */

class GraphBuilder{
	private Graph g ;
	//You can have any number of private variables
	private String[][] e ;

	GraphBuilder(Graph g, String[][] e) {
		this.g = g ;
		this.e = e ;
		buildGraph() ;
	}

	private void buildGraph() {
		//WRITE GRAPH
		GraphTest.GraphType t = g.getType();
		for(String[] a : e) {
			for(int j = 0; j < 2; j++) {
				g.insertOrFind(a[j], false);
			}
		}
		double w = 0;
		for(String[] a : e) {
			int p1 = g.insertOrFind(a[0], true);
			int p2 = g.insertOrFind(a[1], true);
			if(a.length == 3) {
				w = Double.parseDouble(a[2]);
			}
			g.createEdge(p1, p2, w, true);//p1 has a fanout p1
			g.createEdge(p2, p1, w, false);//p2 has a fanin p1
			if((t == GraphTest.GraphType.UNDIRECTED) || (t == GraphTest.GraphType.WEIGHTED_UNDIRECTED)) {
				g.createEdge(p2, p1, w, true);
				g.createEdge(p1, p2, w, false);
			}
		}
		
		
	}

	public static void main(String[] args) {
		System.out.println("GraphBuilder.java starts");
		System.out.println("Use GraphTester.java to test");
		System.out.println("GraphBuilder.java Ends");
	}
}