package integracion.factoria;

import integracion.facturas.DAOFactura;
import integracion.mesas.DAOMesas;

public abstract class FactoriaAbstractaIntegracion { //singleton
	
	private static FactoriaAbstractaIntegracion instancia = null;
		
	public static FactoriaAbstractaIntegracion getInstace() { //creacion perezosa
		if(instancia == null) {
			instancia = new FactoriaIntegracion();
		}
		return instancia;
	}
		
		//metodos para crear DAOS
	
	public abstract DAOMesas crearDAOMesas();
	public abstract DAOFactura crearDAOFactura();
}
