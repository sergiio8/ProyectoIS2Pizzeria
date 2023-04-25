package negocio.producto;

import java.util.ArrayList;

import negocio.ingredientes.TIngrediente;

public abstract class TPlato {
	private String id;
	private String nombre;
	private double precio;
	private ArrayList<TIngrediente> ingredientes;
	private String descripcion;
	
	public TPlato(String id, String nombre, double precio, ArrayList<TIngrediente> ingredientes, String descripcion){
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.ingredientes = ingredientes;
		this.descripcion = descripcion;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public ArrayList<TIngrediente> getIngredientes() {
		return ingredientes;
	}
	
	public void setIngredientes(ArrayList<TIngrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}