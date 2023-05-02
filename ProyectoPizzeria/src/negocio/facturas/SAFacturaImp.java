package negocio.facturas;

import java.util.ArrayList;
import java.util.Collection;

import integracion.clientes.DAOClientes;
import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.facturas.DAOFactura;
import integracion.facturas.DAOFacturaImp;
import integracion.facturas.DAOLineaFactura;
import integracion.producto.DAOPlato;
import negocio.clientes.TCliente;
import negocio.producto.TPlato;

public class SAFacturaImp implements SAFactura{
	
    
    public SAFacturaImp() {
        
    }

    @Override
    public boolean crearFactura(TDatosVenta datos) {
        DAOFactura daof = FactoriaAbstractaIntegracion.getInstace().crearDAOFactura();
        DAOClientes daoc = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
        DAOPlato daop = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
        DAOLineaFactura daol = FactoriaAbstractaIntegracion.getInstace().crearDAOLineaFactura();
        TFactura fact;
        ArrayList<TLineaFactura> lineas = new ArrayList<TLineaFactura>();
        int precio_total = 0;
        String id = null;
        TCliente c = daoc.obtenCliente(datos.getid_cliente());
        
        if (c != null) {
            for (TLineaFactura f : datos.getProductos()) {
                TPlato plato = daop.obtenPlato(f.getIdProducto());
                id = f.getIdFactura();
                precio_total += plato.getPrecio()*f.getCantidad();
                TLineaFactura l = daol.buscarLineaFactura(f.getId());
                if (l == null) {
                	lineas.add(f);
                    daol.crearLineaFactura(f);
                }
            }
            fact = daof.buscarFactura(id);
            if (fact == null) {
                fact = new TFactura(id, precio_total, datos, false);
                daof.crearFactura(fact);
            }
            else return false;
        }
        else return false;
        
        return true;
        
    }


    public TFactura buscarFactura(String id) {
        DAOFactura daof = FactoriaAbstractaIntegracion.getInstace().crearDAOFactura();
        return daof.buscarFactura(id);
    }

    @Override
    public Collection<TFactura> listarFacturas() {
        DAOFactura daof = FactoriaAbstractaIntegracion.getInstace().crearDAOFactura();
        Collection<TFactura> facts = daof.listarFacturas();
        return facts;
        
    }

	@Override
	public void anadirProducto(TLineaFactura linea, Carrito c) {
		c.anadirProducto(linea);
	}

}
