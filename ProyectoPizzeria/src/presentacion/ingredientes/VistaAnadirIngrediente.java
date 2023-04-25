package presentacion.ingredientes;

import java.awt.BorderLayout;

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

public class VistaAnadirIngrediente extends JDialog implements IGUI{	
	private static final long serialVersionUID = 1L;
	public VistaAnadirIngrediente(){
		initGUI();
	}
	private void initGUI() {
		setTitle("Crear ingrediente");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case ALTA_INGREDIENTE_VISTA:
			setVisible(true);
			break;
		case ALTA_INGREDIENTE_OK:
			JOptionPane.showMessageDialog(this, "Ingrediente añadido correctamente", "Ingrediente añadido correctamente", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case ALTA_INGREDIENTE_KO:
			JOptionPane.showMessageDialog(this, "ERROR: NO SE HA PODIDO AÑADIR EL INGREDIENTE", "ERROR: NO SE HA PODIDO AÑADIR EL INGREDIENTE", JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
	}
}
