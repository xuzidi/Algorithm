package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Hauffman {
	private String s;
	private boolean show;
	private String dotfilename;
	private Tree tree;
	private Map<Character, String> encodeMap;

	class Tree {
		private Node root;

		public Node getRoot() {
			return root;
		}

		public void setRoot(Node root) {
			this.root = root;
		}
	}

	class Node implements Comparable<Node> {
		private String chars = "";
		private int weight = 0;
		private Node parent;
		private Node left;
		private Node right;

		public boolean isLeaf() {
			return chars.length() == 1;
		}

		public boolean isRoot() {
			return parent == null;
		}

		public boolean isLeftChild() {
			return parent != null && this == parent.left;
		}
		
		public boolean isRightChild() {
			return parent != null && this == parent.right;
		}

		public String getChars() {
			return chars;
		}

		public void setChars(String chars) {
			this.chars = chars;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		@Override
		public int compareTo(Node o) {

			return this.weight - o.weight;
		}
	}

	private Map<Character, Integer> getWeightMap() {
		Map<Character, Integer> map = new HashMap<>();
		char[] array = s.toCharArray();
		for (char c : array) {
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}
		return map;
	}

	private Tree buildTree(Map<Character, Integer> map) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		
		for (char c : map.keySet()) {
			Node node = new Node();
			node.setChars(c + "");
			node.setWeight(map.get(c));
			queue.offer(node);
		}
		if(queue.size() == 1) {
			tree.setRoot(queue.poll());
			return tree;
		}
		
       int size = queue.size();
		for(int i = 1; i < size; i++) {
			Node node1 = queue.poll(); 
			Node node2 = queue.poll();
			

			Node parentNode = new Node();
			parentNode.setWeight(node1.getWeight() + node2.getWeight());
			parentNode.setChars(node1.getChars() + node2.getChars());
			parentNode.setLeft(node1);
			parentNode.setRight(node2); 

			node1.setParent(parentNode);
			node2.setParent(parentNode);

			queue.offer(parentNode);
		}
		
		tree.setRoot(queue.poll());

		return tree;

	}

	private void getEncodeMap(Node root, Map<Character, String> encodeMap, String s) {
		
		if (root.isLeftChild()) {
			s += "0";
		}
		if (root.isRightChild()) {
			s += "1";
		}

		if (root.isLeaf()) {
			if(s.equals("")) {
				encodeMap.put(root.getChars().charAt(0), "1");
				return;
			}
			encodeMap.put(root.getChars().charAt(0), s);
			s = s.substring(0,s.length() - 1);
			return;
		}

		if (root.getLeft() != null) {
			getEncodeMap(root.getLeft(), encodeMap, s);
		}
		if (root.getRight() != null) {
			getEncodeMap(root.getRight(), encodeMap, s);
		}

	}

	public String encode() {
		Map<Character, Integer> map = this.getWeightMap();
		
		this.tree = this.buildTree(map);
		String string = "";
		getEncodeMap(tree.getRoot(), this.encodeMap, string);
		System.out.println(encodeMap);
		String res = "";
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			String temp = encodeMap.get(c);
			res += temp;
		}
		s = res;
		return res;
	}
	
	public String decode() {
		String res = "";
		Node temp = this.tree.getRoot();
		if(temp.isLeaf()) {
			return temp.getChars();
		}
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == '0') {
				temp = temp.left;
				if(temp.isLeaf()) {
					res += temp.getChars();
					temp = this.tree.getRoot();
				}
			}
			if(c == '1') {
				temp = temp.right;
				if(temp.isLeaf()) {
					res += temp.getChars();
					temp = this.tree.getRoot();
				}
			}
		}
		s = res;
		return res;
		
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

	public Map<Character, String> getEncodeMap() {
		return encodeMap;
	}

	public void setEncodeMap(Map<Character, String> encodeMap) {
		this.encodeMap = encodeMap;
	}

	public Hauffman(String s, boolean show, String dotfilename) {
		super();
		this.s = s;
		this.show = show;
		this.dotfilename = dotfilename;
		tree = new Tree();
		encodeMap = new HashMap<>();
	}

}
