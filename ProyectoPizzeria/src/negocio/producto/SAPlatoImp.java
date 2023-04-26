package negocio.producto;

import java.util.Collection;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.ingredientes.DAOIngrediente;
import integracion.producto.DAOPlato;
import negocio.ingredientes.TIngrediente;

public class SAPlatoImp implements SAPlato {

	@Override
	public String alta(TPlato tp) {
		String id = "";
		
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		
		if(tp != null) {
			TPlato plato = daoPlato.obtenPlato(tp.getId());
			if(plato == null) {
				id = daoPlato.insertaPlato(tp);
			}
		}
		
		return id;
	}

	@Override
	public TPlato consulta(String id) {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		return daoPlato.obtenPlato(id);
	}

	@Override
	public Collection<TPlato> consultaTodos() {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		return daoPlato.obtenTodosPlatos();
	}

	@Override
	public Boolean modificar(TPlato tp) {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		return daoPlato.modificaPlato(tp);
	}

	@Override
	public Boolean borrar(String id) {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		return daoPlato.daDeBajaPlato(id);
	}

	


	
	/*public boolean puede_hacerse(TPlato plato) {
		DAOIngrediente daoi = FactoriaAbstractaIntegracion.getInstace().crearDAOIngrediente();
		boolean se_puede = true;
		for (int i = 0; i < plato.getIngredientes().size() && se_puede; ++i) {
			TIngrediente ti = daoi.cogerIngrediente(plato.getIngredientes().get(i).getNombre());
			if (ti.getCantidad() == 0) se_puede = false;
		}
		
		return se_puede;
		
	}
	public boolean hacerPlato(String id) {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		TPlato plato = daoPlato.obtenPlato(id);
		DAOIngrediente daoi = FactoriaAbstractaIntegracion.getInstace().crearDAOIngrediente();
		boolean se_puede = puede_hacerse(plato);
		if (se_puede) {
			for (int i = 0; i < plato.getIngredientes().size() && se_puede; ++i) {
				TIngrediente ti = daoi.cogerIngrediente(plato.getIngredientes().get(i).getNombre());
				ti.setCantidad(ti.getCantidad() - 1);
			}
		}
		
		return se_puede;
	}*/

}