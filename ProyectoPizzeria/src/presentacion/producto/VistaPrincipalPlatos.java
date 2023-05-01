package presentacion.producto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class VistaPrincipalPlatos extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VistaPrincipalPlatos() {
		super("Platos");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		
		JLabel titulo = new JLabel("Subsistema Platos");
		titulo.setFont(new Font("Serif", Font.PLAIN, 75));
		mainPanel.add(titulo, BorderLayout.NORTH);
		
		JPanel botonesPanel = new JPanel();
		botonesPanel.setLayout(new GridLayout(3, 2, 20 ,20));
		botonesPanel.setPreferredSize(new Dimension(110, 300));
		
		JButton altaBoton = new JButton("Alta Plato");
		altaBoton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.ALTA_PLATO_VISTA, null);
		});
		botonesPanel.add(altaBoton);
		
		JButton bajaBoton = new JButton("Baja Plato");
		bajaBoton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.BAJA_PLATO_VISTA, null);
		});
		botonesPanel.add(bajaBoton);
		
		JButton listarBoton = new JButton("Listar Platos");
		listarBoton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.LISTAR_PLATOS, null);
		});
		botonesPanel.add(listarBoton);
		
		JButton buscarBoton = new JButton("Buscar Plato");
		buscarBoton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.BUSCAR_PLATO_VISTA, null);
		});
		botonesPanel.add(buscarBoton);
		
		JButton modificarBoton = new JButton("Modificar Plato");
		modificarBoton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.MODIFICAR_PLATO_VISTA, null);
		});
		botonesPanel.add(modificarBoton);
		
		JPanel volverPanel = new JPanel();
		volverPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		volverPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		
		JButton volverBoton = new JButton("Volver");
		volverBoton.addActionListener((e)->{
			Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);
			dispose();
		});
		volverBoton.setPreferredSize(new Dimension(100, 45));
		
		volverPanel.add(volverBoton);
		
		mainPanel.add(volverPanel, BorderLayout.SOUTH);
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);
				VistaPrincipalPlatos.this.dispose();
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
		
		mainPanel.add(botonesPanel, BorderLayout.CENTER);
		setContentPane(mainPanel);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		// TODO Auto-generated method stub
		
	}

}
