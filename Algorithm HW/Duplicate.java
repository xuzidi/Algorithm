package algorithm;

/**
 * File Name: Duplicate.java
 * Find duplicate elements in an array
 * The array can have any positive number >= 0
 * 
 * to compile: RandomInt.java IntUtil.java Duplicate.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */
class Duplicate {
	static IntUtil u = new IntUtil() ;
	Duplicate() {
		testBed() ;
	}
	
	/*
	 * Time: O(n^2)
	 * Spave: O(1)
	 */
	
	private int bigOnSquareTimeTheta1Space(int[] a, boolean show) {
		int[] b = u.copyArray(a);
		int k = 0 ;
		int n = a.length ;
		
		//zero will become 1
		for (int i = 0; i < n; ++i) {
			a[i] = a[i] + 1 ;
		}
		for (int i = 0; i < n; ++i) {
			int x = a[i] ;
			if (x >= 0) {
				boolean first = true ;
				for (int j = i+1; j < n; ++j) {
					int y = a[j] ;
					if (x == y) {
						a[j] = -y ;
						if (first) {
							++k ;
							if (show) {
								System.out.print(x-1 + " ") ;
							}
							first = false ;
						}
					}
				}
			}
		}
		if (show) {
			System.out.println() ;
		}
		
		//put back 1 to 0
		for (int i = 0; i < n; ++i) {
			if (a[i] < 0) {
				a[i] = -(a[i]) - 1;
			}else {
				a[i] = a[i] - 1 ;
			}
		}
		u.myassert(u.arrayEqual(a, b)) ;
		return k ;
	}

	private int test(int[] a, boolean show) {
		int n = a.length;
		if (show) {
			System.out.println("-----------------------------") ;
			u.pLn(n);
			u.pLn(a);
		}
		int k1 = bigOnSquareTimeTheta1Space(a,show);
		if (show) {
			u.pLn(n);
			u.pLn(a);
			System.out.println("-----------------------------") ;
		}
		return k1 ;
	}
	
	private void testRandom() {
		int N = 5000 ; //1000000
		int start = 0 ;
		int end = N + N ; ;
		int [] a = u.generateRandomNumber(N, true, start,end);
		long startTime = System.nanoTime();
		int k = test(a,false) ;
		long endTime = System.nanoTime();
    double d2 = u.timeInSec(endTime,startTime) ;
		System.out.println("Number of duplicates in " + N + " elements = " + k + " CPU time = " + d2 + " seconds");	
	}
	

	private void test1() {
		{
			int [] a = {1,2,3};
			int k = test(a, true);
			u.myassert(k == 0);
		}
		{
			int [] a = {1,1,1};
			int k = test(a, true);
			u.myassert(k == 1);
		}
		{
			int [] a = {1,0,0};
			int k = test(a, true);
			u.myassert(k == 1);
		}
		{
			int [] a = {1,0,1,0};
			int k = test(a, true);
			u.myassert(k == 2);
		}
		{
			int [] a = {0,200,300,0,300,6,200,0,0} ;
			int k = test(a, true);
			u.myassert(k == 3);
		}
	}
	
	private void testBed() {
		test1() ;
		testRandom() ;
	}

	public static void main(String[] args) {
		System.out.println("Duplicate.java STARTS");
		Duplicate m = new Duplicate() ;
		System.out.println("Duplicate.java ENDS");
	}
}

//EOF
