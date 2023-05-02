package presentacion.ingredientes;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import presentacion.Evento;
import presentacion.IGUI;

public class VistaListarIngredientes extends JDialog implements IGUI{
	private static final long serialVersionUID = 1L;
	
	private ITableModel modelo;

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
		JScrollPane scb= new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(scb);
		
		pack();
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