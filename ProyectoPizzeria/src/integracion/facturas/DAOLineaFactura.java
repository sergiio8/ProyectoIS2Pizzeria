package integracion.facturas;

import negocio.facturas.TFactura;
import negocio.facturas.TLineaFactura;

public interface DAOLineaFactura {
	public boolean modificarLineaFactura(TLineaFactura f);
	public TLineaFactura buscarLineaFactura(String id);
	public void crearLineaFactura();
	//cambio
	//cambio223
	//cambio david
}
