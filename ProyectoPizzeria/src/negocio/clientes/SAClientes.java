package negocio.clientes;

import java.util.Collection;

import negocio.mesas.TReserva;

public interface SAClientes {
	
	public String alta(TCliente c);
	public TCliente consulta(String id);
	public Collection<TCliente> consultaTodos();
	public Boolean modificar(TCliente c);
	public Boolean borrar (String id);
	String altaReservaCliente(TReserva tr) throws IllegalArgumentException;
	

}
