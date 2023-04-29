package presentacion.clientes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.clientes.SAClientes;
import presentacion.*;
import presentacion.controlador.Controlador;

public class VistaPrincipalCliente extends JFrame implements IGUI{
	
	private JButton altaButton;
	private JButton bajaButton;
	private JButton listarButton;
	private JButton buscarButton;
	private JButton modificarButton;
	private JButton volverButton;

	public VistaPrincipalCliente() {
		super("Clientes");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		
		JLabel titulo = new JLabel("Subsistema Clientes");
		titulo.setFont(new Font("Serif", Font.PLAIN, 75));
		mainPanel.add(titulo, BorderLayout.NORTH);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 2, 20 ,20));
		buttonsPanel.setPreferredSize(new Dimension(110, 300));
		
		altaButton = new JButton("Alta Cliente");
		altaButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.VISTA_ALTA_CLIENTE, null);
		});
		buttonsPanel.add(altaButton);
		
		bajaButton = new JButton("Baja Cliente");
		bajaButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.BAJA_MESA_VISTA, null);
		});
		buttonsPanel.add(bajaButton);
		
		listarButton = new JButton("Listar Clientes");
		listarButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.VISTA_LISTAR_CLIENTES, null);//hecho
		});
		buttonsPanel.add(listarButton);
		
		buscarButton = new JButton("Buscar Cliente");
		buscarButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.BUSCAR_MESA_VISTA, null);
		});
		buttonsPanel.add(buscarButton);
		
		modificarButton = new JButton("Modificar Cliente");
		modificarButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.VISTA_MODIFICAR_CLIENTE, null);//hecho
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
				VistaPrincipalCliente.this.dispose();
				
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