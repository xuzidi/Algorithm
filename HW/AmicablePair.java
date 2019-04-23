package algorithm;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * File Name: AmicablePair.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 * 
 *       To compile: RandomInt.java IntUtil.java AmicablePair.java
 */
public class AmicablePair {
	private int max;
	// YOU CAN HAVE ANY NUMBER OF PRIVATE VARIABLES HERE
	static IntUtil u = new IntUtil();

	/*
	 * Constructor
	 */
	AmicablePair(int n) {
		max = n;
		/* You can initialize any variables here */
		/* you can call any private functions here */
		findPairs(n);
		
		
		
	}

	/*
	 * WRITE YOUR CODE BELOW MUST SOLVE FROM FUNDAMENTALS CANNOT USE SOME WEIRD
	 * FORMULAS FROM INTERNET
	 */
	private void findPairs(int n) {
		int[] sumOfFactors = new int[n];
		int sum = 0;
		int count = 0;
		//use dynamic programming
		for(int i = 1; i < n; i++) {
			for(int j = 2; i*j < n; j++) {
				sumOfFactors[i*j] += i;
			}
			if(sumOfFactors[i] < i && sumOfFactors[sumOfFactors[i]]==i) {
				System.out.println(count++ + ": " + sumOfFactors[i] + " and " + i);
			}
		}
	}
	/*
	 * NOTHING CAN BE CHANGED BELOW
	 */
	private static void test() {
		int n = 100000000;
		AmicablePair a = new AmicablePair(n);
	}

	public static void main(String[] args) {
		System.out.println("AmicablePair.java starts");
		test();
		System.out.println("If you can do in less than 20 secs, you will get FULL points");
		System.out.println("Attach AmicablePair.java, output of your program as a pdf file");
		System.out.println("Attach a word file, explaining why your method is fast");
		System.out.println("AmicablePair.java ends");
	}
}
