package negocio.ingredientes;

import java.util.List;

public class TDatosIngrediente {
	private List<TIngrediente> ingredientes;
	private List<TPlatoIngrediente> platoIngredientes;
	
	public TDatosIngrediente(List<TIngrediente> ingredientes, List<TPlatoIngrediente> platoIngredientes) {
		this.ingredientes = ingredientes;
		this.platoIngredientes = platoIngredientes;
	}
	
	public List<TIngrediente> getIngredientes(){
		return ingredientes;
	}
	public List<TPlatoIngrediente> getPlatoIngredientes(){
		return platoIngredientes;
	}
}
