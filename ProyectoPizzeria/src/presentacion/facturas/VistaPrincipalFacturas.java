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
	private JLabel esta_abierta;
	private JButton altaButton;
	private JButton listarButton;
	private JButton buscarButton;
	private JButton modificarButton;
	private JButton volverButton;
	private JButton anadirButton;
	private boolean first = true;

	public VistaPrincipalFacturas() {
		super("Facturas");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		
		JLabel titulo = new JLabel("Subsistema Facturas");
		titulo.setFont(new Font("Serif", Font.PLAIN, 75));
		mainPanel.add(titulo, BorderLayout.NORTH);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 2, 20 ,20));
		buttonsPanel.setPreferredSize(new Dimension(110, 300));
		
		abrirButton = new JButton("Abrir venta");
		abrirButton.addActionListener((e) -> {
			Controlador.getInstance().accion(Evento.ABRIR_VENTA, null);
			esta_abierta.setText("Factura en proceso");
			
		});
		buttonsPanel.add(abrirButton);
		
		esta_abierta = new JLabel("Factura sin iniciar");
		Font font = null;
		esta_abierta.setFont(new Font("TimesRoman", Font.ITALIC + Font.BOLD, 20));
		buttonsPanel.add(esta_abierta);
		
		anadirButton = new JButton("Añadir producto a factura");
		anadirButton.addActionListener((e) -> {
			if (esta_abierta.getText().equals("Factura en proceso")) {
				if (first) {
					first = false;
					Controlador.getInstance().accion(Evento.ANADIR_PRODUCTO_VISTA_BIS, null);
				}
				else Controlador.getInstance().accion(Evento.ANADIR_PRODUCTO_VISTA, null);
			}
			else JOptionPane.showMessageDialog(this,"ERROR: Debe abrir una venta antes de añadir productos","ERROR" , JOptionPane.INFORMATION_MESSAGE);
		});
		buttonsPanel.add(anadirButton);
		
		
		altaButton = new JButton("Cerrar factura");
		altaButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.ALTA_FACTURA_VISTA, null);
			esta_abierta.setText("Factura cerrada");
			first = true;
		});
		buttonsPanel.add(altaButton);

		
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
		
		/*modificarButton = new JButton("Modificar factura");
		modificarButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.MODIFICAR_FACTURA_VISTA, null);
		});
		buttonsPanel.add(modificarButton);*/
		
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
