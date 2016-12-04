package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Heuristic {
	
	public List<String> calc(final Map<String, Vertex> interfaces, final Map<String, Vertex> clients) {
		List<String> result = new ArrayList<String>();
		
		Set<String> options = new HashSet<String>(UtilGraph.getSetNames(interfaces));
		
		while(!options.isEmpty()){
			//Busca candidato
			Vertex candidate = this.findCandidate(interfaces, clients);//Vértice com menos clientes disponíveis.
			
			//Verifica se candidato pode ser removido sem deixar o grafo desconectado.
			options.remove(candidate.getLabel());
			interfaces.get(candidate.getLabel()).setColor(Color.GRAY);
			
			Boolean isConnected = this.checkConnectedGraphic(clients, interfaces);
			
			//Se candidato não afeta solução, marca como cinza. Ou seja, pode ser removido.
			if(!isConnected){
				//Caso contrário inclui o candidato no conjunto solução.
				result.add(candidate.getLabel());
				interfaces.get(candidate.getLabel()).setColor(Color.BLACK);
				colorClients(clients, candidate.getListAdj());
				
			}
		}
		return result;
	}
	
	/**
	 * Retorna verdadeiro se existem clientes não dominados. Falso caso contrário.
	 * @param clients
	 * @return
	 */
	private Boolean checkConnectedGraphic(final Map<String, Vertex> clients, final Map<String, Vertex> interfaces){
		
		List<String> subgraph = new ArrayList<String>();
		
		for(String name: interfaces.keySet()){
			Vertex v = interfaces.get(name);
			if(!v.getColor().equals(Color.GRAY)){
				subgraph.add(name);
			}
		}
		
		return UtilGraph.containsAllClients(subgraph, clients, interfaces);
	}
	
	
	/**
	 * Colore os clientes dominados.
	 * @param clients
	 * @param names
	 */
	private void colorClients(final Map<String, Vertex> clients, final Set<String> names){
		
		for(String name : clients.keySet()){
			if(names.contains(name)){
				clients.get(name).setColor(Color.BLACK);
			}
		}
		
	}
	
	/**
	 * Retorna o próximo vértice candidato com menor peso.
	 * Peso é dado pela quantidade de clientes pendentes conectados a ele.
	 */
	private Vertex findCandidate(final Map<String, Vertex> interfaces, final Map<String, Vertex> clients){

		String candidate = "";
		int size = Integer.MAX_VALUE; 
		
		for(String nameInterface: interfaces.keySet()){
			Vertex v = interfaces.get(nameInterface);
			if(v.getColor().equals(Color.WHITE)){
				if(v.getListAdjWhite(clients).size() < size){
					size = v.getListAdjWhite(clients).size();
					candidate = nameInterface;
				}
			}
		}
		return interfaces.get(candidate);
	}

}
