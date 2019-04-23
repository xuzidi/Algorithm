package algorithm;

import java.util.ArrayList;
import java.util.List;



/**
 * File Name: knapsack01.java 
 * Dynamic programming
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */
class knapsack01 extends knapsack01Base{
  //given
  private int s ; //Bag size Given
  private int[] wa ; //weight array: Given
  private int[] ca; //cost array: Given
  private int[] maxans ; //Final value of items stolen. You need to compute and fill this.
  
  //You can use as many private data structure you want
  
  knapsack01() {
  	//Nothing can be added here
		testBench();
	}

  @Override
  protected void knapsack(int bagSize, int[] weightArray, int[] costArray, int[] maxans){
    s= bagSize;
    int n = weightArray.length ;
    u.myassert(n == costArray.length);
    wa = weightArray;
    ca = costArray ;
    this.maxans = maxans;
    //Initialize all your private data structures
    //call dynamic programming algorithm
    //1. You need to print vmatrix
    /*
	    ------------ V matrix ----------------
	    0   0   0   0   0   0
	    0   0   0   5   5   5
	    0   0   3   5   5   8
	    0   4   4   7   9   9
	  */
    //2. you need to print kmatrix
    /*
	    ------------ k matrix ----------------
	    0   0   0   0   0   0
	    0   0   0   1   1   1
	    0   0   1   0   0   1
	    0   1   1   1   1   1
	  */
    //3. you need to print what are all the items you stole and total value
    /*
    		i =    1   2   3
    		w =    3   2   1
    		v =    5   3   4
    		Max Value of 9 can obtained from items {3,1} that has values {4+5=9}
    */
    //4. Populate maxans[0] = 9 ; //In the above example
  
    //WRITE AS MANY PRIVATE FUNCTIONS YOU WANT
    //DO NOT CRAM ALL CODE HERE
    
    int[][] vmatrix = new int[n+1][s+1];
    int[][] kmatrix = new int[n+1][s+1];
    for(int i = 1; i < n+1; i++) {
    	int curw = wa[i - 1];   //current item's weight
    	int curv = ca[i - 1];   // current item's value
    	for(int w = 1; w< s+1; w++) {
    		int max = Integer.MIN_VALUE;
    		if(w < curw)
    			continue;
    		max = Math.max(vmatrix[i - 1][w], vmatrix[i-1][w-curw] + curv);
    		vmatrix[i][w] = max;
    		if(max == vmatrix[i - 1][w]) {
    			kmatrix[i][w] = 0;
    		}else {
    			kmatrix[i][w] = 1;
    		}
    	}
    }
    maxans[0] = vmatrix[n][s];
   
    print(vmatrix,kmatrix,wa,ca,maxans);
  }
  
  private void print(int[][] vmatrix, int[][] kmatrix, int[] wa, int[] ca,int maxans[]) {
	  int itemNum = vmatrix.length;
	  int size = vmatrix[0].length;
	  System.out.println("------------- V matrix ----------------");
	  for(int i = 0; i < itemNum; i++) {
		  for(int j = 0; j < size; j++) {
			  System.out.print(vmatrix[i][j]+ "\t");
		  }
		  System.out.println();
	  }
	  System.out.println("------------- K matrix ----------------");
	  for(int i = 0; i < itemNum; i++) {
		  for(int j = 0; j < size; j++) {
			  System.out.print(kmatrix[i][j]+ "\t");
		  }
		  System.out.println();
	  }
	  System.out.println();
	  System.out.print("i =" + "\t");
	  for(int i = 1; i < itemNum ; i ++) {
		  System.out.print(i+ "\t");
	  }
	  System.out.println();
	  System.out.print("w =" + "\t");
	  for(int i = 1; i < itemNum ; i ++) {
		  System.out.print(wa[i - 1]+ "\t");
	  }
	  System.out.println();
	  System.out.print("v =" + "\t");
	  for(int i = 1; i < itemNum ; i ++) {
		  System.out.print(ca[i - 1]+ "\t");
	  }
	  System.out.println();
	  
	  //find stolen items in kmatrix and print them
	  List<Integer> itemsans = new ArrayList<>();
	  int remainProfit = maxans[0];
	  int remainSize = size - 1;
	  int item = itemNum - 1;
	  int column = size - 1;
	  while(remainProfit != 0){
		  if(kmatrix[item][column] == 0) {
			  item -= 1;
		  }else {
			  itemsans.add(item);
			  int curvalue = ca[item - 1];
			  int cursize = wa[item - 1];
			  remainProfit -= curvalue;
			  remainSize -= cursize;
			  item -= 1;
			  column = remainSize;
		  }
	  }
	  System.out.print("Max Value of "+ maxans[0]+ " can obtained from items" + itemsans + " that has values (");
	  for(int i = 0; i < itemsans.size(); i++) {
		 if(i !=  itemsans.size() -1)
			 System.out.print(ca[itemsans.get(i) - 1] + " + ");
		 else
			 System.out.print(ca[itemsans.get(i) - 1]);
	  }
	  System.out.print( " = " + maxans[0] + ")");
	  
	  System.out.println();
  }
  
 
 

  public static void main(String[] args) {
    System.out.println("knapsack01.java starts");
    knapsack01 m = new knapsack01() ;
    System.out.println("You are amazing. Bring me a bar of Chocolate");
    System.out.println("knapsack01.java ends");
  }
}