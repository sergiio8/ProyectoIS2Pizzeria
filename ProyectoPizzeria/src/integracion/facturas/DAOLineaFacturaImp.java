package integracion.facturas;

import negocio.facturas.TLineaFactura;

public class DAOLineaFacturaImp implements DAOLineaFactura{
	
	public DAOLineaFacturaImp() {
		
	}

	@Override
	public boolean modificarLineaFactura(TLineaFactura linea) {
		TLineaFactura l = buscarLineaFactura(linea.getId());
		if (l != null) {
			//modificar la base de datos
			return true;
		}
		else return false;
		
	
	}

	@Override
	public TLineaFactura buscarLineaFactura(String id) {
		//lectura base de datos y pasar datos por parametro
		TLineaFactura linea = new TLineaFactura(null, null, null, 0);
		return linea;
	}

	@Override
	public void crearLineaFactura(TLineaFactura f) {
		// TODO Auto-generated method stub
		
	}

	public void listarLineaFactura() {
		// TODO Auto-generated method stub
		
	}
	
	

}
