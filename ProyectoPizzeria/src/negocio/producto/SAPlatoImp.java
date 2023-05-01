package negocio.producto;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.ingredientes.DAOPlatoIngrediente;
import integracion.producto.DAOPlato;
import negocio.facturas.TLineaFactura;
import negocio.ingredientes.TPlatoIngrediente;

public class SAPlatoImp implements SAPlato {

	@Override
	public String alta(JSONObject datos) {
		String nombre = "";
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		
		if(datos != null) {
			TPlato plato = (TPlato)datos.get("plato");
			if(daoPlato.obtenPlato(plato.getNombre()) == null) {
				nombre = daoPlato.insertaPlato(plato);
				DAOPlatoIngrediente daoPIng = FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
				String[] aux = datos.getString("ingredientes").trim().split(",");
				for(String s : aux) {
					daoPIng.insertarPlatoIngrediente(new TPlatoIngrediente(nombre, s.trim()));
				}
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
	public String modificar(JSONObject datos) {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		
		String nombre = daoPlato.modificaPlato((TPlato)datos.get("plato"));
		if(!datos.getString("ingredientes").trim().equals("")) {
			DAOPlatoIngrediente daoPIng = FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
			daoPIng.modificaPlato(datos);
		}
		return  nombre;
		
	}

	@Override
	public Boolean borrar(String nombre) {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		DAOPlatoIngrediente daoPIng = FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
		boolean b = daoPlato.daDeBajaPlato(nombre);
		daoPIng.daDeBajaPlato(nombre);
		return b;
	}
	
	@Override
	public String cogerIngredientes(String plato) {
		ArrayList<String> ingredientes = cogerIngredientesLista(plato);
		String ing = "";
		int i = 0;
		while(i<ingredientes.size()-1)
			ing += ingredientes.get(i++) + ", ";
		ing += ingredientes.get(i);
		return ing;
	}

	@Override
	public ArrayList<String> cogerIngredientesLista(String plato) {
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