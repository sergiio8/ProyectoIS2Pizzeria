package negocio.producto;

import java.util.ArrayList;
import java.util.Collection;

import negocio.facturas.TLineaFactura;

public interface SAPlato {
	String alta(TDatosPlato datos);
	boolean borrar(String id);
	boolean modificar(TDatosPlato datos);
	TPlato consulta(String id);
	Collection<TPlato> consultaTodos();
	ArrayList<TDatosPlato> listarPlatos();
	ArrayList<String> cogerIngredientes(String plato);
	boolean disponible(String nombre, int cantidad);
	String comprobarDisponibilidad(ArrayList<TLineaFactura> productos);
	void hacerPlato(String nombre, int cantidad);
	void hacerPedido(ArrayList<TLineaFactura> lineas);
}