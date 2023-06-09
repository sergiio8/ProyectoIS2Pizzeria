package integracion.factoria;

import integracion.clientes.DAOClientes;
import integracion.clientes.DAOClientesImp;
import integracion.facturas.DAOFactura;
import integracion.facturas.DAOFacturaImp;
import integracion.facturas.DAOLineaFactura;
import integracion.facturas.DAOLineaFacturaImp;
import integracion.ingredientes.DAOIngrediente;
import integracion.ingredientes.DAOIngredienteImp;
import integracion.ingredientes.DAOPlatoIngrediente;
import integracion.ingredientes.DAOPlatoIngredienteImp;
import integracion.mesas.DAOMesas;
import integracion.mesas.DAOMesasImp;
import integracion.mesas.DAOReserva;
import integracion.mesas.DAOReservaImp;
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
	}

	@Override
	public DAOIngrediente crearDAOIngrediente() {
		return new DAOIngredienteImp();
	}
	
	public DAOClientes crearDAOCliente() {
		return new DAOClientesImp();
	}
	
	public DAOLineaFactura crearDAOLineaFactura() {
		return new DAOLineaFacturaImp();
	}

	@Override
	public DAOPlatoIngrediente crearDAOPlatoIngrediente() {
		// TODO Auto-generated method stub
		return new DAOPlatoIngredienteImp();
	}

	@Override
	public DAOReserva crearDAOReserva() {
		return new DAOReservaImp();
	}

}
