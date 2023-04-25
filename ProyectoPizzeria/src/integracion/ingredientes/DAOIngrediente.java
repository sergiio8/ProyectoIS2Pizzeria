package integracion.ingredientes;

import java.util.Collection;

import negocio.ingredientes.TIngrediente;

public interface DAOIngrediente {
	boolean daDeBajaIngrediente(String name);
	boolean modificaIngrediente(TIngrediente ingrediente);
	String insertarIngrediente(TIngrediente ingrediente);
	Collection<TIngrediente> cogerTodosIngredientes();
	TIngrediente cogerIngrediente(String nombre);
}
