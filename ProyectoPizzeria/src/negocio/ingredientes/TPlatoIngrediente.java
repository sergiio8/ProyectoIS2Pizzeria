package negocio.ingredientes;

public class TPlatoIngrediente {
	private String nombrePlato;
	private String nombreIngrediente;
	
	public TPlatoIngrediente(String id, String nombre) {
		this.nombrePlato=id;
		this.nombreIngrediente=nombre;
	}
	public String getnombrePlato() {
		return this.nombrePlato;
	}
	
	public String getnombreIngrediente() {
		return this.nombreIngrediente;
	}
	
	public void setnombrePlato(String id) {
		this.nombrePlato=id;
	}
	
	public void setnombreIngrediente(String nombre) {
		this.nombreIngrediente=nombre;
	}
}
