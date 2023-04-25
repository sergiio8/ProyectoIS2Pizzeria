package presentacion.ingredientes;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentacion.controlador.Controlador;

public class VistaListar extends JDialog{
	
	private Controlador ctrl;
	private String[] columnNames= {"Nombre","Cantidad","Platos"};
	
	private JTable tbl;
	VistaListar(Controlador ctrl){
		this.ctrl = ctrl;
		initGUI();
	}
	private void initGUI() {
		setTitle("Listar Ingredientes");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		//Pillo los data con el metodo de coger todos los ingredientes (para los platos uno la lista en un string con +)
		//String[][] data = new String[arraydeingrediestes.size()][3]
		//for de i(recorrer la lista de ingredientes) j(las tres columnas) para rellenar el data
		
		//tabla para listar
		
		pack();
		setResizable(false);
		setVisible(true);
	}
}
