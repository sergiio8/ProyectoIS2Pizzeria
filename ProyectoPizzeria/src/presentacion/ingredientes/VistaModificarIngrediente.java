package presentacion.ingredientes;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.Evento;
import presentacion.IGUI;

public class VistaModificarIngrediente extends JDialog implements IGUI{	
	private static final long serialVersionUID = 1L;
	public VistaModificarIngrediente(Frame parent){
		initGUI(parent);
	}
	private void initGUI(Frame parent) {
		setTitle("Modificar ingrediente");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		
		
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case MODIFICAR_INGREDIENTE_VISTA:
			setVisible(true);
			break;
		case MODIFICAR_INGREDIENTE_OK:
			JOptionPane.showMessageDialog(this, "Ingrediente modificado correctamente", "Ingrediente modificado correctamente", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case MODIFICAR_INGREDIENTE_KO:
			JOptionPane.showMessageDialog(this, "ERROR: NO SE HA PODIDO MODIFICAR EL INGREDIENTE", "ERROR: NO SE HA PODIDO MODIFICAR EL INGREDIENTE", JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
	}
}
