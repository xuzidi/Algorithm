package algorithm;

import java.util.ArrayList;
import java.util.List;

public class FindPrime {
	public static void main(String[] args) {
		List<Integer> ans=findPrimeNumber(200);
		System.out.println(ans);
		System.out.println(addBinary("11","1"));
	}
	private static List<Integer> findPrimeNumber(double n) {
		List<Integer> list = new ArrayList<>();
		for(int i=2;i<n+1;i++) {
			int temp=(int)Math.sqrt(i);
			if(i<=3) list.add(i);
			else {
				for(int j=2;j<=temp;j++) {
				if(i%j==0) break;
				if(j>=temp)
					list.add(i);
			}
				  
		}
			
		}
		return list;
	}
	
	 public static String addBinary(String a, String b) {
	        StringBuilder sb = new StringBuilder();
	        int j = a.length()-1;
	        int k = b.length()-1;
	        int carry=0;
	        while(j>=0||k>=0){
	        int num1 = (j>=0)?a.charAt(j)-'0':0;
	        int num2 = (k>=0)?b.charAt(k)-'0':0;
	            int sum= num1+num2+carry;
	            carry=sum/2;
	            sb.append(sum%2);
	            if(j>=0) j--;
	            if(k>=0) k--;
	            
	        }
	        if(carry==1) sb.append(String.valueOf(1));
	        return sb.reverse().toString();
	        
}
}
