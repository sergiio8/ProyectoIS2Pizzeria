package negocio.facturas;

import java.util.ArrayList;
import java.util.Iterator;

public class Carrito {
	private ArrayList<TLineaFactura> lista_productos;
	private int last_id;
	
	public Carrito() {
		lista_productos = new ArrayList<TLineaFactura>();
		last_id = -1;
	}
	
	public void anadirProducto(TLineaFactura f) {
		lista_productos.add(f);
		last_id++;
	}
	
	public void eliminarProducto(TLineaFactura f) {
		lista_productos.remove(f);
		last_id--;
	}
	
	public ArrayList<TLineaFactura> getProductos() {
		return lista_productos;
	}
	
	public void cerrarVenta(TDatosVenta dt) {
		dt.setProductos(lista_productos);
	}
	

}
