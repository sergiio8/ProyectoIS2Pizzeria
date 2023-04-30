package negocio.producto;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;

public interface SAPlato {
	String alta(JSONObject datos);
	TPlato consulta(String id);
	Collection<TPlato> consultaTodos();
	//boolean hacerPlato(String id);
	String modificar(JSONObject datos);
	Boolean borrar(String id);
	//boolean puede_hacerse(TPlato plato);
	ArrayList<String> cogerIngredientes(String plato);
	boolean disponible(String nombre, int cantidad);
	void hacerPlato(String nombre, int cantidad);
}