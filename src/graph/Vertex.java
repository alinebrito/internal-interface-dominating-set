package graph;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Classe para gerenciar vértice.
 * @author aline
 *
 */
public class Vertex {
	
	/**
	 * Tipo do vértice.
	 */
	private Type type;
	
	/**
	 * Valor armazenando no vértice.
	 */
	private String label;
	
	/**
	 * Armazena cor do vértice (branco é um vértice pendente, preto é um vértice dominado.)
	 */
	private Color color = Color.WHITE;
	
	/**
	 * Lista de vértices clientes.
	 */
	private Set<String> listAdj = new HashSet<String>();

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Set<String> getListAdj() {
		return listAdj;
	}

	public void setListAdj(Set<String> listAdj) {
		this.listAdj = listAdj;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Retorna a lista de vértices adijacentes que não estão dominados.
	 * @param clients
	 * @return
	 */
	public Set<String> getListAdjWhite(final Map<String, Vertex> clients){
		Set<String> list = new HashSet<String>();
		
		for(String nameAdj: this.listAdj){
			Vertex adj = clients.get(nameAdj);
			if(adj.getColor().equals(Color.WHITE)){
				list.add(nameAdj);
			}
		}
		return list;
	}

}
