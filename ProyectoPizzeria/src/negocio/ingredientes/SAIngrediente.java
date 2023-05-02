package negocio.ingredientes;
import java.util.ArrayList;
import java.util.Collection;

import negocio.producto.TPlato;


public interface SAIngrediente {
	boolean borrar(String name);
	boolean modificar (TModificacionIngrediente p);
	String crear(TIngrediente ingrediente);
	boolean crear(TPlatoIngrediente platoIngrediente);
	Collection<TPlatoIngrediente> consultaTodosPlatoIngrediente();
	Collection<TIngrediente> consultaTodos();
	TIngrediente consulta(String nombre);
	ArrayList<String> cogerPlatos(String nombre);
	String consultaIngredientes(ArrayList<String> ingredientes);
}