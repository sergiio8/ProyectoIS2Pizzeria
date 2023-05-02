package negocio.producto;

import java.util.ArrayList;

public class TDatosPlato {
	
	private ArrayList<String> ingredientes;
	private TPlato plato;
	
	public TDatosPlato(TPlato plato, ArrayList<String> ingredientes) {
		this.plato = plato;
		this.ingredientes = ingredientes;
	}

	public ArrayList<String> getIngredientes(){
		return ingredientes;
	}
	
	public TPlato getPlato() {
		return plato;
	}
}
