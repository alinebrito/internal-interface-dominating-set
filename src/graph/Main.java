package graph;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String> test = new ArrayList<String>();
		test.add("0");
		test.add("1");
		test.add("2");
		
		Graph g = new Graph();
		System.out.println(g.calcBaseline(test));

	}

}
