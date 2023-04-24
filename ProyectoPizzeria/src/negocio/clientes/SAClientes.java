package negocio.clientes;

import java.util.Collection;

public interface SAClientes {
	
	public String alta(TCliente c);
	public TCliente consulta(String id);
	public Collection<TCliente> consultaTodos();
	public Boolean modificar(TCliente c);
	public Boolean borrar (String id);

}
