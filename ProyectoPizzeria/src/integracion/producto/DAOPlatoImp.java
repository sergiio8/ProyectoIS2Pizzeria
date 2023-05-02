package integracion.producto;

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

import negocio.producto.TEntrante;
import negocio.producto.TPizza;
import negocio.producto.TPlato;
import negocio.producto.TPostre;

public class DAOPlatoImp implements DAOPlato {

	@Override
	public String insertaPlato(TPlato tp) {
		String nombre = "";
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Platos.json"))){ 
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaPlatos");
			JSONObject jo = new JSONObject();
			nombre = tp.getNombre();
			jo.put("nombre", nombre);
			jo.put("tipo", tp.getTipo());
			jo.put("precio", tp.getPrecio());
			jo.put("descripcion", tp.getDescripcion());
			ja.put(jo);
		}
		catch(Exception e1) {
			nombre = "";
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Platos.json", false))){
			JSONObject jo2 = new JSONObject();
			jo2.put("ListaPlatos", ja);
			bw.write(jo2.toString());
		} 
		catch(Exception e2) {
			nombre = "";
		}		
		return nombre;
	}

	@Override
	public Boolean daDeBajaPlato(String nombre) {
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Platos.json"))){ 
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaPlatos");
		}
		catch(Exception e1) {
			return false;
		}
		int i = 0;
		while(i < ja.length() && !ja.getJSONObject(i).get("nombre").equals(nombre)) {
			i++;
		}
		if(i == ja.length()) {
			return false;
		}
		else {
			ja.remove(i);
			try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Platos.json", false))){
				JSONObject jo2 = new JSONObject();
				jo2.put("ListaPlatos", ja);
				bw.write(jo2.toString());
			} 
			catch(Exception e2) {
				return false;
			}
		}
		return true;
	}

	@Override
	public TPlato obtenPlato(String nombre) {
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Platos.json"))){ 
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaPlatos");
		}
		catch(Exception e1) {
			return null;
		}
		int i = 0;
		while(i < ja.length() && !ja.getJSONObject(i).get("nombre").equals(nombre)) {
			i++;
		}
		if(i == ja.length()) {
			return null;
		}
		else {
			JSONObject obj = ja.getJSONObject(i);
			String tipo = obj.getString("tipo").toLowerCase();
			Double precio = obj.getDouble("precio");
			String descripcion = obj.getString("descripcion");
			
			if(tipo.equals("entrante"))
					return new TEntrante(nombre,precio,descripcion);
			else if(tipo.equals("pizza"))
				return new TPizza(nombre,precio,descripcion);
			else if(tipo.equals("postre"))
				return new TPostre(nombre,precio,descripcion);
			else return null;
		}
	}

	@Override
	public boolean modificaPlato(TPlato tp) {
		
		String nombre = tp.getNombre();
		
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Platos.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaPlatos");
		}
		catch(Exception e1) {
			return false;
		}
		
		int i = 0;
		while(i < ja.length() && !ja.getJSONObject(i).getString("nombre").equals(nombre)) {
			i++;
		}
		if(i == ja.length()) {
			return false;
		}
		
		JSONObject jo = ja.getJSONObject(i);
		ja.remove(i);
		if (tp.getPrecio() != 0) {
			jo.remove("precio");
			jo.put("precio", tp.getPrecio());
		}
		if (!tp.getDescripcion().equals("")) {
			jo.remove("descripcion");
			jo.put("descripcion", tp.getDescripcion());
		}
		ja.put(jo);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Platos.json", false))){
			JSONObject jo2 = new JSONObject();
			jo2.put("ListaPlatos", ja);
			bw.write(jo2.toString());
		} 
		catch(Exception e2) {
			return false;
		}
		
		return true;
	}

	@Override
	public Collection<TPlato> obtenTodosPlatos() {
		Collection<TPlato> resultado = new ArrayList<TPlato>();
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Platos.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaPlatos");
			
		}
		catch(Exception e1) {
			return null;
		}
		
		int i = 0;
		while(i < ja.length()) {
			JSONObject obj = ja.getJSONObject(i);
			
			String nombre = obj.getString("nombre");
			String tipo = obj.getString("tipo").toLowerCase();
			Double precio = obj.getDouble("precio");
			/*
			ArrayList<String> ingredientes =  new ArrayList<String>();
			Object o = obj.get("ingredientes");
			String[] aux = o.toString().substring(1,o.toString().length()-2).trim().split(",");
			for(String s : aux)
				ingredientes.add(s.trim());
				*/
			String descripcion = obj.getString("descripcion");
			
			if(tipo.equals("entrante"))
				resultado.add(new TEntrante(nombre,precio,descripcion));
			else if(tipo.equals("pizza"))
				resultado.add(new TPizza(nombre,precio,descripcion));
			else if(tipo.equals("postre"))
				resultado.add(new TPostre(nombre,precio,descripcion));
			i++;
		}
		return resultado;
	}
}
