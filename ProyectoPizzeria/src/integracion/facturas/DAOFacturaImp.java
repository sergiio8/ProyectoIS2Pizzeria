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
		while(i < ja.length() && !ja.getJSONObject(i).getString("id").equals(id)) {
			i++;
		}
		if (i == ja.length()) {
			return null;
		}
		else {
			try {
				JSONObject obj =  ja.getJSONObject(i);
				DAOLineaFactura daol = FactoriaAbstractaIntegracion.getInstace().crearDAOLineaFactura();
				ArrayList<TLineaFactura> lineas = (ArrayList<TLineaFactura>) daol.mostrarLineasFactura();
				ArrayList<TLineaFactura> lineas_def = new ArrayList<>();
				
				for (int j = 0; j < lineas.size(); ++j) {
					if (lineas.get(j).getIdFactura().equals(id)) {
						lineas_def.add(lineas.get(j));
					}
				}
				TDatosVenta dt = new TDatosVenta(lineas_def, obj.getString("id_vendedor"), obj.getString("id_cliente"), obj.getString("fecha"));
				return new TFactura(obj.getString("id"), obj.getDouble("precio"), dt, obj.getBoolean("activa"));
			}
			catch(Exception e) {
				return null;
			}
			
		}
		
    }
    
    public boolean crearFactura(TFactura fact) {
        boolean res = true;
        JSONArray ja = null;
        try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Facturas.json"))){
            JSONObject jsonInput = new JSONObject (new JSONTokener(in));
            ja = jsonInput.getJSONArray("ListaFacturas");
            JSONObject jo = new JSONObject();
            jo.put("fecha", fact.getFecha());
            jo.put("id", fact.getId());
            jo.put("precio", fact.getPrecio_total());
            jo.put("id_cliente", fact.getIdCliente());
            jo.put("id_vendedor", fact.getIdVendedor());
            if (fact.getActivo()) jo.put("activa", "true");
            else jo.put("activa", "false");
            
            ja.put(jo);
        }
        catch(IOException ie) {
        	return false;
            
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
        
        return res;
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
			DAOLineaFactura daol = FactoriaAbstractaIntegracion.getInstace().crearDAOLineaFactura();
			ArrayList<TLineaFactura> lineas = (ArrayList<TLineaFactura>) daol.mostrarLineasFactura();		
			for (int j = 0; j < lineas.size(); ++j) {
				if (!lineas.get(j).getIdFactura().equals(ja.getJSONObject(i).getString("id"))) {
					lineas.remove(j);
				}
			}
			
			TDatosVenta dt = new TDatosVenta(lineas, ja.getJSONObject(i).getString("id_vendedor"), ja.getJSONObject(i).getString("id_cliente"), ja.getJSONObject(i).getString("fecha"));
			resultado.add( new TFactura(ja.getJSONObject(i).getString("id"), ja.getJSONObject(i).getDouble("precio"), dt,ja.getJSONObject(i).getBoolean("activa")));
			i++;
		}
		return resultado;
        
    }
    
    
    
    
    
    
    
    

}