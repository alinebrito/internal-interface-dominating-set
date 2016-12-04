package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UtilGraph {
	
	/**
	 * Retorna uma lista com o nome de todas as interfaces.
	 * @return
	 */
	public static List<String> getListNames(final Map<String, Vertex> listVertex){
		List<String> list = new ArrayList<String>();
		for(String name: listVertex.keySet()){
			list.add(name);
		}
		return list;
	}
	
	/**
	 * Retorna uma lista com o nome de todas as interfaces.
	 * @return
	 */
	public static Set<String> getSetNames(final Map<String, Vertex> listVertex){
		Set<String> list = new HashSet<String>();
		for(String name: listVertex.keySet()){
			list.add(name);
		}
		return list;
	}

	/**
	 * Retorna verdadeiro se o subgrafo conecta-se com todos os clientes.
	 * Falso caso contrário.
	 * @param subgraph
	 * @return
	 */
	public static boolean containsAllClients(final List<String> subgraph, final Map<String, Vertex> clients, final Map<String, Vertex> interfaces) {
		for(String name : clients.keySet()){
			Vertex client = clients.get(name);
			if(!isClient(subgraph, client, interfaces)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Retorna verdadeiro se o subgrafo conecta-se com todos os clientes.
	 * Falso caso contrário.
	 * @param subgraph
	 * @return
	 */
	public static boolean containsAllClients(final Set<String> subgraph, final Map<String, Vertex> clients, final Map<String, Vertex> interfaces) {
		List<String> graph = new ArrayList<>(subgraph);
		return containsAllClients(graph, clients, interfaces);
	}
	
	/**
	 * Calcula todas as combinações de conjuntos de interfaces.
	 * @param listOriginal
	 * @return
	 */
	public static List<List<String>> calcAllSets(final List<String> listOriginal) {
		
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
	 * Percorre todas as interfaces do subgrafo. Retorna verdadeiro se o mesmo
	 * conecta-se com todos os clientes.
	 * @param subgraph
	 * @param client
	 * @return
	 */
	public static boolean isClient(final List<String> subgraph, final Vertex client, final Map<String, Vertex> interfaces) {
		
		//Percorre todos as interfaces do subgrafo. 
		for(String key : subgraph){ 
			Vertex v = interfaces.get(key);
			Set<String> list = v.getListAdj();
			if(list.contains(client.getLabel())){ //Verifica se alguma delas conecta-se com o cliente.
				return true;
			}
		}
		return false;
	}
}
