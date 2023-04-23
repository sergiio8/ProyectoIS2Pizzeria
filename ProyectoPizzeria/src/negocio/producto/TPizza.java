package negocio.producto;

import java.util.ArrayList;
import negocio.ingredientes.TIngrediente;

public class TPizza extends TPlato {
	
	private Tamano tamano;
	
	public TPizza(String id, String nombre, double precio, ArrayList<TIngrediente> ingredientes, 
			String descripcion, Tamano tamano) {
		super(id, nombre, precio, ingredientes, descripcion);
		this.tamano = tamano;
	}

	public Tamano getTamano() {
		return tamano;
	}
	
	public void setTamano(Tamano tamano) {
		this.tamano = tamano;
	}
}