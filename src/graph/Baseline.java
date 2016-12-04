package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Baseline {

	/**
	 * Calcula o baseline (solução exata), do cálculo do conjunto dominante.
	 * @param list
	 * @return
	 */
	public List<String> calc(final Map<String, Vertex> interfaces, final Map<String, Vertex> clients) {
		
		List<List<String>> sets = UtilGraph.calcAllSets(UtilGraph.getListNames(interfaces)); //Lista de todas as combinações.
		List<String> result = new ArrayList<String>();
		int bestSize = Integer.MAX_VALUE;
		
		for (List<String> subgraph : sets) {
			if(subgraph.size() < bestSize){
				if (UtilGraph.containsAllClients(subgraph, clients, interfaces)){
					if(subgraph.size() < bestSize){
						result.clear();
						result.addAll(subgraph);
						bestSize = subgraph.size();
					}
				}
			}
		}
		return result;
	}
}
