package negocio.clientes;

import java.util.Collection;
import java.util.Iterator;

import integracion.clientes.DAOClientes;
import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.mesas.DAOMesas;
import integracion.mesas.DAOReserva;
import negocio.mesas.TMesas;
import negocio.mesas.TReserva;

public class SAClientesImp implements SAClientes{
	
	@Override
	public String alta(TCliente c) {// si cliente ya existía devuelve null
		
		String id = null;
		DAOClientes infoCliente = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		
		if(c != null) {
			TCliente cliente = infoCliente.obtenCliente(c.getId());
			if(cliente == null) {
				id = infoCliente.insertarCliente(c);
			}
		}
		return id;
	}

	@Override
	public TCliente consulta(String id) {
		// TODO Auto-generated method stub
		DAOClientes infoCliente = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		TCliente cliente = infoCliente.obtenCliente(id);
		
		return cliente;
	}

	@Override
	public Collection<TCliente> consultaTodos() {
		// TODO Auto-generated method stub
		DAOClientes infoCliente = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		return infoCliente.consultaTodos();
	}

	@Override
	public Boolean modificar(TCliente c) {
		// TODO Auto-generated method stub
		DAOClientes infoCliente = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		return infoCliente.modificaCliente(c);
	}

	@Override
	public Boolean borrar(String id) {
		// TODO Auto-generated method stub
		DAOClientes infoCliente = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		return infoCliente.daDeBajaCliente(id);
	}

	@Override
	public String altaReservaCliente(TReserva tr) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		String id = null;
		
		DAOMesas daoMesas = FactoriaAbstractaIntegracion.getInstace().crearDAOMesas();
		DAOClientes daoClientes = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		DAOReserva daoR = FactoriaAbstractaIntegracion.getInstace().crearDAOReserva();
		if(tr != null) {
			TMesas esta = daoMesas.obtenMesa(tr.getIdMesa());
			//TCliente estaC = daoClientes.obtenCliente(tr.getIdCliente());
			if(esta == null) {
				throw new IllegalArgumentException("Mesa no existente");
			}
			/*if(estaC == null) {
				throw new IllegalArgumentException("Cliente no existente");
			}*/
			Collection<TReserva> reservas = daoR.consultaTodosMesas(tr.getIdMesa());
			Iterator<TReserva> it = reservas.iterator();
			boolean exito = true;
			while(it.hasNext() && exito) {
				exito = !it.next().getFecha().equals(tr.getFecha());
			}
			if(!exito) {
				throw new IllegalArgumentException("Ya existe una reserva para esa hora en esa mesa");
			}
			
			TCliente cliente = daoClientes.obtenCliente(tr.getIdCliente());
			if(cliente != null) {
				throw new IllegalArgumentException("El cliente con id" + tr.getIdCliente() + "ya está registrado");
			}
			
			
			id = daoR.insertaReserva(tr);
			
			
		}
		
		return id;
	}

}
