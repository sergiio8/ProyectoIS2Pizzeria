package negocio.ingredientes;

public class TPlatoIngrediente {
	private String idPlato;
	private String nombreIngrediente;
	
	public TPlatoIngrediente(String id, String nombre) {
		this.idPlato=id;
		this.nombreIngrediente=nombre;
	}
	public String getidPlato() {
		return this.idPlato;
	}
	
	public String getnombreIngrediente() {
		return this.nombreIngrediente;
	}
	
	public void setidPlato(String id) {
		this.idPlato=id;
	}
	
	public void setnombreIngrediente(String nombre) {
		this.nombreIngrediente=nombre;
	}
}
