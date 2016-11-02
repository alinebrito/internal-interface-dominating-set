

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import graph.Graph;

public class Main {

	public static void main(String[] args) {
		
		try {
			System.out.println("\tInternal Interface Dominating Set \n\nSelecione a opção: \n[1] Calcular Baseline\n>>");
			Scanner sc = new Scanner(System.in); 
			String option = sc.nextLine();
			long timeInit = 0;
			long timeFinal = 0;
			long totalMemory = 0;
			long freeMemory = 0;
			long memory = 0;
			long time = 0;
			List<String> result = new ArrayList<String>();
			if(option.equals("1")){
				System.out.println("Informe o arquivo de entrada: \n>>");
				String nameFile = sc.nextLine();
				File fileIn = new File(nameFile); //Arquivo de entrada.
				System.out.println("Calculando o Baseline (Aguarde)... ");
				Graph g = new Graph(fileIn);
				timeInit = System.currentTimeMillis();
				result = g.calcBaseline();
				timeFinal = System.currentTimeMillis();
				System.out.println("Cálculo finalizado, verifique o resultado no arquivo <output-baseline.txt>");
			}
			else {
				System.err.println("Opção Inválida!\n");
			}
			
			time = (timeFinal - timeInit);
			totalMemory = Runtime.getRuntime().totalMemory();
			freeMemory = Runtime.getRuntime().freeMemory();
			memory = (totalMemory - freeMemory);
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
