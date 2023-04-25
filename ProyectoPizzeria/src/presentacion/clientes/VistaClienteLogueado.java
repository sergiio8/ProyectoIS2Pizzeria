package presentacion.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.Evento;
import presentacion.IGUI;

public class VistaClienteLogueado extends JFrame implements IGUI{
	
	
	private JButton modificarDatos;
	private JButton realizarReserva;
	
	public VistaClienteLogueado() {
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel mPanel = new JPanel();
		mPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		modificarDatos = new JButton();
		modificarDatos.setText("Modifica tus datos");
		modificarDatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		mPanel.add(modificarDatos);
		mainPanel.add(mPanel);
		
		
		JPanel rPanel = new JPanel();
		rPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		realizarReserva = new JButton();
		realizarReserva.setText("Realizar una reserva");
		realizarReserva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		rPanel.add(realizarReserva);
		mainPanel.add(rPanel);
		
		pack();
		setResizable(false);
	}
	
	
	@Override
	public void actualizar(Evento e, Object datos) {
		// TODO Auto-generated method stub
		
		switch(e) {
		case CLIENTE_REGISTRADO:
			this.setVisible(true);
			this.setTitle("Pantalla del cliente: " + (String)datos);
		}
		
	}
}
