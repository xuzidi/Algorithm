package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * File Name: TruthTable.java Print 'n' input truth table
 * 
 * To compile: RandomInt.java IntUtil.java TruthTable.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */

class TruthTable {
	private int n;
	private boolean display;
	static IntUtil u = new IntUtil();

	/*
	 * ---------WRITE CODE HERE -------------------
	 */
	TruthTable(int n, boolean display) {
		List<Integer> list = new ArrayList<>();
		int[] count = new int[1];
		generate(n, count, list, display);
		System.out.println("For " + n + " inputs, " + "table size is " + count[0]);
		
	}

	public static void generate(int n, int[] count, List<Integer> list, boolean display) {
		if (list.size() == n) {
			if(display) {
				for(Object i: list.toArray()) {
					System.out.print(i);
				}
				System.out.println();
			}
			count[0]++;
		}else {
			for(int i = 0; i < 2; i++) {
				list.add(i);
				generate(n, count, list, display);
				list.remove(list.size()-1);
			}
		}
	}

	/*
	 * ---------- YOU CANNOT CHANGE ANYTHING BELOW--------------------------
	 */
	private static void testBench() {
		for (int i = 1; i < 31; ++i) {
			boolean display = (i > 10) ? false : true;
			System.out.println("------------Truth table of " + i + " inputs function --------------");
			long startTime = System.nanoTime();
			// If display is true, you should print like this:
			// ------------Truth table of 2 inputs function --------------
			// 00
			// 01
			// 10
			// 11
			// For 2 inputs, table size is 4

			// If display is false, you must go through the same procedure
			// buy you should print only the last line like this
			// ------------Truth table of 29 inputs function --------------
			// For 29 inputs, table size is 536870912

			TruthTable t = new TruthTable(i, display);
			long endTime = System.nanoTime();
			double d = u.timeInSec(endTime, startTime);
			System.out.println("Cpu time for n = " + i + " is " + d + " seconds");
		}
	}

	public static void main(String[] args) {
		System.out.println("TruthTable.java Starts");
		testBench();
		System.out.println("YOU CANNOT USE bigInteger class");
		System.out.println("Must use only basic java to solve this problem");
		System.out.println("Attach TruthTable.java and output of this program as a pdf file for full grade");
		System.out.println("TruthTable.java ends");
	}
}