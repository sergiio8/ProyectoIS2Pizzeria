package integracion.mesas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
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
			Calendar cal = Calendar.getInstance();
			cal.setTime(tr.getFecha());
			id = Integer.parseInt(String.valueOf(tr.getIdMesa())+ String.valueOf(cal.get(Calendar.DAY_OF_YEAR))+ String.valueOf(cal.get(Calendar.YEAR)-1900)+ String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
			jo.put("id", id);
			jo.put("idMesa", tr.getIdMesa());
			jo.put("idCliente", tr.getIdCliente());
			jo.put("fecha", tr.getFecha().toString());
			ja.put(jo);
			
		}
		catch(IOException ie) {
			throw new IllegalArgumentException("No se ha podido acceder al fichero JSON");
		}
		catch(JSONException je) {
			throw new IllegalArgumentException("Fallo al construir JSON en base de datos");
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Fallo al construir el id de la reserva");
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
	public Boolean modificaReserva(TReserva tr) {

		int id = tr.getId();
		
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Reservas.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaReservas");
			
		}
		catch(Exception e1) {
			throw new IllegalArgumentException("Fallo al acceder al fichero JSON");
		}
		
		int i = 0;
		while(i < ja.length() && ja.getJSONObject(i).getInt("id") != id) {
			i++;
		}
		if(i == ja.length()) {
			return false;
		}
		
		ja.remove(i);
		JSONObject jo = new JSONObject();
		jo.put("id", id);
		jo.put("idMesa", tr.getIdMesa());
		jo.put("idCliente", tr.getIdCliente());
		jo.put("fecha", tr.getFecha().toString());
		ja.put(jo);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Reservas.json", false))){
			JSONObject jo2 = new JSONObject();
			jo2.put("ListaMesas", ja);
			bw.write(jo2.toString());
			
		} 
		catch(Exception e2) {
			throw new IllegalArgumentException("Fallo al escribir en fichero JSON");
		}
		
		return true;
	}

	@Override
	public Collection<TReserva> consultaTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
