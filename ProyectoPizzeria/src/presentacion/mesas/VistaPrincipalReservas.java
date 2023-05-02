package presentacion.mesas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaPrincipalReservas extends JFrame implements IGUI{
	
	private static final long serialVersionUID = 1L;
	
	private JButton altaButton;
	private JButton bajaButton;
	private JButton listarButton;
	private JButton listarCButton;
	private JButton listarMButton;
	private JButton modificarButton;
	private JButton volverButton;

	public VistaPrincipalReservas() {
		super("Reservas");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		
		JLabel titulo = new JLabel("Subsistema Reservas");
		titulo.setFont(new Font("Serif", Font.PLAIN, 75));
		titulo.setHorizontalAlignment(JLabel.CENTER);
		//titulo.setPreferredSize(new Dimension(200, 100));
		mainPanel.add(titulo, BorderLayout.NORTH);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 2, 20 ,20));
		buttonsPanel.setPreferredSize(new Dimension(110, 300));
		
		altaButton = new JButton("Crear Reserva");
		altaButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.ALTA_RESERVA_VISTA, null);
		});
		buttonsPanel.add(altaButton);
		
		bajaButton = new JButton("Baja Reserva");
		bajaButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.BAJA_RESERVA_VISTA, null);
		});
		buttonsPanel.add(bajaButton);
		
		listarButton = new JButton("Listar Reservas");
		listarButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.LISTAR_RESERVAS, null);
		});
		buttonsPanel.add(listarButton);
		
		listarCButton = new JButton("Listar Reservas por Cliente");
		listarCButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.LISTAR_RESERVAS_CLIENTE_VISTA, null);
		});
		buttonsPanel.add(listarCButton);
		
		listarMButton = new JButton("Listar Reservas por Mesa");
		listarMButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.LISTAR_RESERVAS_MESAS_VISTA, null);
		});
		buttonsPanel.add(listarMButton);
		
		modificarButton = new JButton("Modificar Reserva");
		modificarButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.MODIFICAR_RESERVA_VISTA, null);
		});
		buttonsPanel.add(modificarButton);
		
		JPanel volverPanel = new JPanel();
		volverPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		volverPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		
		volverButton = new JButton("Volver");
		volverButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);
			this.dispose();
		});
		volverButton.setPreferredSize(new Dimension(100, 45));
		
		volverPanel.add(volverButton);
		
		mainPanel.add(volverPanel, BorderLayout.SOUTH);
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);
				VistaPrincipalReservas.this.dispose();
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		mainPanel.add(buttonsPanel, BorderLayout.CENTER);
		mainPanel.setPreferredSize(new Dimension(700, 550));
		setContentPane(mainPanel);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		// TODO Auto-generated method stub
		
	}
	
	
}