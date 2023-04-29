package integracion.mesas;

import java.util.Collection;

import negocio.mesas.TMesas;
import negocio.mesas.TReserva;

public interface DAOReserva {
	public Integer insertaReserva(TReserva tr) throws IllegalArgumentException;
	public Boolean daDeBajaReserva(Integer id);
	public TReserva obtenReserva(Integer id);
	Boolean modificaReserva(TReserva tr);
	Collection<TReserva> consultaTodos();
}
