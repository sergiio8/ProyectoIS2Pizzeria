package presentacion.ingredientes;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaListarIngredientes extends JDialog implements IGUI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador ctrl;
	
	
	private JTable tbl;
	public VistaListarIngredientes(){
		
		initGUI();
	}
	private void initGUI() {
		setTitle("Listar Ingredientes");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createTitledBorder("Lista ingredientes:"));
		setContentPane(mainPanel);
		JTable tbl= new JTable(new ITableModel());
		JScrollPane scb= new JScrollPane(tbl);
		mainPanel.add(scb);
		
	
		
	
		//Pillo los data con el metodo de coger todos los ingredientes (para los platos uno la lista en un string con +)
		//String[][] data = new String[arraydeingrediestes.size()][3]
		//for de i(recorrer la lista de ingredientes) j(las tres columnas) para rellenar el data
		
		//tabla para listar
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actualizar(Evento e,Object datos) {
		switch(e) {
		case LISTAR_INGREDIENTE_VISTA:
				setVisible(true);
		}
	}
}