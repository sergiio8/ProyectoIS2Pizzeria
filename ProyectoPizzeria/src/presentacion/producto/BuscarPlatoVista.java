package presentacion.producto;

import java.awt.BorderLayout;
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

public class BuscarPlatoVista extends JDialog implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public BuscarPlatoVista(Frame parent) {
		super(parent, true);
		initGUI(parent);
	}
	
	private void initGUI(Frame parent) {
		setTitle("Buscar plato");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		//ID
		JPanel idPanel = new JPanel();
		JLabel idLabel = new JLabel("ID_plato: ");
		JTextField idText = new JTextField(10);
		
		idPanel.add(idLabel);
		idPanel.add(idText);
		
		contenedor.add(idPanel);
		
		//Botones
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
				
		JButton okButton = new JButton("OK");
		okButton.addActionListener((e) ->{
			String id;
			try {
				id = idText.getText();
				if(id == null) {
					throw new IllegalArgumentException();
				}
				
				Controlador.getInstance().accion(Evento.BUSCAR_PLATO, id);
				
			}
			catch(IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(BuscarPlatoVista.this, "ERROR: Rellene el campo relativo al id", "ERROR: Rellene el campo relativo al id", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener((e)-> setVisible(false));
		
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		
		contenedor.add(buttonsPanel);
		
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case BUSCAR_PLATO_VISTA:
			setVisible(true);
			break;
		case BUSCAR_PLATO_OK:
			JOptionPane.showMessageDialog(this, "Plato con id: encontrado", "Plato con id: encontrado", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case BUSCAR_PLATO_KO:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
		
	}
}
