package negocio.producto;

import java.util.ArrayList;
import negocio.ingredientes.TIngrediente;

public class TPostre extends TPlato {

	public TPostre(String id, String nombre, double precio, ArrayList<TIngrediente> ingredientes, String descripcion, int stock) {
		super(id, nombre, precio, ingredientes, descripcion, stock);
	}
	
}