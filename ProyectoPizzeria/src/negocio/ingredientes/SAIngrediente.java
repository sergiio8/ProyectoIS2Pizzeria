package negocio.ingredientes;
import java.util.ArrayList;
import java.util.Collection;


public interface SAIngrediente {
	boolean borrar(String name);
	boolean modificar (TModificacionIngrediente p);
	String crear(TIngrediente ingrediente);
	boolean crear(TPlatoIngrediente platoIngrediente);
	Collection<TPlatoIngrediente> consultaTodosPlatoIngrediente();
	Collection<TIngrediente> consultaTodos();
	TIngrediente consulta(String nombre);
	String consultaIngredientes(ArrayList<String> ingredientes);
}