package integracion.facturas;

import java.util.Collection;

import negocio.facturas.TFactura;
import negocio.facturas.TLineaFactura;

public interface DAOLineaFactura {
	public boolean modificarLineaFactura(TLineaFactura f);
	public TLineaFactura buscarLineaFactura(String id);
	public void crearLineaFactura(TLineaFactura f);
	public Collection<TLineaFactura> mostrarLineasFactura();
}
