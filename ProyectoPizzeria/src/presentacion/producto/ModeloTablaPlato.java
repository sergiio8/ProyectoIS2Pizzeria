package presentacion.producto;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import negocio.ingredientes.Pair;
import negocio.producto.TPlato;

public class ModeloTablaPlato extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private String[] header = {"Nombre", "Tipo", "Precio", "Ingredientes", "Descripcion"};
	private ArrayList<Pair<TPlato,ArrayList<String>>> datos;
	
	@Override
	public int getRowCount() {
		if(datos == null) {
			return 0;
		}
		else {
			return datos.size();
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
			return datos.get(rowIndex).getFirst().getNombre();
		}
		else if (columnIndex == 1) {
			return datos.get(rowIndex).getFirst().getTipo();
		}
		else if (columnIndex == 2) {
			return datos.get(rowIndex).getFirst().getPrecio();
		}
		else if (columnIndex == 3) {
			String ingredientes = "";
			ArrayList<String> ing = datos.get(rowIndex).getSecond();
			int i;
			for(i = 0; i < ing.size()-1; ++i)
				ingredientes += ing.get(i) + ", ";
			ingredientes += ing.get(i);
			return ingredientes;
		}
		else return datos.get(rowIndex).getFirst().getDescripcion();
	}
	
	public void update(Object datos) {
		this.datos = (ArrayList<Pair<TPlato,ArrayList<String>>>)datos;
		fireTableStructureChanged();
	}

}
