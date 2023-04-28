package negocio.ingredientes;

import java.util.List;

public class TIngrediente {
	
	private String nombre;
	private int cantidad;
	//boolean activo;
	
	public TIngrediente(String nombre) {
		this.nombre = nombre;
	}
	
	public TIngrediente(String nombre, int cantidad) {
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
	/*public boolean getActivo() {
		return activo;
	}*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	/*public void setActivo(boolean activo) {
		this.activo = activo;
	}*/

}
