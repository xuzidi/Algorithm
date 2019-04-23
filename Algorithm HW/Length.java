package algorithm;

import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.Random;

/**
 * File Name: Length.java 
 * 
 * To compile you require: IntUtil.java RandomInt.java  Length.java
 *
 * DO NOT POST ON GITHUB.
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */

class Length {
	private static final IntUtil u = new IntUtil();
	/* YOU CANNOT ADD ANYTHING HERE */

  //WRITE CODE ONLY in length. CANNOT CALL ANY FUNCTION EXCEPT length
	private static int length(int [] s, int x) {
		/*
				YOU CANNOT USE ANY static variable in this function
  			YOU can use as many local variables inside the function
  			Cannot use any loop statements like  for, while, do, while, go to
  			Can use if. 
  			ONLY AFTER THE execution of this routine array s MUST have the same contents as you got it.
  		  YOU cannot call any subroutine inside this function except length itself (NOT length_easy)
		 */
		
		int temp = s[x];
		if(temp >= 0) {
			s[x] =  -temp - 1;
			int count = length(s, temp);
			s[x] = temp;
			return count + 1;
		}else {
			return -1;
		}
		
	}

	/*
	 * ----------------------  YOU CANNOT CHANGE ANYTHING BELOW ----------------------------
	 */
	private static int length_easy(int [] s, int x) {
		int l = 0 ;
		int gx = x ;
		while (true) {
			if (s[x] == gx) {
				return l ;
			}
			x = s[x] ;
			++l ;
		}  
	}

	public static void testbed() {
		int s[] = {5,1,0,4,2,3} ;
		int y = length_easy(s,3) ;
		System.out.println("length_easy y = " + y);
		u.myassert(y == 4) ;
		int b[] = {5,1,0,4,2,3} ;
		int x = length(s,3) ;
		System.out.println("length x = " + x);
		u.myassert(x == y) ;
		for (int i = 0; i < s.length; ++i) {
			u.myassert(s[i] == b[i]);
		}
		System.out.println("Assert passed");
	}

	public static void testbed1() {
		int s[] = {5,1,0,4,2,3} ;
		int b[] = {5,1,0,4,2,3} ;
		int l = s.length ;
		for (int j = 0; j < l ; ++j) {
			System.out.println("---------------------j = " + j + " ------------------");
			int y = length_easy(s,j) ;
			System.out.println("length_easy y = " + y);
			int x = length(s,j) ;
			System.out.println("length x = " + x);
			u.myassert(x == y) ;
			for (int i = 0; i < s.length; ++i) {
				u.myassert(s[i] == b[i]);
			}
			System.out.println("---------------------Assert passed--------------------");
		}
	}

	public static void testbed2() {
		int n = 10000 ;	
		System.out.println("Testing on " + n + " numbers");
		int[] a = u.generateNumberInIncreasingOrder(n, 1);
		//shuffle a
		Random r = new Random() ;
		for (int i = 0; i < n; ++i) {
			int p = RandomInt.getRandomInt(r,0,n); //This gives number between 0 to n-1
			int q = RandomInt.getRandomInt(r,0,n);
			u.swap(a,p,q);
		}
		//make a copy of array a as b
		int[] b = u.copyArray(a) ;
		
		for (int j = 0 ; j < n; ++j) {
			int y = length_easy(a,j) ;
			int x = length(a,j) ;
			u.myassert(x == y) ;
			u.arrayEqual(a, b) ;
		}
		System.out.println("All " + n + " cases passed. You are great");
	}

	public static void main(String[] args) {
		System.out.println("Length.java");
		testbed() ;
		testbed1() ;
		testbed2() ;
		System.out.println("Length.java DONE. Attach Length.java and output of the program as a pdf file");
	}
}