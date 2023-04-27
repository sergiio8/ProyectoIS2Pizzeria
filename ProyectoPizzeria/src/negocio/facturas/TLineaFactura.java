package negocio.facturas;

public class TLineaFactura {
	private String id;
	private String id_factura;
	private String id_producto;
	private double precio_linea;
	private int cantidad;
	
	public TLineaFactura(String Id, String IdFactura, String IdProducto, int Cantidad, double precio) {
		id = Id;
		id_factura = IdFactura;
		id_producto = IdProducto;
		cantidad = Cantidad;
		precio_linea =  cantidad * precio;
	}
	
	public String getId() {
		return id;
	}
	
	public String getIdFactura() {
		return id_factura;
	}
	
	public String getIdProducto() {
		return id_producto;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public double getPrecio() {
		return precio_linea;
	}
	
	public void setId(String Id) {
		id = Id;
		
	}
	
	public void setIdFactura(String IdFactura) {
		id_factura = IdFactura;
	}
	
	public void setIdProducto(String IdProducto) {
		id_producto = IdProducto;
	}
	
	public void setCantidad(int Cantidad) {
		cantidad = Cantidad;
	}
	
	public void setPrecio(double precio) {
		this.precio_linea = precio;
	}
	

}
