package negocio.producto;

import java.util.Collection;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.producto.DAOPlato;

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
		// TODO Auto-generated method stub
		return null;
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

}