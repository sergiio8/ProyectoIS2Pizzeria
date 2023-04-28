package negocio.ingredientes;
import java.util.Collection;

import negocio.mesas.TMesas;

public interface SAIngrediente {
	public boolean borrar(String name);
	//public void tirar(int cantidad);
	//public void anyadirPlato(String plato,String nombre);
	boolean modificar (TIngrediente ingrediente);
	String crear(TIngrediente ingrediente);
	//void comprar();
	//String[] mostrar(); Esto en vd a partir del consulta todos ya lo tienes
	//boolean usar(int cantidad,String nombre);
	Collection<TIngrediente> consultaTodos();
	TIngrediente consulta(String nombre);
	//`rieba

	
	
}
