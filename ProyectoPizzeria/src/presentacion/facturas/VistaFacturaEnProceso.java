package presentacion.facturas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaFacturaEnProceso extends JDialog implements IGUI {
   private static final long serialVersionUID = 1L;


	private JButton altaButton;
	private JButton volverButton;
	private JButton anadirButton;
	private boolean first = true;

	public VistaFacturaEnProceso(Frame parent) {
		super(parent, true);
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
		
		
		
		anadirButton = new JButton("Añadir producto a factura");
		anadirButton.addActionListener((e) -> {
			if (first) {
				first = false;
				Controlador.getInstance().accion(Evento.ANADIR_PRODUCTO_VISTA_BIS, null);
			}
			else Controlador.getInstance().accion(Evento.ANADIR_PRODUCTO_VISTA, null);
		});
		buttonsPanel.add(anadirButton);
		
		
		altaButton = new JButton("Cerrar factura");
		altaButton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.ALTA_FACTURA_VISTA, null);
			first = true;
		});
		buttonsPanel.add(altaButton);
		
		volverButton = new JButton("Volver");
		volverButton.addActionListener((e)->{
			//Controlador.getInstance().accion(Evento.VISTA_PRINCIPAL_FACTURA, null);
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
				VistaFacturaEnProceso.this.dispose();
				
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
