package integracion.clientes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.clientes.TCliente;
import negocio.mesas.TMesas;

public class DAOClientesImp implements DAOClientes{

	@Override
	public String insertarCliente(TCliente cliente) {
		// TODO Auto-generated method stub
		String id = null;
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Clientes.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaClientes");
			JSONObject jo = new JSONObject();
			id = cliente.getId();
			jo.put("id", id);
			jo.put("nombre", cliente.getNombre());
			jo.put("apellido", cliente.getApellido());
			ja.put(jo);
		}
		catch(Exception e1) {
			id = null;
			System.out.print("error en fileInput de IC");
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Clientes.json", false))){
			JSONObject jo2 = new JSONObject();
			jo2.put("ListaClientes", ja);
			bw.write(jo2.toString());
			
		} 
		catch(Exception e2) {
			id = null;
			System.out.print("error en buffered de IC");
		}
		
		return id;
	}

	@Override
	public boolean daDeBajaCliente(String id) {
		// TODO Auto-generated method stub
		
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Clientes.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaClientes");
		}
		catch(Exception e1) {
			return false;
		}
		
		int i = 0;
		while(i < ja.length() && !ja.getJSONObject(i).get("id").equals(id)) {
			i++;
		}
		if(i == ja.length()) {
			return false;
		}
		else {
			ja.remove(i);
			try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Clientes.json", false))){
				JSONObject jo2 = new JSONObject();
				jo2.put("ListaMesas", ja);
				bw.write(jo2.toString());
				
			} 
			catch(Exception e2) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public TCliente obtenCliente(String id) {
		// TODO Auto-generated method stub
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Clientes.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaClientes");
		}
		catch(Exception e1) {
			System.out.print("error en fileInput de OC");
			return null;
		}
		
		int i = 0;
		while(i < ja.length() && !ja.getJSONObject(i).getString("id").equals(id)) {
			i++;
		}
		if(i == ja.length()) {
			return null;
		}
		else {
			try {
				return new TCliente(ja.getJSONObject(i).getString("id"), ja.getJSONObject(i).getString("nombre"), ja.getJSONObject(i).getString("apellido"));
			}
			catch(Exception e) {
				return null;
			}
			
		}
	}

	@Override
	public boolean modificaCliente(TCliente cliente) {
		// TODO Auto-generated method stub
		
		String id = cliente.getId();
		
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Clientes.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaClientes");
			
		}
		catch(Exception e1) {
			return false;
		}
		
		int i = 0;
		while(i < ja.length() && !ja.getJSONObject(i).getString("id").equals(id)) {
			i++;
		}
		if(i == ja.length()) {
			return false;
		}
		
		ja.remove(i);
		JSONObject jo = new JSONObject();
		jo.put("id", id);
		jo.put("nombre", cliente.getNombre());
		jo.put("apellido", cliente.getApellido());
		ja.put(jo);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Clientes.json", false))){
			JSONObject jo2 = new JSONObject();
			jo2.put("ListaClientes", ja);
			bw.write(jo2.toString());
			
		} 
		catch(Exception e2) {
			return false;
		}
		
		return true;
	}

	@Override
	public Collection<TCliente> consultaTodos() {
		// TODO Auto-generated method stub
		Collection<TCliente> resultado = new ArrayList<TCliente>();
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Clientes.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaClientes");
			
		}
		catch(Exception e1) {
			return null;
		}
		
		int i = 0;

		while(i < ja.length()) {
			resultado.add( new TCliente(ja.getJSONObject(i).getString("id"), ja.getJSONObject(i).getString("nombre"), ja.getJSONObject(i).getString("apellido")));
			i++;
		}
		return resultado;
	}
	
	

}
