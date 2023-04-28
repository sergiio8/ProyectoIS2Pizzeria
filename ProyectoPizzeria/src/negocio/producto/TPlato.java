package negocio.producto;

import java.util.ArrayList;


public class TPlato {
	private String id;
	private String tipo;
	private String nombre;
	private double precio;
	private ArrayList<String> ingredientes;
	//os vais a cagar en TODO cuando borreis esto BAAAAA, no sabeis la q se os viene
    private String descripcion;
    //private boolean puede_hacerse;
    private int stock;

	
	public TPlato(String id, String tipo, String nombre, double precio, ArrayList<String> ingredientes, String descripcion){
		this.id = id;
		this.tipo = tipo;
		this.nombre = nombre;
		this.precio = precio;
		this.ingredientes = ingredientes;
		this.descripcion = descripcion;
		stock = 10;
		//puede_hacerse = true;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public int getStock() {
		return stock;
	}
	
	/*public boolean getPuedeHacerse() {
		return puede_hacerse;
	}*/
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public ArrayList<String> getIngredientes() {
		return ingredientes;
	}
	
	public void setIngredientes(ArrayList<String> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	/*public void setPuedeHacerse(boolean puede) {
		puede_hacerse = puede;
	}*/
}