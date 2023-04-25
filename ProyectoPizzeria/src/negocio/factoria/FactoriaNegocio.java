package negocio.factoria;

import negocio.clientes.SAClientes;
import negocio.clientes.SAClientesImp;
import negocio.facturas.SAFactura;
import negocio.facturas.SAFacturaImp;
import negocio.mesas.SAMesas;
import negocio.mesas.SAMesasImp;
import negocio.producto.SAPlato;
import negocio.producto.SAPlatoImp;

public class FactoriaNegocio extends FactoriaAbstractaNegocio {

	@Override
	public SAMesas crearSAMesas() {
		return new SAMesasImp();
	}
	
	public SAFactura crearSAFactura() {
		return new SAFacturaImp();
	}
	
	public SAPlato crearSAPlato() {
		return new SAPlatoImp();
	}
	
	public SAClientes crearSAClientes() {
		return new SAClientesImp();
	}

}
