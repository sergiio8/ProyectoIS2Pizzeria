package negocio.ingredientes;

import java.util.List;

public class TDatosAltaIngrediente {
	private List<String> listaPlatos;
	private TIngrediente ing;
	
	public TDatosAltaIngrediente(TIngrediente i, List<String> l){
		ing = i;
		listaPlatos = l;
	}
	
	public TIngrediente getIngrediente() {
		return  ing;
	}
	public List<String> getListaPlatos(){
		return listaPlatos;
	}
}
