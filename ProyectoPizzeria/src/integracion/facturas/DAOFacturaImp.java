package integracion.facturas;

import negocio.facturas.TFactura;

public class DAOFacturaImp implements DAOFactura {
	
	
	public DAOFacturaImp() {
		
		
		
		
	}
	
	
	public boolean modificarFactura(TFactura f) {
		TFactura factura = buscarFactura(f.getId());
		if (factura != null) {
			//modificarla en base de datos
			return true;
		}
		else return false;
		//cambio main...
		
		
		
	}
	
	public TFactura buscarFactura(String id) {
		//acceso a base de datos y lectura de datos qeu se pasan al transfer por parametro
		TFactura factura = new TFactura(id, 0, null, id, false);
		return factura;
	}
	
	public int crearFactura(TFactura fact) {
		return -2;
		
		
		
	}


	@Override
	public void mostrarFacturas() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	

}
