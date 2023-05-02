package integracion.producto;

import java.util.Collection;

import negocio.producto.TPlato;

public interface DAOPlato {
	
	public String insertaPlato(TPlato tp);
	public Boolean daDeBajaPlato(String id);
	public TPlato obtenPlato(String id);
	public Collection<TPlato> obtenTodosPlatos();
	public boolean modificaPlato(TPlato tp);
}
