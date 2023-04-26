package presentacion.mesas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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

public class VistaPrincipalMesas extends JFrame implements IGUI{
	
	private static final long serialVersionUID = 1L;
	
	private JButton altaButton;
	private JButton bajaButton;
	private JButton listarButton;
	private JButton buscarButton;
	private JButton modificarButton;
	private JButton volverButton;

	public VistaPrincipalMesas() {
		super("Mesas");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		
		JLabel titulo = new JLabel("Subsistema Mesas");
		titulo.setFont(new Font("Serif", Font.PLAIN, 75));
		//titulo.setPreferredSize(new Dimension(200, 100));
		mainPanel.add(titulo, BorderLayout.NORTH);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 2, 20 ,20));
		buttonsPanel.setPreferredSize(new Dimension(110, 300));
		
		altaButton = new JButton("Alta Mesa");
		altaButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.ALTA_MESA_VISTA, null);
		});
		buttonsPanel.add(altaButton);
		
		bajaButton = new JButton("Baja Mesa");
		bajaButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.BAJA_MESA_VISTA, null);
		});
		buttonsPanel.add(bajaButton);
		
		listarButton = new JButton("Listar Mesa");
		listarButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.LISTAR_MESAS, null);
		});
		buttonsPanel.add(listarButton);
		
		buscarButton = new JButton("Buscar Mesa");
		buscarButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.BUSCAR_MESA_VISTA, null);
		});
		buttonsPanel.add(buscarButton);
		
		modificarButton = new JButton("Modificar Mesa");
		modificarButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.MODIFICAR_MESA, null);
		});
		buttonsPanel.add(modificarButton);
		
		volverButton = new JButton("Volver");
		volverButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);
			this.dispose();
		});
		volverButton.setAlignmentX(LEFT_ALIGNMENT);
		mainPanel.add(volverButton, BorderLayout.SOUTH);
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);
				VistaPrincipalMesas.this.dispose();
				
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