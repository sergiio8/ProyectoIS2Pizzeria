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
    public boolean crearFactura(TDatosVenta datos) {
        boolean valida = false;
        DAOFactura daof = FactoriaAbstractaIntegracion.getInstace().crearDAOFactura();
        DAOClientes daoc = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
        DAOPlato daop = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
        DAOLineaFactura daol = FactoriaAbstractaIntegracion.getInstace().crearDAOLineaFactura();
        TFactura fact;
        ArrayList<TLineaFactura> lineas = new ArrayList<TLineaFactura>();
        int precio_total = 0;
        String id = null;
        
        if (daoc.obtenCliente(datos.getid_cliente()) != null) {
            for (TLineaFactura f : datos.getProductos()) {
                TPlato plato = daop.obtenPlato(f.getIdProducto());
                if (plato != null) {
                    if (plato.getStock() > f.getCantidad()) {
                        valida = true;
                        plato.setStock(plato.getStock() - f.getCantidad());
                        id = f.getIdFactura();
                        precio_total += plato.getPrecio() * f.getCantidad();
                        lineas.add(f);
                        daol.crearLineaFactura(f);
                    }
                    else if (plato.getStock() > 0){
                        f.setCantidad(plato.getStock());
                        plato.setStock(0);
                        id = f.getIdFactura();
                        precio_total += plato.getPrecio() * f.getCantidad();
                        lineas.add(f);
                        daol.crearLineaFactura(f);
                    }
                }
            }
            fact = daof.buscarFactura(id);
            if (fact == null && !lineas.isEmpty()) {
                fact = new TFactura(id, precio_total, datos, true);
                daof.crearFactura(fact);
                valida = true;
            }
            else valida = false;
        }
        
        return valida;
        
    }


    @Override
    public boolean modificarFactura(TLineaFactura linea) {
        DAOFactura daof = FactoriaAbstractaIntegracion.getInstace().crearDAOFactura();
        return daof.modificarFactura(linea);
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
    public void anadirProducto(TLineaFactura linea, Carrito c) {
        c.anadirProducto(linea);
       
        // TODO Auto-generated method stub
    }
    

}
