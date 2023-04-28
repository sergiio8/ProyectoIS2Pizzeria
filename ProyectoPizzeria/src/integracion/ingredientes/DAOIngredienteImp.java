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

public class DAOIngredienteImp implements DAOIngrediente{

	@Override
	public Pair<Boolean, Integer> daDeBajaIngrediente(String nombre) {
		JSONArray ja = null;
		int cantidad = -1;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Ingredientes.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaIngredientes");
		}
		catch(Exception e1) {
			return new Pair<Boolean,Integer>(false, cantidad);
		}
		/*
		catch(IOException ie) {
			
		}
		catch(JSONException je) {
			
		}*/
		
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
		
		
		/*catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		
		return new Pair<Boolean,Integer>(true, cantidad);
	}

	@Override
	public boolean modificaIngrediente(Pair<String,TIngrediente> p) {
		Pair<Boolean,Integer> b = daDeBajaIngrediente(p.getFirst());
		if(b.getFirst()) {
			if(p.getSecond().getCantidad() == -1)
				this.insertarIngrediente(new TIngrediente(p.getSecond().getNombre(), b.getSecond()));
			else
				this.insertarIngrediente(p.getSecond());
			return true;
		}
		return false;
	}

	@Override
	public String insertarIngrediente(TIngrediente ingrediente) {
		String nombre= "";
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Ingredientes.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaIngredientes");
			JSONObject jo = new JSONObject();
			jo.put("nombre", ingrediente.getNombre());
			nombre = ingrediente.getNombre();
			jo.put("cantidad", ingrediente.getCantidad());
			//jo.put("platos", ingrediente.getPlatos());
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
		/*catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		
		return nombre;
	}
	

	@Override
	public Collection<TIngrediente> cogerTodosIngredientes() {
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Ingredientes.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaIngredientes");
		}
		catch(Exception e1) {
			return null;
		}
		/*
		catch(IOException ie) {
			
		}
		catch(JSONException je) {
			
		}*/
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
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Ingredientes.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaIngredientes");
		}
		catch(Exception e1) {
			return null;
		}
		/*
		catch(IOException ie) {
			
		}
		catch(JSONException je) {
			
		}*/
		
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
