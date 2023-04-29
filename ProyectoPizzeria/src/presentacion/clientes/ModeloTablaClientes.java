package presentacion.clientes;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import negocio.clientes.TCliente;

public class ModeloTablaClientes extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private String[] header = {"Id", "Nombre", "Apellido"};
	private ArrayList<TCliente> clientes;

	@Override
	public int getRowCount() {
		return clientes == null ? 0 : clientes.size();
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
			return clientes.get(rowIndex).getId();
		}
		else if(columnIndex == 1) {
			return clientes.get(rowIndex).getNombre();
		}
		else {
			return clientes.get(rowIndex).getApellido();
		}
	}
	
	public void update(Object datos) {
		clientes = new ArrayList<TCliente>((Collection<TCliente>)datos);
		fireTableStructureChanged();
	}

}
