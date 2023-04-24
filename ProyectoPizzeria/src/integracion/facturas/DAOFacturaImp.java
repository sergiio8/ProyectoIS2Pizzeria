package integracion.facturas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import integracion.factoria.FactoriaAbstractaIntegracion;

import org.json.JSONObject;
import negocio.facturas.TFactura;
import negocio.facturas.TLineaFactura;

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
        //cambio
        
        
        
    }
    
    public TFactura buscarFactura(String id) {
    	return null;
    }
    
    public int crearFactura(TFactura fact) {
        int id = 0;
        JSONArray ja = null;
        try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Facturas.json"))){
            JSONObject jsonInput = new JSONObject (new JSONTokener(in));
            ja = jsonInput.getJSONArray("ListaFacturas");
            JSONObject jo = new JSONObject();
            jo.put("fecha", fact.getFecha());
            jo.put("id", fact.getId());
            jo.put("precio", fact.getPrecio_total());
            DAOLineaFactura daol = FactoriaAbstractaIntegracion.getInstace().crearDAOLineaFactura();
            for (TLineaFactura t : fact.getProductos()) {
                daol.crearLineaFactura(t);
                jo.put("linea" + t.getId(), daol.buscarLineaFactura(t.getId()));
                
            }
            jo.put("cliente", fact.getIdCliente());
            jo.put("vendedor", fact.getIdVendedor());
            
            ja.put(jo);
        }
        catch(IOException ie) {
            
        }
        catch(JSONException je) {
            
        }
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Facturas.json"))){
            JSONObject jo2 = new JSONObject();
            jo2.put("ListaFacturas", ja);
            bw.write(jo2.toString());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        return id;
    }


    @Override
    public void mostrarFacturas() {
        // TODO Auto-generated method stub
        
    }
    
    
    
    
    
    
    
    

}