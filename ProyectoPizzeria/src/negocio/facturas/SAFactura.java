package negocio.facturas;

import negocio.clientes.TCliente;
import negocio.producto.TPlato;

public interface SAFactura {
	public boolean crearFactura(TCliente cliente, Carrito carrito);
	public void anadirProducto(int cantidad, TPlato p, TFactura f);
	public boolean modificarFactura(TFactura f);
	public TFactura buscarFactura(String id);
	public void mostrarFacturas();

}
