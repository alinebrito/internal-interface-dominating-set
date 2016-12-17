package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
				String interfaceInternal = values[1];
				String client = values[0];
				this.addClientToInterface(interfaceInternal, client);
				this.addClient(interfaceInternal, client);
			}
			System.out.println(this.interfaces.size() + " interfaces.");
			System.out.println(this.clients.size() + " clientes.");
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
	
	private void addClient(final String interfaceInternal, final String client){
		if(this.clients.containsKey(client)){
			if(!this.clients.get(client).getListAdj().contains(interfaceInternal)){
				this.clients.get(client).getListAdj().add(interfaceInternal);
			}
		}
		else{
			Vertex v = new Vertex();
			v.setType(Type.CLIENT);
			v.setLabel(client);
			v.getListAdj().add(interfaceInternal);
			this.clients.put(client, v);
		}
	}
	
	/**
	 * Calcula o baseline (solução exata), do cálculo do conjunto dominante.
	 * @param list
	 * @return
	 */
	public List<String> calcBaseline() {
		Baseline baseline = new Baseline();
		return baseline.calc(this.interfaces, this.clients);
	}
	
	/**
	 * Calcula o heurística gulosa para cálculo do baseline.
	 * @param list
	 * @return
	 */
	public List<String> calcHeuristic() {
		Heuristic heuristic = new Heuristic();
		return heuristic.calc(this.interfaces, this.clients);
	}

}
