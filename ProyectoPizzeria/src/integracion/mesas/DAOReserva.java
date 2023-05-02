package integracion.mesas;

import java.util.Collection;

import negocio.mesas.TMesas;
import negocio.mesas.TReserva;

public interface DAOReserva {
	public String insertaReserva(TReserva tr) throws IllegalArgumentException;
	public Boolean daDeBajaReserva(String id);
	public TReserva obtenReserva(String id);
	Boolean modificaReserva(TReserva tr);
	Collection<TReserva> consultaTodos();
	public Collection<TReserva> consultaTodosCliente(String id);
	public Collection<TReserva> consultaTodosMesas(Integer id);
}
