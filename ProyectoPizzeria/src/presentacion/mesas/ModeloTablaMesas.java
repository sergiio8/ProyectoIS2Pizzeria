package presentacion.mesas;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import negocio.mesas.TMesas;

public class ModeloTablaMesas extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private String[] header = {"id", "Localizacion"};
	private ArrayList<TMesas> mesas;

	@Override
	public int getRowCount() {
		return mesas == null ? 0 : mesas.size();
		
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
			return mesas.get(rowIndex).getId();
		}
		else {
			return mesas.get(rowIndex).getLocalizacion().toString();
		}
	}
	
	public void update(Object datos) {
		mesas = new ArrayList<TMesas>((Collection<TMesas>)datos);
		fireTableStructureChanged();
	}
}
