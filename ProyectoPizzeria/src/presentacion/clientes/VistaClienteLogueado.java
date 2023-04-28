package presentacion.clientes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import negocio.clientes.TCliente;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaClienteLogueado extends JDialog implements IGUI{
	
	
	private JButton modificarDatos;
	private JButton realizarReserva;
	private JButton bajaCliente;
	private JButton consultarDatos;
	private String id;
	
	public VistaClienteLogueado(Frame parent) {
		super(parent ,true);
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		
		JLabel titulo = new JLabel("Pantalla del cliente con id" + this.id);
		titulo.setFont(new Font("Serif", Font.BOLD, 30));
		//titulo.setPreferredSize(new Dimension(200, 100));
		mainPanel.add(titulo, BorderLayout.NORTH);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 2, 20 ,20));
		buttonsPanel.setPreferredSize(new Dimension(110, 300));
		
		
		modificarDatos = new JButton();
		modificarDatos.setText("Modificar datos");
		modificarDatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().accion(Evento.VISTA_MODIFICAR_CLIENTE, id);
			}
			
		});
		buttonsPanel.add(modificarDatos);

		
		
		realizarReserva = new JButton();
		realizarReserva.setText("Realizar una reserva");
		realizarReserva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		buttonsPanel.add(realizarReserva);
		
		
		bajaCliente = new JButton();
		bajaCliente.setText("Baja de cliente");
		bajaCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(VistaClienteLogueado.this, "Se ha dado de baja al cliente con id " + id, "Baja de cliente", JOptionPane.INFORMATION_MESSAGE);
				Controlador.getInstance().accion(Evento.BAJA_CLIENTE, id);
			}
			
		});
		
		buttonsPanel.add(bajaCliente);
		
		
		consultarDatos = new JButton();
		consultarDatos.setText("Baja de cliente");
		consultarDatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().accion(Evento.CONSULTAR_DATOS_CLIENTE, VistaClienteLogueado.this.id);
			}
			
		});
		buttonsPanel.add(consultarDatos );
		
		mainPanel.add(buttonsPanel);
		
		
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	
	
	@Override
	public void actualizar(Evento e, Object datos) {
		// TODO Auto-generated method stub
		switch(e) {
		case VISTA_CLIENTE_LOGUEADO:
			this.setVisible(true);
			this.id = (String)datos;
			break;
		case VISTA_MODIFICAR_CLIENTE:
			this.setVisible(false);
			break;
		case BAJA_CLIENTE:
			this.setVisible(false);
			break;
		case CONSULTAR_DATOS_CLIENTE:
			 TCliente cliente = (TCliente)datos;
			 JOptionPane.showMessageDialog(this, "Sus datos son: \n Nombre : " + cliente.getNombre() + "\n Apellido: " + cliente.getApellido(), "Datos cliente " + cliente.getId(), JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
}