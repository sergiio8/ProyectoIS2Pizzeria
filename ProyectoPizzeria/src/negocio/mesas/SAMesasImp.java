package negocio.mesas;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

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
		DAOReserva daoR = FactoriaAbstractaIntegracion.getInstace().crearDAOReserva();
		Collection<TReserva> reservas = daoR.consultaTodosMesas(id);
		Iterator<TReserva> it = reservas.iterator();
		boolean exito = true;
		while(it.hasNext() && exito) {
			exito = it.next().getFecha().before(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
		if(!exito) {
			throw new IllegalArgumentException("No se puede dar de baja la mesa, existen reservas proximas");
		}
		
		DAOMesas daoMesas = FactoriaAbstractaIntegracion.getInstace().crearDAOMesas();
		boolean res = daoMesas.daDeBajaMesa(id);
		if(res) {
			it = reservas.iterator();
			while(it.hasNext()) {
				daoR.daDeBajaReserva(it.next().getId());
			}
		}
		return res;
	}

	@Override
	public String altaReserva(TReserva tr) throws IllegalArgumentException{
		String id = null;
		
		DAOMesas daoMesas = FactoriaAbstractaIntegracion.getInstace().crearDAOMesas();
		DAOClientes daoClientes = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		DAOReserva daoR = FactoriaAbstractaIntegracion.getInstace().crearDAOReserva();
		if(tr != null) {
			TMesas esta = daoMesas.obtenMesa(tr.getIdMesa());
			TCliente estaC = daoClientes.obtenCliente(tr.getIdCliente());
			if(esta == null) {
				throw new IllegalArgumentException("Mesa no existente");
			}
			if(estaC == null) {
				throw new IllegalArgumentException("Cliente no existente");
			}
			Collection<TReserva> reservas = daoR.consultaTodosMesas(tr.getIdMesa());
			Iterator<TReserva> it = reservas.iterator();
			boolean exito = true;
			while(it.hasNext() && exito) {
				exito = !it.next().getFecha().equals(tr.getFecha());
			}
			if(!exito) {
				throw new IllegalArgumentException("Ya existe una reserva para esa hora en esa mesa");
			}
			
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

			Collection<TReserva> reservas = daoR.consultaTodosMesas(tr.getIdMesa());
			Iterator<TReserva> it = reservas.iterator();
			boolean exito = true;
			while(it.hasNext() && exito) {
				exito = !it.next().getFecha().equals(tr.getFecha());
			}
			if(!exito) {
				throw new IllegalArgumentException("Ya existe una reserva para esa hora en esa mesa");
			}
			
			
			
			return daoR.modificaReserva(tr);
			
			
		}
		
		return false;
	}

	@Override
	public Boolean borrarR(String id) {
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

	@Override
	public Collection<TReserva> consultaTodosRCliente(String id) {
		DAOClientes daoClientes = FactoriaAbstractaIntegracion.getInstace().crearDAOCliente();
		TCliente estaC = daoClientes.obtenCliente(id);
		if(estaC == null) {
			throw new IllegalArgumentException("Cliente no existente");
		}
		DAOReserva daoR = FactoriaAbstractaIntegracion.getInstace().crearDAOReserva();
		return daoR.consultaTodosCliente(id);
	}

	@Override
	public Collection<TReserva> consultaTodosRMesa(Integer id) {
		DAOMesas daoMesas = FactoriaAbstractaIntegracion.getInstace().crearDAOMesas();
		TMesas estaM = daoMesas.obtenMesa(id);
		if(estaM == null) {
			throw new IllegalArgumentException("Mesa no existente");
		}
		DAOReserva daoR = FactoriaAbstractaIntegracion.getInstace().crearDAOReserva();
		return daoR.consultaTodosMesas(id);
	}

}
