package integracion.ingredientes;

import java.util.Collection;

import negocio.ingredientes.TIngrediente;
import negocio.ingredientes.TPlatoIngrediente;
import negocio.producto.TPlato;

public interface DAOPlatoIngrediente {
	void daDeBajaIngrediente(String name);
	//boolean modificaIngrediente(TIngrediente ingrediente);//No se si sera necesario
	boolean insertarPlatoIngrediente(TPlatoIngrediente platoIngrediente);
	Collection<TPlatoIngrediente> cogerTodosIngredientes();
	TPlatoIngrediente cogerIngrediente(String nombre);
	boolean daDeBajaPlato (String id);
	boolean modificaPlato(TPlato plato);
	TPlatoIngrediente cogerPlato(String id);
}
