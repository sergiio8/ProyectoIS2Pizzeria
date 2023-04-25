package negocio.ingredientes;

import java.util.Collection;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.ingredientes.DAOIngrediente;

public class SAIngredienteImp implements SAIngrediente{

	@Override
	public boolean borrar(String name) {
		DAOIngrediente ingrediente=FactoriaAbstractaIntegracion.getInstace().crearDAOIngrediente();
		return ingrediente.daDeBajaIngrediente(name);
		
	}

	@Override
	public boolean modificar(TIngrediente ing) {
		DAOIngrediente ingrediente= FactoriaAbstractaIntegracion.getInstace().crearDAOIngrediente();
		return ingrediente.modificaIngrediente(ing);
	}

	@Override
	public String crear(TIngrediente nuevoIngrediente) {
		String nombre =null;
		DAOIngrediente ingrediente= FactoriaAbstractaIntegracion.getInstace().crearDAOIngrediente();
		try {
			TIngrediente ing= ingrediente.cogerIngrediente(nuevoIngrediente.getNombre());
			if(ing!=null) {
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

}
