package integracion.ingredientes;

import java.util.Collection;
import java.util.List;

import negocio.ingredientes.TPlatoIngrediente;
import negocio.producto.TPlato;

public interface DAOPlatoIngrediente {
	List<String> daDeBajaIngrediente(String name);
	void modificaIngrediente(String nombreAntiguo, String nombreNuevo);//No se si sera necesario
	boolean insertarPlatoIngrediente(TPlatoIngrediente platoIngrediente);
	Collection<TPlatoIngrediente> cogerTodosIngredientes();
	TPlatoIngrediente cogerIngrediente(String nombre);
	boolean daDeBajaPlato (String id);
	boolean modificaPlato(TPlato plato);
	TPlatoIngrediente cogerPlato(String id);
}