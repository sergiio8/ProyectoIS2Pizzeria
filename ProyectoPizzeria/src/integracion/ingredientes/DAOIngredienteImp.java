package integracion.ingredientes;

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

import negocio.ingredientes.Pair;
import negocio.ingredientes.TIngrediente;
import negocio.ingredientes.TModificacionIngrediente;

public class DAOIngredienteImp implements DAOIngrediente{

	@Override
	public Pair<Boolean, Integer> daDeBajaIngrediente(String nombre) {
		JSONArray ja = null;
		int cantidad = -1;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Ingredientes.json"))){
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaIngredientes");
		}
		catch(Exception e1) {
			return new Pair<Boolean,Integer>(false, cantidad);
		}
		
		int i = 0;
		while(i < ja.length() && !ja.getJSONObject(i).getString("nombre").equals(nombre)) {
			i++;
		}
		if(i == ja.length()) {
			return new Pair<Boolean,Integer>(false, cantidad);
		}
		else {
			cantidad = ja.getJSONObject(i).getInt("cantidad");
			ja.remove(i);
			try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Ingredientes.json", false))){
				JSONObject jo2 = new JSONObject();
				jo2.put("ListaIngredientes", ja);
				bw.write(jo2.toString());
				
			} 
			catch(Exception e2) {
				return new Pair<Boolean,Integer>(false, cantidad);
			}
		}
		
		return new Pair<Boolean,Integer>(true, cantidad);
	}

	@Override
	public boolean modificaIngrediente(TModificacionIngrediente p) {
		Pair<Boolean,Integer> b = daDeBajaIngrediente(p.getNombreAntiguo());
		if(b.getFirst()) {
			if(p.getIngrediente().getCantidad() == -1)
				this.insertarIngrediente(new TIngrediente(p.getIngrediente().getNombre(), b.getSecond()));
			else
				this.insertarIngrediente(p.getIngrediente());
			return true;
		}
		return false;
	}

	@Override
	public String insertarIngrediente(TIngrediente ingrediente) {
		String nombre= "";
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Ingredientes.json"))){
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaIngredientes");
			JSONObject jo = new JSONObject();
			jo.put("nombre", ingrediente.getNombre());
			nombre = ingrediente.getNombre();
			jo.put("cantidad", ingrediente.getCantidad());
			ja.put(jo);
		}
		catch(Exception e1) {
			nombre="";
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Ingredientes.json", false))){
			JSONObject jo2 = new JSONObject();
			jo2.put("ListaIngredientes", ja);
			bw.write(jo2.toString());
			
		} 
		catch(Exception e2) {
			nombre="";
		}
		
		return nombre;
	}
	

	@Override
	public Collection<TIngrediente> cogerTodosIngredientes() {
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Ingredientes.json"))){
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaIngredientes");
		}
		catch(Exception e1) {
			return null;
		}
		
		Collection<TIngrediente> l = new ArrayList<TIngrediente>();
		for(int k = 0; k<ja.length(); k++) {
		    TIngrediente ingrediente = new TIngrediente(ja.getJSONObject(k).getString("nombre"), ja.getJSONObject(k).getInt("cantidad"));
		    l.add(ingrediente);
		}
		return l;
	}

	@Override
	public TIngrediente cogerIngrediente(String nombre) {
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Ingredientes.json"))){
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaIngredientes");
		}
		catch(Exception e1) {
			return null;
		}
		
		int i = 0;
		while(i < ja.length() && !ja.getJSONObject(i).getString("nombre").equals(nombre)) {
			i++;
		}
		if(i == ja.length()) {
			return null;
		}
		else {
			TIngrediente ingrediente = new TIngrediente(nombre, ja.getJSONObject(i).getInt("cantidad"));
			return ingrediente;
		}
	}

}
