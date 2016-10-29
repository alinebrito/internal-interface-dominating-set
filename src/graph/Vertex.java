package graph;

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

}
