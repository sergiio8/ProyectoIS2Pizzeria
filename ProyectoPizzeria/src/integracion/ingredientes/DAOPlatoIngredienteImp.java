package integracion.ingredientes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.ingredientes.TIngrediente;
import negocio.ingredientes.TPlatoIngrediente;
import negocio.producto.TPlato;

public class DAOPlatoIngredienteImp implements DAOPlatoIngrediente {

	@Override
	public List<String> daDeBajaIngrediente(String name) {
		List<String> s = new ArrayList<String>();
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/PlatoIngrediente.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaPlatoIngrediente");
		}
		catch(Exception e1) {
			
		}
		/*
		catch(IOException ie) {
			
		}
		catch(JSONException je) {
			
		}*/
		

		for(int i=0; i<ja.length();i++) {
			if(ja.getJSONObject(i).getString("nombreIngrediente").equals(name)) {
				s.add(ja.getJSONObject(i).getString("idPlato"));
				ja.remove(i);
				i--;
			}
		}
		
	
			
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/PlatoIngrediente.json", false))){
			JSONObject jo2 = new JSONObject();
			jo2.put("ListaPlatoIngrediente", ja);
			bw.write(jo2.toString());
				
		} 
		catch(Exception e2) {
				
		}
		
		
		
		return s;

	}

	@Override
	public void modificaIngrediente(String nombreAntiguo, String nombreNuevo) {
		List<String> ids = daDeBajaIngrediente(nombreAntiguo);
		if(ids.size()!=0) {
			for(String id: ids)
				insertarPlatoIngrediente(new TPlatoIngrediente(id, nombreNuevo));
		}
	}

	@Override
	public boolean insertarPlatoIngrediente(TPlatoIngrediente platoIngrediente) {
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/PlatoIngrediente.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaPlatoIngrediente");
			JSONObject jo = new JSONObject();
			//NO SE COMO BUSCAR SI ESTABA YA PREVIAMENTE O NO
			jo.put("idPlato", platoIngrediente.getidPlato());
			jo.put("nombreIngrediente", platoIngrediente.getnombreIngrediente());
			ja.put(jo);
		}
		catch(Exception e1) {
			return false;
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/PlatoIngrediente.json", false))){
			JSONObject jo2 = new JSONObject();
			jo2.put("ListaPlatoIngrediente", ja);
			bw.write(jo2.toString());
		} 
		catch(Exception e2) {
			return false;
		}
		/*catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		
		return true;
		
	}

	@Override
	public Collection<TPlatoIngrediente> cogerTodosIngredientes() {
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/PlatoIngrediente.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaPlatoIngrediente");
		}
		catch(Exception e1) {
			return null;
		}
		
		Collection<TPlatoIngrediente> l = new ArrayList<TPlatoIngrediente>();
		for(int k = 0; k<ja.length(); k++) {
		    TPlatoIngrediente ingrediente = new TPlatoIngrediente(ja.getJSONObject(k).getString("idPlato"), ja.getJSONObject(k).getString("nombreIngrediente"));
		    l.add(ingrediente);
		}
		return l;
	}

	@Override
	public TPlatoIngrediente cogerIngrediente(String nombre) {
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/PlatoIngrediente.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaPlatoIngrediente");
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
		while(i < ja.length() && !ja.getJSONObject(i).getString("nombreIngrediente").equals(nombre)) {
			i++;
		}
		if(i == ja.length()) {
			return null;
		}
		else {
			TPlatoIngrediente platoIngrediente = new TPlatoIngrediente( ja.getJSONObject(i).getString("idPlato"),ja.getJSONObject(i).getString("nombreIngrediente") );
			return platoIngrediente;
		}
		
	}

	@Override//Lo tiene que hacer el subsistema producto
	public boolean daDeBajaPlato(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override//Lo tiene que hacer el subsistema producto
	public boolean modificaPlato(TPlato plato) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override//LO teinee que hacer el subsistema producto
	public TPlatoIngrediente cogerPlato(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
