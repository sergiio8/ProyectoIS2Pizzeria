package integracion.ingredientes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.ingredientes.TIngrediente;

public class DAOIngredienteImp implements DAOIngrediente{

	@Override
	public boolean daDeBajaIngrediente(String nombre) {
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Ingredientes.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaIngredientes");
		}
		catch(Exception e1) {
			return false;
		}
		/*
		catch(IOException ie) {
			
		}
		catch(JSONException je) {
			
		}*/
		
		int i = 0;
		while(i < ja.length() && ja.getJSONObject(i).getString("nombre") != nombre) {
			i++;
		}
		if(i == ja.length()) {
			return false;
		}
		else {
			ja.remove(i);
			try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Ingredientes.json", false))){
				JSONObject jo2 = new JSONObject();
				jo2.put("ListaIngredientes", ja);
				bw.write(jo2.toString());
				
			} 
			catch(Exception e2) {
				return false;
			}
		}
		
		
		/*catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		
		return true;
	}

	@Override
	public boolean modificaIngrediente(TIngrediente ingrediente) {
		if(this.daDeBajaIngrediente(ingrediente.getNombre())) {
			this.insertarIngrediente(ingrediente);
			return true;
		}
		return false;
	}

	@Override
	public TIngrediente coger(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insertarIngrediente(TIngrediente ingrediente) {
		String nombre= "";
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Ingredientes.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("Ingrediente");
			JSONObject jo = new JSONObject();
			jo.put("nombre", ingrediente.getNombre());
			nombre = ingrediente.getNombre();
			jo.put("cantidad", ingrediente.getCantidad());
			String platos="{";
			for(int i=0; i<platos.length()-1;i++) {
				platos+=ingrediente.getPlatos()[i];
				platos+=",";
			}
			if(platos.length()>0) {
				platos+=ingrediente.getPlatos()[platos.length()-1];
			}
			
			platos+="}";
			jo.put("platos", ingrediente.getPlatos());
			ja.put(jo);
		}
		catch(Exception e1) {
			nombre="";
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Mesas.json", false))){
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TIngrediente cogerIngrediente(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}
