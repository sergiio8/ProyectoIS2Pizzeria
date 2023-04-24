package negocio.facturas;

import negocio.clientes.TCliente;
import negocio.producto.TPlato;

public interface SAFactura {
	public void anadirProducto(int cantidad, TPlato p, TFactura f);
	public boolean modificarFactura(TFactura f);
	public TFactura buscarFactura(String id);
	public void mostrarFacturas();
	public boolean crearFactura(TDatosVenta datos);

}
