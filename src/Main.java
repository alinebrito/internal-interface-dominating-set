

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import graph.Graph;

public class Main {

	public static void main(String[] args) {
		
		File fileIn = new File("inputs/exemplo-1.csv"); //Arquivo de entrada.
		
		
		System.out.println("Internal Interface Dominating Set");
		System.out.println("Calculando o Baseline (Aguarde)... ");
		long timeInit = System.currentTimeMillis();
		
		Graph g = new Graph(fileIn);
		System.out.println(g.calcBaseline());
		
		long timeFinal = System.currentTimeMillis();
		
		System.out.println("Cálculo finalizado, verifique o resultado no arquivo <output.txt>");
		System.out.println("Tempo de Execução: " + (timeFinal - timeInit) + "ms.");

	}

}
