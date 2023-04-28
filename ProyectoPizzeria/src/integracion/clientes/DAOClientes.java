package integracion.clientes;

import java.util.Collection;

import negocio.clientes.TCliente;
import negocio.mesas.TMesas;

public interface DAOClientes {
	
	public String insertarCliente(TCliente cliente);
	public boolean daDeBajaCliente(String id);
	public TCliente obtenCliente(String id);
	public boolean modificaCliente(TCliente cliente);
	Collection<TCliente> consultaTodos();
}
