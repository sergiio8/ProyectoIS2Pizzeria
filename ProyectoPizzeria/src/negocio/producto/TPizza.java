package negocio.producto;

import java.util.ArrayList;

public class TPizza extends TPlato {
	
	//private Tamano tamano;
	
	public TPizza(String nombre, double precio, ArrayList<String> ingredientes, 
			String descripcion) {
		super("Pizza", nombre, precio, ingredientes, descripcion);
	}
/*
	public Tamano getTamano() {
		return tamano;
	}
	
	public void setTamano(Tamano tamano) {
		this.tamano = tamano;
	}
	*/
}
