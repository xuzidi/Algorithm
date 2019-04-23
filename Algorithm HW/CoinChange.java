package algorithm;


import java.util.Arrays;


class CoinChange extends CoinChangeBase{
  //input to the program
  private int n ; //amount for which change has to be given
  private int[] d ; //array of denominations {1,3,4}
  //Data used for solution
  //YOU CAN ADD whatever you want
  
  CoinChange() {
  	//Nothing can be added here
		testBench();
	}
  
  @Override
  protected void computeChange(int amount, int [] arrayOfCoinsAvailable) {
  	//WRITE CODE
	    n = amount;
		d = arrayOfCoinsAvailable;
		int[] m = new int[n + 1];
		int[] k = new int[n + 1];
		for (int i = 0; i < 2; i++) {
			m[i] = i;
			k[i] = i;
		}
		for (int i = 2; i <= n; i++) {
			int min = Integer.MAX_VALUE;
			int mark = 0;
			for (int j = 0; j < d.length; j++) {
				if (i - d[j] < 0)
					continue;
				if (1 + m[i - d[j]] < min) {
					min = 1 + m[i - d[j]];
					mark = d[j];
				}
			}
			m[i] = min;
			k[i] = mark;
		}
		
		
		System.out.println("Give minimum change for " + n + " cents using coins " + Arrays.toString(d));
		System.out.print("i =     ");
		for (int i = 0; i <= n; i++) {
			System.out.print(i + "\t");
		}
		System.out.println();
		System.out.print("m array ");
		for (int i = 0; i <= n; i++) {
			System.out.print(m[i] + "\t");
		}
		System.out.println();
		System.out.print("k array ");
		for (int i = 0; i <= n; i++) {
			System.out.print(k[i] + "\t");
		}
		System.out.println();
		for (int i = 0; i <= n; ++i) {
			printSolution(i, m, k);
		}
  }
  private void printSolution(int p, int[] m, int[] k) {

		System.out.println("minimum change for " + p + " cents can be achieved using " + m[p] + " coins");
		int remaining = p;
		int cur = 0;
		for (int i = 0; i < m[p]; i++) {
			System.out.println((i + 1)+":" + ": Pick Coin " + k[remaining] + ". Current Val = "
					+ (cur + k[remaining]) + ". Remaining Val = " + (remaining - k[remaining]));
			cur += k[remaining];
			remaining -= k[remaining];

		}

	}
 
  public static void main(String[] args) {
  	//NOTHING CAN BE CHANGED BELOW
		System.out.println("CoinChange problem STARTS");
		CoinChange m = new CoinChange() ;
		System.out.println("CoinChange problem ENDS");
	}
}
