package negocio.facturas;

import java.util.ArrayList;

import integracion.clientes.DAOClientes;
import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.facturas.DAOFactura;
import integracion.facturas.DAOFacturaImp;
import integracion.facturas.DAOLineaFactura;
import integracion.mesas.DAOMesas;
import integracion.producto.DAOPlato;
import negocio.clientes.TCliente;
import negocio.mesas.TMesas;
import negocio.producto.TPlato;

public class SAFacturaImp implements SAFactura{
	
	public SAFacturaImp() {
		
	}

	@Override
	public boolean crearFactura(TCliente cliente, Carrito carrito) {
		boolean valida = false;
		DAOFactura daof = FactoriaAbstractaIntegracion.getInstace().crearDAOFactura();
		DAOClientes daoc = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		DAOPlato daop = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		TFactura fact;
		TDatosVenta datos;
		ArrayList<TLineaFactura> lineas = new ArrayList<TLineaFactura>();
		int precio_total = 0;
		String id = null;
		
		if (daoc.obtenCliente(cliente.getId()) != null) {
			for (TLineaFactura f : carrito.getProductos()) {
				TPlato plato = daop.obtenPlato(f.getIdProducto());
				if (plato != null) {
					id = f.getIdFactura();
					precio_total += plato.getPrecio();
					lineas.add(f);
				}	
			}
			datos = new TDatosVenta(lineas, cliente.getId(), "id_vendedor");
			fact = daof.buscarFactura(id);
			if (fact == null) {
				fact = new TFactura(id, precio_total, datos, "fecha" , true);
				daof.crearFactura(fact);
				valida = true;
			}	
		}
		
		return valida;
		
	}


	@Override
	public boolean modificarFactura(TFactura f) {
		DAOFactura daof = FactoriaAbstractaIntegracion.getInstace().crearDAOFactura();
		return daof.modificarFactura(f);
		// TODO Auto-generated method stub
		
	}

	@Override
	public TFactura buscarFactura(String id) {
		DAOFactura daof = FactoriaAbstractaIntegracion.getInstace().crearDAOFactura();
		return daof.buscarFactura(id);
		
		
	}

	@Override
	public void mostrarFacturas() {
		DAOFactura daof = FactoriaAbstractaIntegracion.getInstace().crearDAOFactura();
		daof.mostrarFacturas();
	}

	@Override
	public void anadirProducto(int cantidad, TPlato p, TFactura f) {
		DAOFactura daof = FactoriaAbstractaIntegracion.getInstace().crearDAOFactura();
		ArrayList<TLineaFactura> productos = f.getProductos();
		TLineaFactura linea = new TLineaFactura("nuevo_id", f.getId(), p.getId(), cantidad);
		productos.add(linea);
		f.setProductos(productos);
		daof.modificarFactura(f);
		// TODO Auto-generated method stub
		
	}
	

}
