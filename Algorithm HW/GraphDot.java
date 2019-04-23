package algorithm;

import java.io.FileWriter;
import java.io.IOException;


/**
 * File Name: GraphDot.java 
 * Writes graph as a dot file
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphDot.java
 */

class GraphDot{
	private Graph g ;
	private String fname;
	//You can have any number of private variables
	private String[][] e ;

	GraphDot(Graph g, String s)  {
		this.g = g ;
		this.fname = s ;
		writeDot() ;
	}

	private void writeDot()  {
		//WRITE CODE
	/*	FileWriter fw;
		int numV = g.getnumV();
		try {
			fw = new FileWriter(fname,true);
			fw.write("graph " + fname + " {\r\n");
			for(int i = 0; i < numV; i++) {
				String curnodeName = g.getNodeRealName(i);
				int numFanout = g.numFanout(i);
				for(int j = 0; j < numFanout; j++) {
					int edge = g.getNodeFanout(i, j);
					String edgeName = g.getNodeRealName(edge);
					fw.write(curnodeName + " -- " + edgeName);
					fw.write("\r\n");
				}
			}
			fw.write("}\r\n");
		    fw.flush();
		    fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	public static void main(String[] args) {
		System.out.println("GraphDot.java starts");
		System.out.println("Use GraphTester.java to test");
		System.out.println("GraphDot.java Ends");
	}
}