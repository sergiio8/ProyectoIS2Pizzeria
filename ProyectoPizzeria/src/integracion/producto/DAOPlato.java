package integracion.producto;

import negocio.producto.TPlato;

public interface DAOPlato {
	
	public String insertaPlato(TPlato tp);
	public Boolean daDeBajaPlato(String id);
	public TPlato obtenPlato(String id);
	Boolean modificaPlato(TPlato tp);
}
