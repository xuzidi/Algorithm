package algorithm;

/**
 * File Name: Cstring.java Implements C String
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */
/*
 * To compile you require: IntUtil.java RandomInt.java CharArray.java
 * Cstring.java WRITE CODE IN THIS FILE
 */

class Cstring {
	// YOU CANNOT ADD ANYTHING HERE
	private CharArray d; // Infinite array of char
	static IntUtil u = new IntUtil();

	public Cstring(char c) {
		d = new CharArray();
		d.set(0, c);
		d.set(1, '\0');
	}

	public Cstring(String s) {
		d = new CharArray();
		for (int i = 0; i < s.length(); i++) {
			d.set(i, s.charAt(i));
		}
		d.set(s.length(), '\0');
	}

	public void pLn(String s) {
		System.out.print(s);
		int i = 0;
		while (d.get(i) != '\0') {
			System.out.print(d.get(i++));
		}
		System.out.println();
	}

	public String beString() {
		int i = 0;
		while (d.get(i) != '\0') {
			i++;
		}
		String string = "";
		char[] ch = new char[i];
		for (int j = 0; j < i; j++) {
			ch[j] = d.get(j);
			string += ch[j];
		}
		return string;

	}

	public Cstring clone() {
		String string = this.beString();
		Cstring cstring = new Cstring(string);
		return cstring;
	}

	public void reverse() {
		int i = 0;
		while (d.get(i) != '\0') {
			i++;
		}
		for (int j = 0; j < i / 2; j++) {
			d.swap(j, i - j - 1);
		}
	}

	public Cstring add(Cstring cstring) {
		String string = cstring.beString();
		return this.add(string);
	}

	public Cstring add(String s) {
		String string = "";
		string = string + this.beString() + s;
		Cstring cstring = new Cstring(string);
		return cstring;
	}

	public void append(String s) {
		int i = 0;
		while (d.get(i) != '\0') {
			i++;
		}
		for (int j = 0; j < s.length(); j++) {
			d.set(i++, s.charAt(j));
		}
		d.set(i, '\0');
	}

	public void append(Cstring cstring) {
		String string = cstring.beString();
		this.append(string);
	}

	public boolean isEqual(Cstring cstring) {
		String s1 = this.beString();
		String s2 = cstring.beString();
		if (s1.length() != s2.length())
			return false;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i))
				return false;
		}
		return true;
	}

	// WRITE ALL THE ROUTINES BELOW, so that all the tests pass
	private static void testBasic() {
		Cstring a = new Cstring('b');
		a.pLn("a = ");
		Cstring b = new Cstring('7');
		b.pLn("b = ");
		Cstring c = new Cstring("123456789012345678901234567890123456789012345678901234567890");
		c.pLn("c = ");
		Cstring d = c.clone();
		d.pLn("d = ");
		Cstring e = new Cstring("A quick brown fox junped over a lazy dog");
		e.pLn("e = ");
		Cstring f = new Cstring("Gateman sees name garageman sees nametag");
		f.pLn("f =  ");
		f.reverse();
		f.pLn("f' = ");
	}

	private static void testAdd() {
		Cstring a = new Cstring("UCSC");
		Cstring b = new Cstring("Extension");
		Cstring c = a.add(b);
		a.pLn("a = ");
		b.pLn("b = ");
		c.pLn("c = ");
		Cstring d = c.add("USA");
		d.pLn("d = ");
		a.append(b);
		a.pLn("a+b = ");
		a.append("World");
		a.pLn("a+b+World = ");
	}

	private static void testEqual() {
		Cstring a = new Cstring("123456789012345678901234567890123456789012345678901234567890");
		a.pLn("a = ");
		Cstring b = new Cstring("123456789012345678901234567890123456789012345678901234567890");
		b.pLn("b = ");
		u.myassert(a.isEqual(b));
		Cstring c = new Cstring("12345678901234567890123456789012345678901234567890123456789");
		c.pLn("c = ");
		u.myassert(a.isEqual(c) == false);
	}

	private static void testBench() {
		System.out.println("-----------Basic----------------");
		testBasic();
		System.out.println("-----------Addition----------------");
		testAdd();
		System.out.println("-----------Equal----------------");
		testEqual();
	}

	public static void main(String[] args) {
		System.out.println("Cstring.java");
		testBench();
		System.out.println("Done");
	}

}