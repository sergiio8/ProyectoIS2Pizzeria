package negocio.ingredientes;

import java.util.ArrayList;
import java.util.Collection;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.ingredientes.DAOIngrediente;
import integracion.ingredientes.DAOPlatoIngrediente;
import integracion.producto.DAOPlato;

public class SAIngredienteImp implements SAIngrediente{

	@Override
	public boolean borrar(String name) {
		DAOIngrediente ingrediente=FactoriaAbstractaIntegracion.getInstace().crearDAOIngrediente();
		DAOPlatoIngrediente platoIngrediente=FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
		DAOPlato plato= FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		if(ingrediente.daDeBajaIngrediente(name).getFirst()) {
			ArrayList<TPlatoIngrediente> lista= new ArrayList<TPlatoIngrediente>(platoIngrediente.cogerTodosIngredientes());
			for(TPlatoIngrediente p:lista) {
				plato.daDeBajaPlato(p.getnombrePlato());
			}
			platoIngrediente.daDeBajaIngrediente(name);
			return true;
		}
		return false;
	}

	@Override
	public boolean modificar(TModificacionIngrediente p) {
		DAOIngrediente ingrediente= FactoriaAbstractaIntegracion.getInstace().crearDAOIngrediente();
		if(ingrediente.modificaIngrediente(p)) {
			if(!p.getNombreAntiguo().equals(p.getIngrediente().getNombre())) {
				DAOPlatoIngrediente platoIngrediente = FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
				platoIngrediente.modificaIngrediente(p.getNombreAntiguo(), p.getIngrediente().getNombre());
			}
			return true;
		}
		return false;
	}
	
	@Override
	public String crear(TIngrediente nuevoIngrediente) {//Falta usar la clase ingrediente plato para cuando creas un ingrediente
		String nombre =null;
		DAOIngrediente ingrediente= FactoriaAbstractaIntegracion.getInstace().crearDAOIngrediente();
		try {
			TIngrediente ing= ingrediente.cogerIngrediente(nuevoIngrediente.getNombre());
			if(ing==null) {
				nombre=ingrediente.insertarIngrediente(nuevoIngrediente);
			}
		}catch(Exception e) {
			
		}
		return nombre;
	}


	@Override
	public Collection<TIngrediente> consultaTodos() {
		DAOIngrediente ingrediente= FactoriaAbstractaIntegracion.getInstace().crearDAOIngrediente();
		return ingrediente.cogerTodosIngredientes();
	}

	@Override
	public TIngrediente consulta(String nombre) {
		DAOIngrediente ingrediente= FactoriaAbstractaIntegracion.getInstace().crearDAOIngrediente();
		return ingrediente.cogerIngrediente(nombre);
	}

	@Override
	public boolean crear(TPlatoIngrediente platoIngrediente) {
		DAOPlatoIngrediente a= FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
		return a.insertarPlatoIngrediente(platoIngrediente);
	}

	@Override
	public Collection<TPlatoIngrediente> consultaTodito() {
		DAOPlatoIngrediente i=FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
		return i.cogerTodosIngredientes();
	}

	@Override
	public String consultaIngredientes(String ingredientes) {
		String[] aux = ingredientes.split(",");
		for(String s : aux) {
			if(consulta(s.trim()) == null) {
				return s;
			}
		}
		return null;
	}

}