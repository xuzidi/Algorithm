package algorithm;

import java.util.Stack;

/**
 * File Name: BigNumber.java Infinite capacity Unsigned Number
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */
/*
 * To compile you require: IntUtil.java RandomInt.java CharArray.java
 * Cstring.java BigNumber.java
 */

class BigNumber {
	private Cstring d; // data
	static IntUtil u = new IntUtil();

	// YOU CANNOT add any data members
	// YOU CAN add any public or private function so that all the tests will pass

	public Cstring getD() {
		return d;
	}

	public void setD(Cstring d) {
		this.d = d;
	}

	public BigNumber(int i) {
		String string = String.valueOf(i);
		d = new Cstring(string);
	}

	public BigNumber(char c) {
		d = new Cstring(c);
	}

	public BigNumber(String s) {
		d = new Cstring(s);
	}

	public void pLn(String s) {
		d.pLn(s);
	}

	public BigNumber clone() {
		String temp = this.getD().beString();
		return new BigNumber(temp);
	}

	public BigNumber add(BigNumber bigNumber) {
		String s1 = this.getD().beString();
		String s2 = bigNumber.getD().beString();
		String reString = "";
		Stack<Character> stack1 = new Stack<>();
		Stack<Character> stack2 = new Stack<>();
		Stack<Integer> stack3 = new Stack<>();
		for (char c : s1.toCharArray()) {
			stack1.push(c);
		}
		for (char c : s2.toCharArray()) {
			stack2.push(c);
		}
		int carry = 0;
		while (!stack1.isEmpty() || !stack2.isEmpty()) {
			int temp1 = stack1.isEmpty() ? 0 : (stack1.pop() - '0');
			int temp2 = stack2.isEmpty() ? 0 : (stack2.pop() - '0');
			int sum = temp1 + temp2 + carry;
			carry = sum / 10;
			int res = sum % 10;
			stack3.push(res);
		}
		if (carry == 1) {
			stack3.push(1);
		}
		while (!stack3.isEmpty()) {
			reString += stack3.pop();
		}
		return new BigNumber(reString);
	}

	public boolean isEqual(String s) {
		String string = this.getD().beString();
		if (s.length() != string.length())
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != string.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public boolean isEqual(BigNumber bigNumber) {
		String str1 = bigNumber.getD().beString();
		return this.isEqual(str1);
	}

	public boolean isEqual(int i) {
		String string = String.valueOf(i);
		return this.isEqual(string);
	}

	public BigNumber mult(BigNumber bigNumber) {
		String str1 = this.getD().beString();
		String str2 = bigNumber.getD().beString();
		int n = str1.length();
		int m = str2.length();
		int[] res = new int[m + n];
		for (int i = m - 1; i >= 0; i--) {
			int num2 = str2.charAt(i) - '0';
			for (int j = n - 1; j >= 0; j--) {
				int num1 = str1.charAt(j) - '0';
				res[i + j + 1] += num1 * num2;
				res[i + j] += res[i + j + 1] / 10;
				res[i + j + 1] %= 10;
			}
		}
		String result = "";
		boolean zero = false;
		for (int num : res) {

			if (!zero) {
				zero = (num != 0);
				if (!zero)
					continue;
			}
			result += num;
		}
		return result.length() == 0 ? new BigNumber("0") : new BigNumber(result);

	}

	public int size() {
		String string = this.getD().beString();
		return string.length();
	}

	/*
	 * num1 is greater than num2
	 */
	private BigNumber subhelp(BigNumber bigNumber) {
		String str1 = this.getD().beString();
		String str2 = bigNumber.getD().beString();
		String reString = "";
		Stack<Character> stack1 = new Stack<>();
		Stack<Character> stack2 = new Stack<>();
		Stack<Integer> stack3 = new Stack<>();
		for (char c : str1.toCharArray()) {
			stack1.push(c);
		}
		for (char c : str2.toCharArray()) {
			stack2.push(c);
		}
		int borrow = 0;
		int res = 0;
		while (!stack1.isEmpty() || !stack2.isEmpty()) {
			int temp1 = stack1.isEmpty() ? 0 : (stack1.pop() - '0');
			int temp2 = stack2.isEmpty() ? 0 : (stack2.pop() - '0');
			temp1 += borrow;
			if (temp1 < temp2) {
				res = temp1 + 10 - temp2;
				borrow = -1;
				stack3.push(res);
			} else {
				res = temp1 - temp2;
				borrow = 0;
				stack3.push(res);
			}
		}
		boolean zero = false;
		while (!stack3.isEmpty()) {
			int num = stack3.pop();
			if (!zero) {
				zero = (num != 0);
				if (!zero)
					continue;
			}
			reString += num;
		}
		return reString.length() == 0 ? new BigNumber("0") : new BigNumber(reString);

	}

	private boolean isGreater(BigNumber bigNumber) {
		String str1 = this.getD().beString();
		String str2 = bigNumber.getD().beString();
		if (str1.length() > str2.length() || this.isEqual(bigNumber))
			return true;
		if (str1.length() < str2.length())
			return false;
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) > str2.charAt(i))
				return true;
			else if (str1.charAt(i) < str2.charAt(i))
				return false;
			else {
				continue;
			}
		}
		return true;
	}

	public BigNumber sub(BigNumber bigNumber) {
		if (this.isGreater(bigNumber))
			return subhelp(bigNumber);
		else {
			BigNumber bigNumber2 = bigNumber.subhelp(this);
			String temp = bigNumber2.getD().beString();
			StringBuffer sBuffer = new StringBuffer(temp);
			sBuffer.insert(0, "-");
			return new BigNumber(sBuffer.toString());
		}
	}

	public static BigNumber factorial(int i) {
		BigNumber[] res = new BigNumber[i];
		res[0] = new BigNumber("1");
		if (i == 1)
			return res[0];
		for (int j = 1; j < i; j++) {
			BigNumber temp = new BigNumber(j + 1);
			res[j] = temp.mult(res[j - 1]);
		}
		return res[i - 1];
	}

	// WRITE ALL THE ROUTINES required to pass all the tests in BigNumberTester.java

	public static void main(String[] args) {
		System.out.println("BigNumber.java");
		System.out.println("Done");
	}
}
