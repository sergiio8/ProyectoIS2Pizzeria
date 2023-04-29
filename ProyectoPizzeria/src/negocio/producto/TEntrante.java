package negocio.producto;

import java.util.ArrayList;


public class TEntrante extends TPlato{
	
	//private Salsa salsa;
	
	public TEntrante(String nombre, double precio, ArrayList<String> ingredientes,
			String descripcion) {
		super("Entrante", nombre, precio, ingredientes, descripcion);
	}
	
	/*public Salsa getSalsa() {
		return salsa;
	}
	
	public void setSalsa(Salsa salsa) {
		this.salsa = salsa;
	}
	*/
}
