package presentacion.ingredientes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.ingredientes.TIngrediente;

public class ITableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] columnNames= {"Nombre","Cantidad","Platos"};
	String[] filas;
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return filas.length;
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
		// TODO Auto-generated method stub
		switch(columnIndex) {
		case 0:
			return filas[0];
		case 1:
			return filas[1];
		case 2:
			return filas[2];
			//return filas.get(rowIndex).getPlatosToString();
			
		}
		return null;
	}
	
	public void update(Object datos) {//las filas tendran que ser un array de strings mejor
		ArrayList<TIngrediente> a = new ArrayList<TIngrediente>((Collection<TIngrediente>) datos);
		filas= new ArrayList<TIngrediente>((Collection<TIngrediente>) datos);
		this.fireTableStructureChanged();
	
}
	
}