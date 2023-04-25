package negocio.clientes;

public class TCliente {
	
	private String id;
	private String nombre;
	private String apellido;
	
	public TCliente(String id, String nombre, String apellido) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
}
