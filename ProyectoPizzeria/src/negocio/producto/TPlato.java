package negocio.producto;

public class TPlato {
	private String tipo;
	private String nombre;
	private double precio;
    private String descripcion;
	
	public TPlato(String tipo, String nombre, double precio, String descripcion){
		this.tipo = tipo;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}