package negocio.facturas;

import java.util.ArrayList;

public class TDatosVenta {
	private ArrayList<TLineaFactura> productos;
	private String id_factura;
	private String id_vendedor;
	private String id_cliente;
	private String fecha;
	
	public TDatosVenta(ArrayList<TLineaFactura> productos, String Id_factura, String Id_vendedor, String Id_cliente, String fecha) {
	    this.productos = productos;
	    id_factura = Id_factura;
		id_vendedor = Id_vendedor;
		id_cliente = Id_cliente;
		this.fecha = fecha;
	}
	
	public ArrayList<TLineaFactura>  getProductos(){
		return productos;
	}
	
	public String getid_vendedor() {
		return id_vendedor;
	}
	
	public String getid_cliente() {
		return id_cliente;
	}
	
	public String getid_factura() {
		return id_factura;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setProductos(ArrayList<TLineaFactura> prod) {
		this.productos = prod;
	}
	
	public void setId_vendedor(String Id_vendedor) {
		id_vendedor = Id_vendedor;
	}
	
	public void setId_cliente(String Id_cliente) {
		id_cliente = Id_cliente;
	}
	
	public void getid_factura(String ID_factura) {
		id_factura = ID_factura;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	

}
