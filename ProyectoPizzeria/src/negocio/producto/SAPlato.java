package negocio.producto;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;

import negocio.facturas.TLineaFactura;

public interface SAPlato {
	String alta(JSONObject datos);
	TPlato consulta(String id);
	Collection<TPlato> consultaTodos();
	String modificar(JSONObject datos);
	Boolean borrar(String id);
	String cogerIngredientes(String plato);
	ArrayList<String> cogerIngredientesLista(String nombre);
	boolean disponible(String nombre, int cantidad);
	void hacerPlato(String nombre, int cantidad);
	public String comprobarDisponibilidad(ArrayList<TLineaFactura> productos);
	void hacerPedido(ArrayList<TLineaFactura> lineas);
}