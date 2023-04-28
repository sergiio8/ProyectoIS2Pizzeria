package integracion.facturas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import integracion.factoria.FactoriaAbstractaIntegracion;
import negocio.facturas.TDatosVenta;
import negocio.facturas.TFactura;
import negocio.facturas.TLineaFactura;
import negocio.mesas.TMesas;

public class DAOLineaFacturaImp implements DAOLineaFactura{
    
    public DAOLineaFacturaImp() {
        
    }

    @Override
    /*public boolean modificarLineaFactura(TLineaFactura linea) {
    	boolean res = true;
    	
		
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/LineasFactura.json"))){ 
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaLineasFactura");
			
		}
		catch(Exception e1) {
			return false;
		}
		
		int i = 0;
		while(i < ja.length() && ja.getJSONObject(i).get("id") != linea.getId()) {
			i++;
		}
		if(i == ja.length()) {
			return false;
		}
		
		JSONObject jo = ja.getJSONObject(i);
		ja.remove(i);
		if (linea.getIdProducto() != null) {
			jo.remove("producto");
			jo.put("producto", linea.getIdProducto());
		}
		if (linea.getCantidad() != null){
			jo.remove("cantidad");
			jo.put("cantidad", linea.getCantidad());
			
		}
		if (linea.getPrecio() != null) {
			jo.remove("precio");
			jo.put("precio", linea.getPrecio());
		}
		ja.put(jo);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/LineasFactura.json", false))){
			JSONObject jo2 = new JSONObject();
			jo2.put("ListaFacturas", ja);
			bw.write(jo2.toString());
			
		} 
		catch(Exception e2) {
			res = false;
		}
		
		return res;
        
    
    }*/

  
    public TLineaFactura buscarLineaFactura(String id) {
    	JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/LineasFactura.json"))){ 
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaLineasFactura");
		}
		catch(Exception e1) {
			return null;
		}
		
		int i = 0;
		while(i < ja.length() && ja.getJSONObject(i).get("id") != id) {
			i++;
		}
		if(i == ja.length()) {
			return null;
		}
		else {
			try {
				return new TLineaFactura(ja.getJSONObject(i).getString("id"), ja.getJSONObject(i).getString("id_factura"), ja.getJSONObject(i).getString("producto"), ja.getJSONObject(i).getInt("cantidad"));
			}
			catch(Exception e) {
				return null;
			}
			
		}
    }

    @Override
    public void crearLineaFactura(TLineaFactura f) {
        JSONArray ja = null;
        try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/LineasFactura.json"))){
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
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/LineasFactura.json"))){
            JSONObject jo2 = new JSONObject();
            jo2.put("ListaLineasFactura", ja);
            bw.write(jo2.toString());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
    }
    
    public ArrayList<TLineaFactura> mostrarLineasFactura() {
    	ArrayList<TLineaFactura> resultado = new ArrayList<>();
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/LineasFactura.json"))){ 
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaLineasFactura");
			
		}
		catch(Exception e1) {
			return null;
		}
		
		int i = 0;

		while(i < ja.length()) {
			
			resultado.add( new TLineaFactura(ja.getJSONObject(i).getString("id"), ja.getJSONObject(i).getString("id_factura"), ja.getJSONObject(i).getString("producto"), ja.getJSONObject(i).getInt("cantidad")));
			i++;
		}
		return resultado;
        
    }
    
    

}
