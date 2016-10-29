package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Classe para armazenar o grafos e respectivas configurações.
 * @author aline
 *
 */
public class Graph {
	
	private File file; //Arquivo CSV de entrada. 
	
	private Map<String, Vertex> interfaces = new HashMap<String, Vertex>();
	private Map<String, Vertex> clients = new HashMap<String, Vertex>();
	
	public Graph(final File file) {
		this.file = file;
		this.createGraph();
	}
	
	
	/**
	 * Preenche matriz de vertices e respectivas arestas.
	 */
	private void createGraph(){
		try {
			BufferedReader inputReader = new BufferedReader(new FileReader(this.file));
			String  line = null;
			while((line = inputReader.readLine()) != null){ //Percorre as linhas do arquivo.
				String values[] = line.split(";");
				String interfaceInternal = values[0];
				String client = values[1];
				this.addClientToInterface(interfaceInternal, client);
				this.addClient(client);
			}
		} catch (IOException e) {
			System.err.println("[ERROR] Falha ao ler o arquivo de entrada." + e);
		}
	}
	
	private void addClientToInterface(final String interfaceInternal, final String client){
		
		if(this.interfaces.containsKey(interfaceInternal)){
			if(!this.interfaces.get(interfaceInternal).getListAdj().contains(client)){
				this.interfaces.get(interfaceInternal).getListAdj().add(client);
			}
		}
		else{
			Vertex v = new Vertex();
			v.setType(Type.INTERNAL_INTERFACE);
			v.setLabel(interfaceInternal);
			v.getListAdj().add(client);
			this.interfaces.put(interfaceInternal, v);
		}
	}
	
	private void addClient(final String client){
		if(this.clients.containsKey(client)){
			return;
		}
		else{
			Vertex v = new Vertex();
			v.setType(Type.CLIENT);
			v.setLabel(client);
			this.clients.put(client, v);
		}
	}
	
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
	public List<String> calcBaseline() {
		
		List<List<String>> conjuntos = this.calcAllSets(this.getNameAllInterfaces()); //Lista de todas as combinações.
		List<String> result = new ArrayList<String>();
		int bestSize = Integer.MAX_VALUE;
		
		for (List<String> subgraph : conjuntos) {
			if(subgraph.size() < bestSize){
				if (this.containsAllClients(subgraph)){
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
	
	/**
	 * Retorna verdadeiro se o subgrafo conecta-se com todos os clientes.
	 * Falso caso contrário.
	 * @param subgraph
	 * @return
	 */
	private boolean containsAllClients(List<String> subgraph) {
		for(String name : this.clients.keySet()){
			Vertex client = this.clients.get(name);
			if(!isClient(subgraph, client)){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Percorre todas as interfaces do subgrafo. Retorna verdadeiro se o mesmo
	 * conecta-se com todos os clientes.
	 * @param subgraph
	 * @param client
	 * @return
	 */
	private boolean isClient(List<String> subgraph, Vertex client) {
		
		//Percorre todos as interfaces do subgrafo. 
		for(String key : subgraph){ 
			Vertex v = this.interfaces.get(key);
			Set<String> list = v.getListAdj();
			if(list.contains(client.getLabel())){ //Verifica se alguma delas conecta-se com o cliente.
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retorna uma lista com o nome de todas as interfaces.
	 * @return
	 */
	private List<String> getNameAllInterfaces(){
		List<String> list = new ArrayList<String>();
		for(String name: this.interfaces.keySet()){
			list.add(name);
		}
		return list;
	}
}
