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
		String id = "";
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Platos.json"))){ 
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaPlatos");
			JSONObject jo = new JSONObject();
			id = tp.getId();
			jo.put("id", id);
			jo.put("tipo", tp.getTipo());
			jo.put("nombre", tp.getNombre());
			jo.put("precio", tp.getPrecio());
			/*String aux = "";
			int i;
			ArrayList<String> lista = tp.getIngredientes();
			for(i = 0;i<lista.size()-1;++i)
				aux+=lista.get(i)+", ";
			aux+=lista.get(i);*/
			jo.put("ingredientes", tp.getIngredientes());
			jo.put("descripcion", tp.getDescripcion());
			ja.put(jo);
		}
		catch(Exception e1) {
			id = "";
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Platos.json", false))){
			JSONObject jo2 = new JSONObject();
			jo2.put("ListaPlatos", ja);
			bw.write(jo2.toString());
			
		} 
		catch(Exception e2) {
			id = "";
		}		
		return id;
	}

	@Override
	public Boolean daDeBajaPlato(String id) {
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Platos.json"))){ 
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaPlatos");
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
	public TPlato obtenPlato(String id) {
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Platos.json"))){ 
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaPlatos");
		}
		catch(Exception e1) {
			return null;
		}
		int i = 0;
		while(i < ja.length() && !ja.getJSONObject(i).get("id").equals(id)) {
			i++;
		}
		if(i == ja.length()) {
			return null;
		}
		else {
			JSONObject obj = ja.getJSONObject(i);
			
			String tipo = obj.getString("tipo").toLowerCase();
			String nombre = obj.getString("nombre");
			Double precio = Double.parseDouble(obj.getString("precio"));
			ArrayList<String> ingredientes = new ArrayList<String>();
			String[] aux = obj.getString("ingredientes").trim().split(",");
			for(String s : aux)
				ingredientes.add(s.trim());
			String descripcion = obj.getString("descripcion");
			
			if(tipo.equals("entrante"))
					return new TEntrante(id,nombre,precio,ingredientes,descripcion);
			else if(tipo.equals("pizza"))
				return new TPizza(id,nombre,precio,ingredientes,descripcion);
			else if(tipo.equals("postre"))
				return new TPostre(id,nombre,precio,ingredientes,descripcion);
			else return null;
		}
	}

	@Override
	public Boolean modificaPlato(TPlato tp) {
		
		String id = tp.getId();
		
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Platos.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaPlatos");
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
		jo.put("tipo", tp.getTipo());
		jo.put("nombre", tp.getNombre());
		jo.put("precio", tp.getPrecio());
		
		/*String aux = "";
		int j;
		ArrayList<String> lista = tp.getIngredientes();
		for(j = 0;i<lista.size()-1;++j)
			aux+=lista.get(j)+", ";
		aux+=lista.get(j);*/
		
		jo.put("ingredientes", tp.getIngredientes());
		jo.put("descripcion", tp.getDescripcion());
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
			
			String id = obj.getString("id");
			String tipo = obj.getString("tipo").toLowerCase();
			String nombre = obj.getString("nombre");
			Double precio = Double.parseDouble(obj.getString("precio"));
			ArrayList<String> ingredientes = new ArrayList<String>();
			String[] aux = obj.getString("ingredientes").trim().split(",");
			for(String s : aux)
				ingredientes.add(s.trim());
			String descripcion = obj.getString("descripcion");
			
			if(tipo.equals("entrante"))
				resultado.add(new TEntrante(id,nombre,precio,ingredientes,descripcion));
			else if(tipo.equals("pizza"))
				resultado.add(new TPizza(id,nombre,precio,ingredientes,descripcion));
			else if(tipo.equals("postre"))
				resultado.add(new TPostre(id,nombre,precio,ingredientes,descripcion));
			i++;
		}
		return resultado;
	}
}
