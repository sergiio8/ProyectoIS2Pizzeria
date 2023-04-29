package presentacion.clientes;

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

public class VistaBajaCliente  extends JDialog implements IGUI{
	
	private static final long serialVersionUID = 1L;
	private JLabel lId;
	private JTextField tId;
	private JButton okButton;
	private JButton cancelButton;
	
	
	public VistaBajaCliente(Frame parent) {
		super(parent, true);
		
		setTitle("Baja Cliente");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		JPanel idPanel = new JPanel();
		
		
		this.lId = new JLabel("Id Cliente: ");
		idPanel.add(lId);
		
		this.tId = new JTextField(10);
		idPanel.add(tId);
		
		
		contenedor.add(idPanel);
		
		

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		this.okButton = new JButton("OK");
		this.okButton.addActionListener((e) ->{
			
				if(tId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(VistaBajaCliente.this, "ERROR: No puede dejar vacío el id", "ERROR: No puede dejar vacío el id", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Controlador.getInstance().accion(Evento.BAJA_CLIENTE, tId.getText().toString());
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
		
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case VISTA_BAJA_CLIENTE:
			setVisible(true);
			break;
		case BAJA_CLIENTE_KO:
			JOptionPane.showMessageDialog(this, "ERROR: No se ha podido borrar al cliente con id " + datos.toString(), "ERROR: No se ha podido borrar el cliente", JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		case BAJA_CLIENTE_OK:
			JOptionPane.showMessageDialog(this, "Cliente con id " + datos.toString()+" ha sido borrado correctamente", "Cliente borrado correctamente", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		}
		
	}

}

