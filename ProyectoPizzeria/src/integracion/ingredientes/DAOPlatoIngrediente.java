package integracion.ingredientes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import negocio.ingredientes.TPlatoIngrediente;
import negocio.producto.TDatosPlato;
import negocio.producto.TPlato;

public interface DAOPlatoIngrediente {
	List<String> daDeBajaIngrediente(String name);
	void modificaIngrediente(String nombreAntiguo, String nombreNuevo);
	boolean insertarPlatoIngrediente(TPlatoIngrediente platoIngrediente);
	Collection<TPlatoIngrediente> cogerTodosIngredientes();
	TPlatoIngrediente cogerIngrediente(String nombre);
	boolean daDeBajaPlato (String id);
	boolean modificaPlato(TDatosPlato datos);
	ArrayList<String> cogerIngredientes(String plato);
	ArrayList<String> cogerPlatos(String ingrediente);
	boolean disponible(String nombre, int cantidad);
	void hacerPlato(String nombre, int cantidad);
}