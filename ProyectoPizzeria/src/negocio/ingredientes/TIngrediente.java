package negocio.ingredientes;

public class TIngrediente {
	
	private String nombre;
	private int cantidad;
	
	public TIngrediente(String nombre) {
		this.nombre = nombre;
	}
	
	public TIngrediente(String nombre, int cantidad) {
		this.cantidad = cantidad;
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
