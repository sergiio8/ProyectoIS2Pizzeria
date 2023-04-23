package integracion.factoria;

import integracion.clientes.DAOClientes;
import integracion.clientes.DAOClientesImp;
import integracion.facturas.DAOFactura;
import integracion.facturas.DAOFacturaImp;
import integracion.ingredientes.DAOIngrediente;
import integracion.ingredientes.DAOIngredienteImp;
import integracion.mesas.DAOMesas;
import integracion.mesas.DAOMesasImp;
import integracion.producto.DAOPlato;
import integracion.producto.DAOPlatoImp;

public class FactoriaIntegracion extends FactoriaAbstractaIntegracion {

	@Override
	public DAOMesas crearDAOMesas() {
		// TODO Auto-generated method stub
		return new DAOMesasImp();
	}
	
	public DAOFactura crearDAOFactura() {
		return new DAOFacturaImp();
	}

	@Override
	public DAOPlato crearDAOPlato() {
		// TODO Auto-generated method stub
		return new DAOPlatoImp();
	}//cambio

	@Override
	public DAOIngrediente crearDAOIngrediente() {
		return new DAOIngredienteImp();
	}
	
	public DAOClientes crearDAOCliente() {
		return new DAOClientesImp();
	}

}
