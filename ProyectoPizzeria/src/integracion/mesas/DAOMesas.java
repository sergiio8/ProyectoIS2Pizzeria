package integracion.mesas;

import java.util.Collection;

import negocio.mesas.TMesas;

public interface DAOMesas {
	
	public Integer insertaMesa(TMesas tm);
	public Boolean daDeBajaMesa(Integer id);
	public TMesas obtenMesa(Integer id);
	Boolean modificaMesa(TMesas tm);
	Collection<TMesas> consultaTodos();
}
