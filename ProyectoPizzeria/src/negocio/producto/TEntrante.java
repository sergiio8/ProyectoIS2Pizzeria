package negocio.producto;

import java.util.ArrayList;

import negocio.ingredientes.TIngrediente;

public class TEntrante extends TPlato{
	
	//private Salsa salsa;
	
	public TEntrante(String id, String nombre, double precio, ArrayList<TIngrediente> ingredientes,
			String descripcion) {
		super(id, "Entrante", nombre, precio, ingredientes, descripcion);
	}
	
	/*public Salsa getSalsa() {
		return salsa;
	}
	
	public void setSalsa(Salsa salsa) {
		this.salsa = salsa;
	}
	*/
}
