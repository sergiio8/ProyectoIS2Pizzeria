package integracion.ingredientes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import negocio.ingredientes.TPlatoIngrediente;
import negocio.producto.TDatosPlato;

public interface DAOPlatoIngrediente {
	List<String> daDeBajaIngrediente(String name);
	void modificaIngrediente(String nombreAntiguo, String nombreNuevo);//No se si sera necesario
	boolean insertarPlatoIngrediente(TPlatoIngrediente platoIngrediente);
	Collection<TPlatoIngrediente> cogerTodosIngredientes();
	TPlatoIngrediente cogerIngrediente(String nombre);
	boolean daDeBajaPlato (String id);
	boolean modificaPlato(TDatosPlato datos);
	TPlatoIngrediente cogerPlato(String id);
	ArrayList<String> cogerIngredientes(String plato);
	boolean disponible(String nombre, int cantidad);
	void hacerPlato(String nombre, int cantidad);
}