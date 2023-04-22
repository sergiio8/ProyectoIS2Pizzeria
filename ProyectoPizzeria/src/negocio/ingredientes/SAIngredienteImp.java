package negocio.ingredientes;


import java.util.Collection;

import negocio.mesas.TMesas;

public interface SAIngredienteImp {
	public void borrar(String name);
	//public void tirar(int cantidad);
	//public void anyadirPlato(String plato,String nombre);
	Boolean modificar (TIngrediente ingrediente);
	void crear(TIngrediente ingrediente);
	//void comprar();
	String[] mostrar();
	//boolean usar(int cantidad,String nombre);
	Collection<TIngrediente> consultaTodos();
	

	
	
}
