package integracion.factoria;

import integracion.facturas.DAOFactura;
import integracion.facturas.DAOFacturaImp;
import integracion.ingredientes.DAOIngrediente;
import integracion.ingredientes.DAOIngredienteImp;
import integracion.mesas.DAOMesas;
import integracion.mesas.DAOMesasImp;
import integracion.producto.DAOPlato;

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
		return null;
	}

	@Override
	public DAOIngrediente crearDAOIngrediente() {
		// TODO Auto-generated method stub
		return new DAOIngredienteImp();
	}

}
