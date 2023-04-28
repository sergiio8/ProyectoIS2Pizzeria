package integracion.ingredientes;

import java.util.Collection;

import negocio.ingredientes.Pair;
import negocio.ingredientes.TIngrediente;

public interface DAOIngrediente {
	Pair<Boolean, Integer> daDeBajaIngrediente(String name);
	boolean modificaIngrediente(Pair<String,TIngrediente> p);
	String insertarIngrediente(TIngrediente ingrediente);
	Collection<TIngrediente> cogerTodosIngredientes();
	TIngrediente cogerIngrediente(String nombre);
}
