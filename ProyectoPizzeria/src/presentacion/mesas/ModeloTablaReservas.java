package presentacion.mesas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import negocio.mesas.TReserva;

public class ModeloTablaReservas extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private String[] header = {"id", "idMesa", "idCliente", "Fecha"};
	private ArrayList<TReserva> reservas;

	@Override
	public int getRowCount() {
		return reservas == null ? 0 : reservas.size();
		
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
		switch(columnIndex) {
		case 0:
			return reservas.get(rowIndex).getId();
		
		case 1:
			return reservas.get(rowIndex).getIdMesa();
		case 2:
			return reservas.get(rowIndex).getIdCliente();
		case 3:
			return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(reservas.get(rowIndex).getFecha()).toString(); //provisional
		default:
			return null;
		}
	}
	
	public void update(Object datos) {
		reservas = new ArrayList<TReserva>((Collection<TReserva>)datos);
		fireTableStructureChanged();
	}
}
