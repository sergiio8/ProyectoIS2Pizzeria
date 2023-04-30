package presentacion.producto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;
import presentacion.mesas.VistaBorrarMesa;

public class EliminarPlatoVista extends JDialog implements IGUI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField nameText;
	private JButton okButton;
	private JButton cancelButton;
	
	public EliminarPlatoVista(Frame parent) {
		super(parent,true);
		initGUI(parent);
	}
	
	private void initGUI(Frame parent) {
		setTitle("Eliminar Plato");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		JPanel namePanel = new JPanel();
		
		namePanel.add(new JLabel("Nombre Plato: "));
		nameText = new JTextField(10);
		namePanel.add(nameText);
		
		contenedor.add(namePanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		okButton = new JButton("OK");
		okButton.addActionListener((e) ->{
			String nombre = nameText.getText();
			Controlador.getInstance().accion(Evento.BAJA_PLATO, nombre);
		});
		
		buttonPanel.add(okButton);
		
		cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener((e)-> setVisible(false));
		
		buttonPanel.add(cancelButton);
		
		contenedor.add(buttonPanel);
		mainPanel.add(contenedor,BorderLayout.CENTER);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case BAJA_PLATO_VISTA:
			setVisible(true);
			break;
		case BAJA_PLATO_OK:
			JOptionPane.showMessageDialog(this, "Baja Plato", "Plato borrado correctamente", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case BAJA_PLATO_KO:
			JOptionPane.showMessageDialog(this, "ERROR: NO SE HA PODIDO BORRAR EL PLATO", "ERROR: NO SE HA ENCONTRADO EL PLATO", JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
		
	}

}
