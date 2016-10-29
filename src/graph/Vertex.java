package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

}
