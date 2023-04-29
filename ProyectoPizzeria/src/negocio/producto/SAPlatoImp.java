package negocio.producto;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.ingredientes.DAOPlatoIngrediente;
import integracion.producto.DAOPlato;
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
	public Boolean modificar(TPlato tp) {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		return daoPlato.modificaPlato(tp);
	}

	@Override
	public Boolean borrar(String nombre) {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		return daoPlato.daDeBajaPlato(nombre);
	}

	@Override
	public ArrayList<String> cogerIngredientes(String plato) {
		DAOPlatoIngrediente daoPlatoIngrediente = FactoriaAbstractaIntegracion.getInstace().crearDAOPlatoIngrediente();
		return daoPlatoIngrediente.cogerIngredientes(plato);
	}



	
	/*public boolean puede_hacerse(TPlato plato) {
		DAOIngrediente daoi = FactoriaAbstractaIntegracion.getInstace().crearDAOIngrediente();
		boolean se_puede = true;
		for (int i = 0; i < plato.getIngredientes().size() && se_puede; ++i) {
			TIngrediente ti = daoi.cogerIngrediente(plato.getIngredientes().get(i).getNombre());
			if (ti.getCantidad() == 0) se_puede = false;
		}
		
		return se_puede;
		
	}
	public boolean hacerPlato(String id) {
		DAOPlato daoPlato = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
		TPlato plato = daoPlato.obtenPlato(id);
		DAOIngrediente daoi = FactoriaAbstractaIntegracion.getInstace().crearDAOIngrediente();
		boolean se_puede = puede_hacerse(plato);
		if (se_puede) {
			for (int i = 0; i < plato.getIngredientes().size() && se_puede; ++i) {
				TIngrediente ti = daoi.cogerIngrediente(plato.getIngredientes().get(i).getNombre());
				ti.setCantidad(ti.getCantidad() - 1);
			}
		}
		
		return se_puede;
	}*/

}