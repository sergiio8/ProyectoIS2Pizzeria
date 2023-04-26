package negocio.producto;

import java.util.Collection;

public interface SAPlato {
	String alta(TPlato tp);
	TPlato consulta(String id);
	Collection<TPlato> consultaTodos();
	//boolean hacerPlato(String id);
	Boolean modificar(TPlato tp);
	Boolean borrar(String id);
	//boolean puede_hacerse(TPlato plato);
}