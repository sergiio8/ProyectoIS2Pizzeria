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
import org.json.JSONTokener;

import integracion.factoria.FactoriaAbstractaIntegracion;

import org.json.JSONObject;
import negocio.facturas.TFactura;
import negocio.facturas.TLineaFactura;
import negocio.mesas.TMesas;
import negocio.facturas.TDatosVenta;

public class DAOFacturaImp implements DAOFactura {
    
    
    public DAOFacturaImp() {
        
        
        
        
    }
    
    
    public boolean modificarFactura(TLineaFactura l) {
    	boolean res = true;
    	
		
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Facturas.json"))){ 
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaFacturas");
			
		}
		catch(Exception e1) {
			return false;
		}
		
		int i = 0;
		while(i < ja.length() && ja.getJSONObject(i).get("id") != l.getIdFactura()) {
			i++;
		}
		if(i == ja.length()) {
			return false;
		}
		

		int j = 0;
		while (j < ja.getJSONObject(i).getJSONArray("lineas").length() && ja.getJSONObject(i).getJSONArray("lineas").getJSONObject(j).get("id") != l.getId()) {
			j++;
		}
		
		JSONObject jo = ja.getJSONObject(i).getJSONArray("lineas").getJSONObject(j);
		ja.getJSONObject(i).getJSONArray("lineas").remove(j);
		
		DAOLineaFactura daol = FactoriaAbstractaIntegracion.getInstace().crearDAOLineaFactura();
		daol.modificarLineaFactura(l);
		if (l.getIdProducto() != jo.getString("producto")) {
			jo.remove("producto");
			jo.put("producto", l.getIdProducto());
		}
		if (l.getCantidad() != jo.getDouble("cantidad")) {
			jo.remove("cantidad");
			jo.put("cantidad", l.getCantidad());
		}
        
   
        ja.getJSONObject(i).getJSONArray("lineas").put(ja.getJSONObject(i).getJSONArray("lineas").length(), jo);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Facturass.json", false))){
			JSONObject jo2 = new JSONObject();
			jo2.put("ListaFacturas", ja);
			bw.write(jo2.toString());
			
		} 
		catch(Exception e2) {
			res = false;
		}
		
		return res;
        
        
        
    }
    
    public TFactura buscarFactura(String id) {
    	JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Facturas.json"))){ 
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaFacturas");
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
				JSONObject obj =  ja.getJSONObject(i);
				ArrayList<TLineaFactura> lineas = new ArrayList();
				DAOLineaFactura daol = FactoriaAbstractaIntegracion.getInstace().crearDAOLineaFactura();
				for (int j = 0; j < obj.getJSONArray("lineas").length(); ++j) {
					lineas.add(daol.buscarLineaFactura(obj.getJSONArray("lineas").getJSONObject(j).getString("id")));
				}
				TDatosVenta dt = new TDatosVenta(lineas, obj.get("id").toString(), obj.get("id_vendedor").toString(), obj.get("id_cliente").toString(), obj.getString("fecha"));
				return new TFactura(obj.getString("id"), obj.getDouble("precio"), dt, obj.getBoolean("activa"));
			}
			catch(Exception e) {
				return null;
			}
			
		}
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
            JSONArray lines = new JSONArray();
            int i = 0;
            DAOLineaFactura daol = FactoriaAbstractaIntegracion.getInstace().crearDAOLineaFactura();
            for (TLineaFactura t : fact.getProductos()) {
                daol.crearLineaFactura(t);
                JSONObject jo3 = new JSONObject();
                jo3.put("id_factura", t.getIdFactura());
                jo3.put("id", t.getId());
                jo3.put("producto", t.getIdProducto());
                jo3.put("cantidad", t.getCantidad());
                lines.put(i, jo3);
                i++;
            }
            jo.put("lineas", lines);
            jo.put("id_cliente", fact.getIdCliente());
            jo.put("id_vendedor", fact.getIdVendedor());
            if (fact.getActivo()) jo.put("activa", "true");
            else jo.put("activa", "false");
            
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
            e1.printStackTrace();
        }
        
        return id;
    }


    @Override
    public Collection<TFactura> mostrarFacturas() {
    	Collection<TFactura> resultado = new ArrayList<TFactura>();
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Facturas.json"))){ 
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaFacturas");
			
		}
		catch(Exception e1) {
			return null;
		}
		
		int i = 0;

		while(i < ja.length()) {
			ArrayList<TLineaFactura> productos = new ArrayList<>();
			DAOLineaFactura daol = FactoriaAbstractaIntegracion.getInstace().crearDAOLineaFactura();
			for (int j = 0; j < ja.getJSONObject(i).getJSONArray("lineas").length(); ++j){
				TLineaFactura l = daol.buscarLineaFactura(ja.getJSONObject(i).getJSONArray("lineas").getJSONObject(j).getString("id"));
				productos.add(l);
			}
			TDatosVenta dt = new TDatosVenta(productos, ja.getJSONObject(i).getString("id"), ja.getJSONObject(i).getString("id_vendedor"), ja.getJSONObject(i).getString("id_cliente"), ja.getJSONObject(i).getString("fecha"));
			
			resultado.add( new TFactura(ja.getJSONObject(i).getString("id"), ja.getJSONObject(i).getDouble("precio"), dt,ja.getJSONObject(i).getBoolean("activo")));
			i++;
		}
		return resultado;
        
    }
    
    
    
    
    
    
    
    

}