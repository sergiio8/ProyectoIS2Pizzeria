package presentacion.ingredientes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.json.JSONObject;

import negocio.ingredientes.Pair;
import negocio.ingredientes.TIngrediente;
import negocio.ingredientes.TPlatoIngrediente;

public class ITableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] columnNames= {"Nombre","Cantidad","Platos"};
	List<List<String>> datos;
	
	public ITableModel() {
		datos = new ArrayList<>();
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return datos.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.length;
	}

	@Override
	public String getColumnName(int index) {
		return columnNames[index];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return datos.get(rowIndex).get(columnIndex);
	}
	
	public void update(Object datos) {
		Pair pair = (Pair)datos;
		List<TIngrediente> ingredientes = (List<TIngrediente>) pair.getFirst();
		List<TPlatoIngrediente> platoIngredientes = (List<TPlatoIngrediente>) pair.getSecond();
		for(TIngrediente ing : ingredientes) {
			List<String> aux = new ArrayList<String>();
			aux.add(ing.getNombre());
			aux.add(String.valueOf(ing.getCantidad()));
			aux.add(idsPlatos(platoIngredientes, ing.getNombre()));
			this.datos.add(aux);
		}
		this.fireTableStructureChanged();
	}
	
	private String idsPlatos(List<TPlatoIngrediente> platoIngredientes, String nombre) {
		String s = "{ ";
		for(TPlatoIngrediente pi : platoIngredientes) {
			if(pi.getnombreIngrediente().equals(nombre)) s+=pi.getidPlato()+", ";
		}
		if(s.length() > 2)
			s = s.substring(0, s.length()-2);
		s+=" }";
		return s;
	}
	
}