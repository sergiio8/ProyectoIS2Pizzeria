package negocio.producto;

import java.util.ArrayList;

public class TPostre extends TPlato {

	public TPostre(String id, String nombre, double precio, ArrayList<String> ingredientes, String descripcion) {
		super(id, "Postre", nombre, precio, ingredientes, descripcion);
	}
	
}