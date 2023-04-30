package negocio.facturas;

import java.util.Collection;

import negocio.clientes.TCliente;
import negocio.producto.TPlato;

public interface SAFactura {
	public TFactura buscarFactura(String id);
	public Collection<TFactura> mostrarFacturas();
	public boolean crearFactura(TDatosVenta datos);
	void anadirProducto(TLineaFactura linea, Carrito c);

}
