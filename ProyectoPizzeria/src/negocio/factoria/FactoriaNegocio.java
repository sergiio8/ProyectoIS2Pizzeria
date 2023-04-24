package negocio.factoria;

import negocio.facturas.SAFactura;
import negocio.facturas.SAFacturaImp;
import negocio.mesas.SAMesas;
import negocio.mesas.SAMesasImp;

public class FactoriaNegocio extends FactoriaAbstractaNegocio {

	@Override
	public SAMesas crearSAMesas() {
		
		return new SAMesasImp();
	}
	
	public SAFactura crearSAFactura() {
		return new SAFacturaImp();
	}

}
