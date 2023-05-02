package negocio.producto;

import java.util.ArrayList;
import java.util.Collection;

import negocio.facturas.TLineaFactura;

public interface SAPlato {
	String alta(TDatosPlato datos);
	TPlato consulta(String id);
	Collection<TPlato> consultaTodos();
	boolean modificar(TDatosPlato datos);
	boolean borrar(String id);
	ArrayList<String> cogerIngredientes(String plato);
	boolean disponible(String nombre, int cantidad);
	void hacerPlato(String nombre, int cantidad);
	public String comprobarDisponibilidad(ArrayList<TLineaFactura> productos);
	void hacerPedido(ArrayList<TLineaFactura> lineas);
}