

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import graph.Graph;

public class Main {

	public static void main(String[] args) {
		
		//Variáveis para calcular tempo e consumo de memória dos algoritmos.
		long timeInit = 0;
		long timeFinal = 0;
		long memory = 0;
		long time = 0;
		
		Scanner sc = new Scanner(System.in); 
		List<String> result = new ArrayList<String>();
		
		try {
			
			System.out.println("\tInternal Interface Dominating Set \n\nSelecione a opção: \n[1] Calcular Baseline\n[2] Calcular Heurística\n>>");
			String option = sc.nextLine();
			//String option = "2";
			
			System.out.println("Informe o arquivo de entrada: \n>>");
			String nameFile = sc.nextLine();
			//String nameFile = "inputs/output.junit.maior.10.txt";
			
			File fileIn = new File(nameFile); //Arquivo de entrada.
			Graph g = new Graph(fileIn);
			
			if(option.equals("1")){
				System.out.println("Calculando o Baseline (Aguarde)... ");
				timeInit = System.currentTimeMillis();
				result = g.calcBaseline();
				timeFinal = System.currentTimeMillis();
			}
			else if(option.equals("2")){
				System.out.println("Calculando Heurística (Aguarde)... ");
				timeInit = System.currentTimeMillis();
				result = g.calcHeuristic();
				timeFinal = System.currentTimeMillis();
			}
			else {
				System.err.println("Opção Inválida!\n");
			}
			
			System.out.println("Cálculo finalizado, verifique o resultado no arquivo <output.txt>");
			//Insere consumo de memória e tempo no arquivo de saída.
			time = (timeFinal - timeInit);
			memory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
			System.out.println("Tempo de Execução: " + time + "ms.");
			System.out.println("Memória usada: " + memory + " bytes.");
			result.add("Memória usada: " + memory + " bytes.");
			result.add("Tempo de Execução: " + time + "ms.");
			UtilFile.writeFile("output.txt", result);
			
		} catch (Exception e) {
			System.err.println("\n[ERROR]: Falha ao calcular. Verifique o formato do arquivo de entrada utilizado.\n");
			e.printStackTrace();
		}
	}

}
