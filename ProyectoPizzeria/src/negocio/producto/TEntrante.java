package negocio.producto;

import java.util.ArrayList;

import negocio.ingredientes.TIngrediente;

public class TEntrante extends TPlato{
	
	private Salsa salsa;
	
	public TEntrante(String id, String nombre, double precio, ArrayList<TIngrediente> ingredientes,
			String descripcion, int stock, Salsa salsa) {
		super(id, nombre, precio, ingredientes, descripcion, stock);
		this.salsa = salsa;
	}
	
	public Salsa getSalsa() {
		return salsa;
	}
	
	public void setSalsa(Salsa salsa) {
		this.salsa = salsa;
	}
}
