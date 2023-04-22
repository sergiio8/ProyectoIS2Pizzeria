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
		String nombre ="";
		DAOIngrediente ingrediente= FactoriaAbstractaIntegracion.getInstace().crearDAOIngrediente();
		try {
			TIngrediente ing= ingrediente.coger(nuevoIngrediente.getNombre());
			if(ing!=null) {
				nombre=ingrediente.insertarIngrediente(nuevoIngrediente);
			}else {
				System.out.println("No sabes crear un ingrediente retrasado");
			}
			
			return nombre;
		}catch(Exception e) {
			System.out.println("No sabes crear un ingrediente retrasado");
			
		}
	}


	@Override
	public Collection<TIngrediente> consultaTodos() {
		DAOIngredientes ingrediente= FactoriaAbstractaIntegracion.getInstace().crearDAOIngredientes();
		return ingrediente.cogerTodosIngredientes();
	}

	@Override
	public TIngrediente consulta(String nombre) {
		DAOIngredientes ingrediente= FactoriaAbstractaIntegracion.getInstace().crearDAOIngredientes();
		return ingrediente.cogerIngrediente(nombre);
	}

}
