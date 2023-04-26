package integracion.facturas;

import java.util.Collection;

import negocio.facturas.TFactura;
import negocio.facturas.TLineaFactura;

public interface DAOFactura {
	
	public boolean modificarFactura(TLineaFactura linea);
	public TFactura buscarFactura(String id);
	public int crearFactura(TFactura f);
	public Collection<TFactura> mostrarFacturas();

}
