package negocio.mesas;

import java.util.Collection;

public interface SAMesas {
	
	Integer alta(TMesas tm);
	TMesas consulta(Integer id);
	Collection<TMesas> consultaTodos();
	Boolean modificar(TMesas tm);
	Boolean borrar(Integer id);
	String altaReserva(TReserva tr) throws IllegalArgumentException;
	Boolean modificarR(TReserva tr);
	Boolean borrarR(String id);
	Collection<TReserva> consultaTodosR();
	Collection<TReserva> consultaTodosRCliente(String id);
	Collection<TReserva> consultaTodosRMesa(Integer id);
}
