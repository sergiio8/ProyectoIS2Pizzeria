package negocio.producto;

import java.util.ArrayList;
import java.util.Collection;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.ingredientes.DAOPlatoIngrediente;
import integracion.producto.DAOPlato;
import negocio.facturas.TLineaFactura;
import negocio.ingredientes.TPlatoIngrediente;

public class SAPlatoImp implements SAPlato {

	@Override
	public String alta(TDatosPlato datos) {
		String nombre = "";
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		TPlato plato = datos.getPlato();
		if(daoPlato.obtenPlato(plato.getNombre()) == null) {
			nombre = daoPlato.insertaPlato(plato);
			DAOPlatoIngrediente daoPIng = FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
			for(String s : datos.getIngredientes()) {
				daoPIng.insertarPlatoIngrediente(new TPlatoIngrediente(nombre, s));
			}
		}
		return nombre;
	}

	@Override
	public TPlato consulta(String nombre) {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		return daoPlato.obtenPlato(nombre);
	}

	@Override
	public Collection<TPlato> consultaTodos() {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		return daoPlato.obtenTodosPlatos();
	}

	@Override
	public boolean modificar(TDatosPlato datos) {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		if(daoPlato.modificaPlato(datos.getPlato())) {
			if(!datos.getIngredientes().isEmpty()) {
				DAOPlatoIngrediente daoPIng = FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
				if(!daoPIng.modificaPlato(datos))
					return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean borrar(String nombre) {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		DAOPlatoIngrediente daoPIng = FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
		boolean b = daoPlato.daDeBajaPlato(nombre);
		daoPIng.daDeBajaPlato(nombre);
		return b;
	}

	@Override
	public ArrayList<String> cogerIngredientes(String plato) {
		DAOPlatoIngrediente daoPlatoIngrediente = FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
		return daoPlatoIngrediente.cogerIngredientes(plato);
	}

	@Override
	public boolean disponible(String nombre, int cantidad) {
		DAOPlatoIngrediente daoPIng = FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
		return daoPIng.disponible(nombre,cantidad);
	}

	@Override
	public void hacerPedido(ArrayList<TLineaFactura> lineas) {
		for (TLineaFactura linea : lineas) {
			hacerPlato(linea.getIdProducto(), linea.getCantidad());
		}
	}
	public void hacerPlato(String nombre, int cantidad) {
		DAOPlatoIngrediente daoPIng = FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
		daoPIng.hacerPlato(nombre,cantidad);
		
	}
	
	public String comprobarDisponibilidad(ArrayList<TLineaFactura> productos) {
		for (TLineaFactura linea : productos) {
			if (!disponible(linea.getIdProducto(), linea.getCantidad())) {
				return "El producto " + linea.getIdProducto() + " no está disponible";
			}
			else if (consulta(linea.getIdProducto()) == null) {
				return "El producto " + linea.getIdProducto() + " no existe";
			}
		}
		return null;
	}
}