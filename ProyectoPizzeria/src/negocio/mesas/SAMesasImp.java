package negocio.mesas;

import java.util.Collection;

import integracion.clientes.DAOClientes;
import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.mesas.DAOMesas;
import integracion.mesas.DAOReserva;
import negocio.clientes.TCliente;

public class SAMesasImp implements SAMesas{

	@Override
	public Integer alta(TMesas tm) {
		Integer id =-1;
		
		DAOMesas daoMesas = FactoriaAbstractaIntegracion.getInstace().crearDAOMesas();
		
		if(tm != null) {
			TMesas esta = daoMesas.obtenMesa(tm.getId());
			if(esta == null) {
				id = daoMesas.insertaMesa(tm);
			}
		}
		
		return id;
	}

	@Override
	public TMesas consulta(Integer id) {
		DAOMesas daoMesas = FactoriaAbstractaIntegracion.getInstace().crearDAOMesas();
		return daoMesas.obtenMesa(id);
	}

	@Override
	public Collection<TMesas> consultaTodos() {
		DAOMesas daoMesas = FactoriaAbstractaIntegracion.getInstace().crearDAOMesas();
		return daoMesas.consultaTodos();
	}

	@Override
	public Boolean modificar(TMesas tm) {
		if(tm != null) {
			DAOMesas daoMesas = FactoriaAbstractaIntegracion.getInstace().crearDAOMesas();
			return daoMesas.modificaMesa(tm);
		}
		else {
			return false;
		}
		
		
		
	}

	@Override
	public Boolean borrar(Integer id) {
		DAOMesas daoMesas = FactoriaAbstractaIntegracion.getInstace().crearDAOMesas();
		return daoMesas.daDeBajaMesa(id);
	}

	@Override
	public Integer altaReserva(TReserva tr) throws IllegalArgumentException{
		Integer id =-1;
		
		DAOMesas daoMesas = FactoriaAbstractaIntegracion.getInstace().crearDAOMesas();
		DAOClientes daoClientes = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		
		if(tr != null) {
			TMesas esta = daoMesas.obtenMesa(tr.getIdMesa());
			//TCliente estaC = daoClientes.obtenCliente(tr.getIdCliente());
			if(esta == null) {
				throw new IllegalArgumentException("Mesa no existente");
			}
			/*if(estaC == null) {
				throw new IllegalArgumentException("Cliente no existente");
			}*/
			DAOReserva daoR = FactoriaAbstractaIntegracion.getInstace().crearDAOReserva();
			
			id = daoR.insertaReserva(tr);
			
			
		}
		
		return id;
	}

	@Override
	public Boolean modificarR(TReserva tr) {
		
		DAOMesas daoMesas = FactoriaAbstractaIntegracion.getInstace().crearDAOMesas();
		DAOClientes daoClientes = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		DAOReserva daoR = FactoriaAbstractaIntegracion.getInstace().crearDAOReserva();
		if(tr != null) {
			TMesas esta = daoMesas.obtenMesa(tr.getIdMesa());
			TCliente estaC = daoClientes.obtenCliente(tr.getIdCliente());
			TReserva estaR = daoR.obtenReserva(tr.getId());
			if(esta == null) {
				throw new IllegalArgumentException("Mesa no existente");
			}
			if(estaC == null) {
				throw new IllegalArgumentException("Cliente no existente");
			}
			if(estaR == null) {
				throw new IllegalArgumentException("Reserva no existente");
			}
			
			
			return daoR.modificaReserva(tr);
			
			
		}
		
		return false;
	}

	@Override
	public Boolean borrarR(Integer id) {
		DAOReserva daoR = FactoriaAbstractaIntegracion.getInstace().crearDAOReserva();
		
		TReserva estaR = daoR.obtenReserva(id);
			
		if(estaR == null) {
			throw new IllegalArgumentException("Reserva no existente");
		}
			
			
		return daoR.daDeBajaReserva(id);
			
			
		
	}

	@Override
	public Collection<TReserva> consultaTodosR() {
		DAOReserva daoR = FactoriaAbstractaIntegracion.getInstace().crearDAOReserva();
		return daoR.consultaTodos();
	}

}
