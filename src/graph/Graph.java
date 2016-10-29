package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para armazenar o grafos e respectivas configurações.
 * @author aline
 *
 */
public class Graph {
	
	/**
	 * Calcula todas as combinações de conjuntos de interfaces.
	 * @param listOriginal
	 * @return
	 */
	public List<List<String>> calcAllSets(List<String> listOriginal) {
		
		 List<List<String>> conjuntos = new ArrayList<List<String>>();
		if (listOriginal.isEmpty()) {
			conjuntos.add(new ArrayList<String>());
		}
		else{
			List<String> list = new ArrayList<String>(listOriginal);
			String cabecalho = list.get(0);//armazena primeira posição da lista.
			List<String> sublist = list.subList(1, list.size());//restante da lista sem o cabeçallho.
			for (List<String> conjunto : calcAllSets(sublist)) {

				List<String> novoConjunto = new ArrayList<String>();
				novoConjunto.add(cabecalho);
				novoConjunto.addAll(conjunto);
				
				//Adiciona novo conjunto na lista.
				conjuntos.add(novoConjunto);
				conjuntos.add(conjunto);
			}	
		}
		
		return conjuntos;
	}
	
	/**
	 * Calcula o baseline (solução exata), do cálculo do conjunto dominante.
	 * @param list
	 * @return
	 */
	public List<String> calcBaseline(List<String> list) {
		List<String> result = new ArrayList<>();
		//TODO: pendente.
		return result;
		
	}

}
