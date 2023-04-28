package negocio.ingredientes;

public class TIngrediente {
	
	private String nombre;
	private int cantidad;
	private String[] platos;
	//boolean activo;
	
	public TIngrediente(String nombre) {
		this.nombre = nombre;
	}
	
	public TIngrediente(String nombre, int cantidad) {
		//this.platos = platos;
		this.cantidad = cantidad;
		this.nombre = nombre;
		//activo = true;
	}
	
	/*public String getPlatosToString() {
		String s = "{ ";
		for(int i=0; i<platos.length; i++) {
			s+=platos[i];
			if(i<platos.length-1) s+=", ";
		}
		s+=" }";
		return s;
	}*/
	
	public String getNombre() {
		return nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	/*public String[] getPlatos() {
		return platos;
	}*/
	/*public boolean getActivo() {
		return activo;
	}*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	/*public void setPlatos(String[] platos) {
		this.platos = platos;
	}*/
	/*public void setActivo(boolean activo) {
		this.activo = activo;
	}*/

}
