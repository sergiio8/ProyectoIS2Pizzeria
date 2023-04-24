package integracion.facturas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

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
        JSONArray ja = null;
        try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/LineasFacturas.json"))){
            JSONObject jsonInput = new JSONObject (new JSONTokener(in));
            ja = jsonInput.getJSONArray("ListaLineasFactura");
            JSONObject jo = new JSONObject();
            jo.put("id_factura", f.getIdFactura());
            jo.put("id", f.getId());
            jo.put("producto", f.getIdProducto());
            jo.put("cantidad", f.getCantidad());
            ja.put(jo);
        }
        catch(IOException ie) {
            
        }
        catch(JSONException je) {
            
        }
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/LineasFacturas.json"))){
            JSONObject jo2 = new JSONObject();
            jo2.put("ListaLineasFactura", ja);
            bw.write(jo2.toString());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
    }

    public void listarLineaFactura() {
        // TODO Auto-generated method stub
        
    }
    
    

}
