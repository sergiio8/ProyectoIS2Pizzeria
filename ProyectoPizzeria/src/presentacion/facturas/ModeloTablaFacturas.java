package presentacion.facturas;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import negocio.facturas.TFactura;
import negocio.mesas.TMesas;

public class ModeloTablaFacturas extends AbstractTableModel{
private static final long serialVersionUID = 1L;
	
	private String[] header = {"ID", "ID_cliente", "ID_vendedor", "Precio total", "Fecha"};
	private ArrayList<TFactura> facturas;

	@Override
	public int getRowCount() {
		return facturas == null ? 0 : facturas.size();
	}

	@Override
	public int getColumnCount() {
		
		return header.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return header[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return facturas.get(rowIndex).getId();
		}
		else if(columnIndex == 1){
			return facturas.get(rowIndex).getIdCliente();
		}
		else if (columnIndex == 2) {
			return facturas.get(rowIndex).getIdVendedor();
		}
		else if (columnIndex == 3) {
			return facturas.get(rowIndex).getPrecio_total();
		}
		else{
			return  facturas.get(rowIndex).getFecha();
			
		}
	}
	
	public void update(Object datos) {
		facturas = new ArrayList<TFactura>((Collection<TFactura>)datos);
		fireTableStructureChanged();
	}

}
