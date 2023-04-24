package integracion.clientes;

import negocio.clientes.TCliente;

public interface DAOClientes {
	
	public String insertarCliente(TCliente cliente);
	public boolean daDeBajaCliente(String id);
	public TCliente obtenCliente(String id);
	public boolean modificaCliente(TCliente Cliente);
}
