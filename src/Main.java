

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import graph.Graph;

public class Main {

	public static void main(String[] args) {
		
		File fileIn = new File("inputs/exemplo-1.csv"); //Arquivo de entrada.
		
		
		System.out.println("Internal Interface Dominating Set\nCalculando o Baseline (Aguarde)... ");
		
		long timeInit = System.currentTimeMillis();
		
		Graph g = new Graph(fileIn);
		List<String> result = g.calcBaseline();
		System.out.println("Cardinalidade: " + result.size());
		
		long timeFinal = System.currentTimeMillis();
		
		UtilFile.writeFile("output.txt", result);
		
		System.out.println("Cálculo finalizado, verifique o resultado no arquivo <output.txt>");
		System.out.println("Tempo de Execução: " + (timeFinal - timeInit) + "ms.");

	}

}
