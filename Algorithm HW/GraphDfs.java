package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;



/**
 * File Name: GraphDfs.java All routines that builds Graph
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs
 * GraphDfs.java
 */

class GraphDfs {
	private String t;
	private Graph g;
	String start;
	// Output
	boolean[] cycle;
	int[] work;
	ArrayList<String> ans;
	// You can have any number of private variables
	// Data used to solve the problem

	GraphDfs(String t, Graph g, String start, boolean[] cycle, int[] work, ArrayList<String> ans) {
		this.t = t;
		this.g = g;
		this.start = start;
		this.cycle = cycle;
		this.work = work;
		this.ans = ans;

		dfs(start);
		printDFS();
		assertDFS();
	}

	private void printDFS() {
		// WRITE CODE
		System.out.println();
		System.out.println(t);
		Collections.reverse(ans);
		int numV = ans.size();
		System.out.println("Num Vertices = " + numV);
		System.out.println("Num Edges = " + (work[0] - numV));
		System.out.println("Work Done = " + work[0]);
		System.out.println("Has Cycle =  " + cycle[0]);
		System.out.println("DFS Topological Order =   " + ans);
		if (cycle[0] == true) {
			System.out.println("This order has no meaning");
		}
		
	}

	private void assertDFS() {
		// WRITE CODE
		if(cycle[0] == true || (g.getType() == GraphTest.GraphType.UNDIRECTED))
			return;
		HashSet<Integer> set = new HashSet<>();
		for(int i = 0; i < ans.size(); i++) {
			String name = ans.get(i);
			int index = g.graphHasNode(name);
			int numIn = g.numFanin(index);
			for(int j = 0; j < numIn; j++) {
				int temp = g.getNodeFanin(index, j);
				if(!set.contains(temp)) {
					System.out.println("DFS assert failed");
					return;
				}
			}
			set.add(index);
		}
		System.out.println("DFS assert passed");
		
	}
	
	/*0 represents green 
	1 represents blue
	2 represents red*/
	private void dfs(String start) {
		// WRITE CODE
		GraphTest.GraphType t = g.getType();
		int index = g.graphHasNode(start);
		int color = g.getTemp(index);
		if (color == 0) {
			work[0]++;
			g.setTemp(index, 1);
			int size = g.numFanout(index);
			for (int i = 0; i < size; i++) {
				work[0]++;
				int temp = g.getNodeFanout(index, i);
				String name = g.getNodeRealName(temp);
				dfs(name);
			}
			ans.add(start);
			g.setTemp(index, 2);
		} else if (color == 1) {
			if(t == GraphTest.GraphType.DIRECTED) {
				cycle[0] = true;
			}
			return;
		} else if (color == 2) {
			if(t == GraphTest.GraphType.UNDIRECTED) {
				cycle[0] = true;
			}
			return;
		}

	}

	public static void main(String[] args) {
		System.out.println("GraphDfs.java starts");
		System.out.println("Use GraphTester.java to test");
		System.out.println("GraphDfs.java Ends");
	}
}