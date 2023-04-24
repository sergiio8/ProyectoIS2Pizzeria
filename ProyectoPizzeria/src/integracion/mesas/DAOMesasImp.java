package integracion.mesas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.mesas.TMesas;

public class DAOMesasImp implements DAOMesas {

	@Override
	public Integer insertaMesa(TMesas tm) {
		int id = -1;
		JSONArray ja = null;
		try(InputStream in = new FileInputStream(new File("ProyectoPizzeria/resources/Mesas.json"))){ //idea mandar excepciones y tratarlas en controlador
			JSONObject jsonInput = new JSONObject (new JSONTokener(in));
			ja = jsonInput.getJSONArray("ListaMesas");
			JSONObject jo = new JSONObject();
			jo.put("id", tm.getId());
			jo.put("localizacion", tm.getLocalizacion().toString());
			ja.put(jo);
		}
		catch(Exception e1) {
			id = -1;
		}
		/*
		catch(IOException ie) {
			
		}
		catch(JSONException je) {
			
		}*/
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("ProyectoPizzeria/resources/Mesas.json"))){
			JSONObject jo2 = new JSONObject();
			jo2.put("ListaMesas", ja);
			bw.write(jo2.toString());
			
		} 
		catch(Exception e2) {
			id = -1;
		}
		/*catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		
		return id;
	}

	@Override
	public Boolean daDeBajaMesa(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TMesas obtenMesa(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean modificaMesa(TMesas tm) {
		// TODO Auto-generated method stub
		return null;
	}

}
