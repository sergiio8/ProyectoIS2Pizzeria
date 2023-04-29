package negocio.producto;

import java.util.ArrayList;

public class TPostre extends TPlato {

	public TPostre(String nombre, double precio, ArrayList<String> ingredientes, String descripcion) {
		super("Postre", nombre, precio, ingredientes, descripcion);
	}
	
}