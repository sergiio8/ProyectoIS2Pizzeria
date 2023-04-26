package negocio.facturas;

import java.util.Collection;

import negocio.clientes.TCliente;
import negocio.producto.TPlato;

public interface SAFactura {
	public void anadirProducto(TLineaFactura linea, Carrito c);
	public boolean modificarFactura(TLineaFactura linea);
	public TFactura buscarFactura(String id);
	public Collection<TFactura> mostrarFacturas();
	public boolean crearFactura(TDatosVenta datos);

}
