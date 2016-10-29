

import java.util.ArrayList;
import java.util.List;

import graph.Graph;

public class Main {

	public static void main(String[] args) {
		
		//Para testes <PENDENTE>
		List<String> test = new ArrayList<String>();
		test.add("0");
		test.add("1");
		test.add("2");
		
		Graph g = new Graph();
		System.out.println(g.calcBaseline(test));

	}

}
