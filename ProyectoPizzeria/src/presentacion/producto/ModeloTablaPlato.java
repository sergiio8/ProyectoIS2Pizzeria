package presentacion.producto;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import negocio.producto.TPlato;

public class ModeloTablaPlato extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private String[] header = {"Id", "Tipo", "Nombre", "Precio", "Ingredientes", "Descripcion"};
	private ArrayList<TPlato> platos;
	
	@Override
	public int getRowCount() {
		if(platos == null) {
			return 0;
		}
		else {
			return platos.size();
		}
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}
	
	@Override
	public boolean isCellEditable(int col, int row) {
		return false;
	}
	
	@Override
	public String getColumnName(int col) {
		return header[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return platos.get(rowIndex).getId();
		}
		else if (columnIndex == 1) {
			return platos.get(rowIndex).getTipo();
		}
		else if (columnIndex == 2) {
			return platos.get(rowIndex).getNombre();
		}
		else if (columnIndex == 3) {
			return platos.get(rowIndex).getPrecio();
		}
		else if (columnIndex == 4) {
			String ingredientes = "";
			for(int i = 0; i < platos.get(rowIndex).getIngredientes().size(); ++i) {
				ingredientes += platos.get(rowIndex).getIngredientes().get(i) + '\n';
			}
			return ingredientes;
		}
		else return platos.get(rowIndex).getDescripcion();
	}
	
	public void update(Object datos) {
		platos = new ArrayList<TPlato>((Collection<TPlato>)datos);
		fireTableStructureChanged();
	}

}
