package integracion.mesas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.mesas.TMesas;
import negocio.mesas.TReserva;

public class DAOReservaImp implements DAOReserva{

	

	@Override
	public Integer insertaReserva(TReserva tr) throws IllegalArgumentException{
		int id = -1;
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Reservas.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaReservas");
			JSONObject jo = new JSONObject();
			jo.put("idMesa", tr.getIdMesa());
			jo.put("idCliente", tr.getIdCliente());
			jo.put("fecha", tr.getFecha().toString());
			ja.put(jo);
			id = ja.length();
		}
		catch(IOException ie) {
			throw new IllegalArgumentException("No se ha podido acceder al fichero JSON");
		}
		catch(JSONException je) {
			throw new IllegalArgumentException("Fallo al construir JSON en base de datos");
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Reservas.json", false))){
			JSONObject jo2 = new JSONObject();
			jo2.put("ListaReservas", ja);
			bw.write(jo2.toString());
			
		} 
		
		catch (IOException e1) {
			throw new IllegalArgumentException("No se ha podido escribir al fichero JSON");
		}
		
		
		
		return id;
	}

	@Override
	public Boolean daDeBajaReserva(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TReserva obtenReserva(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean modificaReserva(TMesas tr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<TReserva> consultaTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
