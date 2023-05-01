package presentacion.facturas;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaPrincipalFacturas extends JFrame implements IGUI {
   private static final long serialVersionUID = 1L;


	private JButton abrirButton;
	private JButton listarButton;
	private JButton buscarButton;
	private JButton volverButton;
	private boolean first = true;

	public VistaPrincipalFacturas() {
		super("Facturas");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		
		JLabel titulo = new JLabel("Facturas");
		titulo.setFont(new Font("Serif", Font.PLAIN, 75));
		mainPanel.add(titulo, BorderLayout.NORTH);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 2, 20 ,20));
		buttonsPanel.setPreferredSize(new Dimension(110, 300));
		
		abrirButton = new JButton("Abrir venta");
		abrirButton.addActionListener((e) -> {
			Controlador.getInstance().accion(Evento.ABRIR_VENTA, null);
			Controlador.getInstance().accion(Evento.VISTA_FACTURA_EN_PROCESO, null);
		});
		buttonsPanel.add(abrirButton);

		
		listarButton = new JButton("Listar facturas");
		listarButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.LISTAR_FACTURAS_VISTA, null);
		});
		buttonsPanel.add(listarButton);
		
		buscarButton = new JButton("Buscar factura");
		buscarButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.BUSCAR_FACTURA_VISTA, null);
		});
		buttonsPanel.add(buscarButton);
		
		
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
				VistaPrincipalFacturas.this.dispose();
				
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
