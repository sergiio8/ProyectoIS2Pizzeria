package presentacion.mesas;

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

public class VistaBorrarReserva extends JDialog implements IGUI{
	private static final long serialVersionUID = 1L;
	private JLabel idReservaLabel;
	private JTextField idReservaField;
	private JButton okButton;
	private JButton cancelButton;
	
	
	public VistaBorrarReserva(Frame parent) {
		super(parent, true);
		
		setTitle("Borrar Reserva");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		JPanel idPanel = new JPanel();
		
		
		this.idReservaLabel = new JLabel("idReserva: ");
		idPanel.add(idReservaLabel);
		
		this.idReservaField = new JTextField(10);
		//this.idMesaField.setPreferredSize(new Dimension(130, 30));
		idPanel.add(idReservaField);
		
		
		contenedor.add(idPanel);
		
		

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		this.okButton = new JButton("OK");
		this.okButton.addActionListener((e) ->{
			int id;
			try {
				id = Integer.parseInt(idReservaField.getText());
				
				Controlador.getInstance().accion(Evento.BAJA_RESERVA, id);
				
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(VistaBorrarReserva.this, "ERROR: El id de reserva debe ser un entero", "ERROR: El id de reserva debe ser un entero", JOptionPane.ERROR_MESSAGE);
			}
			
			
		});
		
		
		this.cancelButton = new JButton("Cancelar");
		this.cancelButton.addActionListener((e)->{
			this.setVisible(false);
		});
		
		
		
		buttonsPanel.add(this.okButton);
		buttonsPanel.add(this.cancelButton);
		
		
		contenedor.add(buttonsPanel);
		
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		//setPreferredSize(new Dimension(320, 500));
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case BAJA_RESERVA_VISTA:
			this.idReservaField.setText("");
			setVisible(true);
			break;
		case BAJA_RESERVA_OK:
			JOptionPane.showMessageDialog(this, "Reserva borrada correctamente", "Reserva borrada correctamente", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case BAJA_RESERVA_KO:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		
		}
		
	}
}
