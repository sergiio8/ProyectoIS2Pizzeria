package presentacion.ingredientes;

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
import presentacion.producto.VistaPrincipalPlatos;

public class VistaMainIngredientes extends JFrame implements IGUI{
	
	public VistaMainIngredientes() {
		initGUI();
	}
	private void initGUI() {
		setTitle("Ingredientes");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		
		JLabel titulo = new JLabel("Subsistema Ingredientes");
		titulo.setFont(new Font("Serif", Font.PLAIN, 75));
		mainPanel.add(titulo, BorderLayout.NORTH);
		
		setContentPane(mainPanel);
		
		JPanel panel = new JPanel(new GridLayout(2,2,20,20));
		panel.setPreferredSize(new Dimension(80,150));
		
		JButton botonListar = new JButton("Listar ingredientes");
		botonListar.addActionListener((e) -> listar());
		panel.add(botonListar);
		
		JButton botonEliminar = new JButton("Eliminar ingrediente");
		botonEliminar.addActionListener((e) -> eliminar());
		panel.add(botonEliminar);
		
		JButton botonModificar = new JButton("Modificar ingrediente");
		botonModificar.addActionListener((e) -> modificar());
		panel.add(botonModificar);
		
		JButton botonCrear = new JButton("Crear ingrediente");
		botonCrear.addActionListener((e) -> crear());
		panel.add(botonCrear);
		
		mainPanel.add(panel, BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel3.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		
		JButton volverBoton = new JButton("Volver");
		volverBoton.addActionListener((e)-> volver());
		volverBoton.setPreferredSize(new Dimension(100, 45));
		panel3.add(volverBoton);
		
		mainPanel.add(panel3, BorderLayout.SOUTH);
		
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);
				VistaMainIngredientes.this.dispose();
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
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	void volver() {
		Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);
		this.dispose();
	}
	void listar() {
		Controlador.getInstance().accion(Evento.LISTAR_INGREDIENTE_VISTA, null);
	}
	void eliminar() {
		Controlador.getInstance().accion(Evento.BAJA_INGREDIENTE_VISTA, null);
	}
	void modificar() {
		Controlador.getInstance().accion(Evento.MODIFICAR_INGREDIENTE_VISTA, null);
	}
	void crear() {
		Controlador.getInstance().accion(Evento.ALTA_INGREDIENTE_VISTA, null);
	}
	@Override
	public void actualizar(Evento e, Object datos) {
	}
}
