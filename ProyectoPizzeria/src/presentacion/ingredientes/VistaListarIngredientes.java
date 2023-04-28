package presentacion.ingredientes;

import java.awt.BorderLayout;
import java.awt.Frame;
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
	private ITableModel modelo;
	
	
	private JTable tbl;
	public VistaListarIngredientes(Frame parent){
		
		initGUI(parent);
	}
	private void initGUI(Frame parent) {
		setTitle("Listar Ingredientes");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createTitledBorder("Lista ingredientes:"));
		setContentPane(mainPanel);
		modelo = new ITableModel();
		JTable tbl= new JTable(modelo);
		JScrollPane scb= new JScrollPane(tbl);
		mainPanel.add(scb);
		
	
		
	
		//Pillo los data con el metodo de coger todos los ingredientes (para los platos uno la lista en un string con +)
		//String[][] data = new String[arraydeingrediestes.size()][3]
		//for de i(recorrer la lista de ingredientes) j(las tres columnas) para rellenar el data
		
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	
	@Override
	public void actualizar(Evento e,Object datos) {
		switch(e) {
		case LISTAR_INGREDIENTE_VISTA:
			modelo.update(datos);
			setVisible(true);
		}
	}
}