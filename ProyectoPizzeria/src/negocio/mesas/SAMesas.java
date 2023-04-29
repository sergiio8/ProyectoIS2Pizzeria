package negocio.mesas;

import java.util.Collection;

public interface SAMesas {
	
	Integer alta(TMesas tm);
	TMesas consulta(Integer id);
	Collection<TMesas> consultaTodos();
	Boolean modificar(TMesas tm);
	Boolean borrar(Integer id);
	Integer altaReserva(TReserva tr) throws IllegalArgumentException;
	Boolean modificarR(TReserva tr);
	Boolean borrarR(Integer id);
}
