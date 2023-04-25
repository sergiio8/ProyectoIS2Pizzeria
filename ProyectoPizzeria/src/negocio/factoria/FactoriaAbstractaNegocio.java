package negocio.factoria;

import negocio.clientes.SAClientes;
import negocio.facturas.SAFactura;
import negocio.mesas.SAMesas;
import negocio.producto.SAPlato;

public abstract class FactoriaAbstractaNegocio {
	
	private static FactoriaAbstractaNegocio instancia = null;
	
	public static FactoriaAbstractaNegocio getInstace() { //creacion perezosa
		if(instancia == null) {
			instancia = new FactoriaNegocio();
		}
		return instancia;
	}
		
		//metodos para crear SAs
	
	
	
	public abstract SAMesas crearSAMesas();
	public abstract SAFactura crearSAFactura();
	public abstract SAPlato crearSAPlato();
	public abstract SAClientes crearSAClientes();
}
