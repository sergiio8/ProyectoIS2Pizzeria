package negocio.ingredientes;
import java.util.Collection;


public interface SAIngrediente {
	public boolean borrar(String name);
	//public void tirar(int cantidad);
	//public void anyadirPlato(String plato,String nombre);
	boolean modificar (Pair<String,TIngrediente> p);
	String crear(TIngrediente ingrediente);
	boolean crear(TPlatoIngrediente platoIngrediente);
	Collection<TPlatoIngrediente> consultaTodito();
	//void comprar();
	//String[] mostrar(); Esto en vd a partir del consulta todos ya lo tienes
	//boolean usar(int cantidad,String nombre);
	Collection<TIngrediente> consultaTodos();
	TIngrediente consulta(String nombre);
	//`rieba

	
	
}