package negocio.clientes;

import java.util.Collection;

import integracion.clientes.DAOClientes;
import integracion.factoria.FactoriaAbstractaIntegracion;

public class SAClientesImp implements SAClientes{
	
	@Override
	public String alta(TCliente c) {// si cliente ya existía devuelve null
		
		String id = null;
		DAOClientes infoCliente = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		
		if(c != null) {
			TCliente cliente = infoCliente.obtenCliente(c.getId());
			if(cliente == null) {
				id = infoCliente.insertarCliente(c);
			}
		}
		return id;
	}

	@Override
	public TCliente consulta(String id) {
		// TODO Auto-generated method stub
		DAOClientes infoCliente = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		TCliente cliente = infoCliente.obtenCliente(id);
		
		return cliente;
	}

	@Override
	public Collection<TCliente> consultaTodos() {
		// TODO Auto-generated method stub
		DAOClientes infoCliente = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		return infoCliente.consultaTodos();
	}

	@Override
	public Boolean modificar(TCliente c) {
		// TODO Auto-generated method stub
		DAOClientes infoCliente = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		return infoCliente.modificaCliente(c);
	}

	@Override
	public Boolean borrar(String id) {
		// TODO Auto-generated method stub
		DAOClientes infoCliente = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		return infoCliente.daDeBajaCliente(id);
	}

}
