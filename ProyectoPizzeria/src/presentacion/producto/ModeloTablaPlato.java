package presentacion.producto;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import negocio.producto.TDatosPlato;

public class ModeloTablaPlato extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private String[] header = {"Nombre", "Tipo", "Precio", "Ingredientes", "Descripcion"};
	private ArrayList<TDatosPlato> datos;
	
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
			return datos.get(rowIndex).getPlato().getNombre();
		}
		else if (columnIndex == 1) {
			return datos.get(rowIndex).getPlato().getTipo();
		}
		else if (columnIndex == 2) {
			return datos.get(rowIndex).getPlato().getPrecio();
		}
		else if (columnIndex == 3) {
			String ingredientes = "";
			ArrayList<String> ing = datos.get(rowIndex).getIngredientes();
			if(ing.size()>0) {
				int i;
				for(i = 0; i < ing.size()-1; ++i)
					ingredientes += ing.get(i) + ", ";
				ingredientes += ing.get(i);
			}
			return ingredientes;
		}
		else return datos.get(rowIndex).getPlato().getDescripcion();
	}
	
	public void update(Object datos) {
		this.datos = (ArrayList<TDatosPlato>)datos;
		fireTableStructureChanged();
	}

}
