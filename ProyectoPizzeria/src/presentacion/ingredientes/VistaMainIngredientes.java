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

public class VistaMainIngredientes extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	
	public VistaMainIngredientes() {
		initGUI();
	}
	private void initGUI() {
		setTitle("Ingredientes");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(8,15,15,15));
		
		JLabel titulo = new JLabel("Subsistemas Ingredientes");
		titulo.setFont(new Font("Serif", Font.PLAIN, 75));
		mainPanel.add(titulo, BorderLayout.NORTH);
		
		setContentPane(mainPanel);
		
		JPanel panel = new JPanel(new GridLayout(3,2,20,20));
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
		
		JButton botonBuscar = new JButton("Buscar ingrediente");
		botonBuscar.addActionListener((e) -> buscar());
		panel.add(botonBuscar);
		
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
			public void windowOpened(WindowEvent e) {}

			@Override
			public void windowClosing(WindowEvent e) {
				Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);
				VistaMainIngredientes.this.dispose();
			}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}
			
		});
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private void volver() {
		Controlador.getInstance().accion(Evento.MAIN_WINDOW, null);
		this.dispose();
	}
	private void listar() {
		Controlador.getInstance().accion(Evento.LISTAR_INGREDIENTE_VISTA, null);
	}
	private void eliminar() {
		Controlador.getInstance().accion(Evento.BAJA_INGREDIENTE_VISTA, null);
	}
	private void modificar() {
		Controlador.getInstance().accion(Evento.MODIFICAR_INGREDIENTE_VISTA, null);
	}
	private void crear() {
		Controlador.getInstance().accion(Evento.ALTA_INGREDIENTE_VISTA, null);
	}
	private void buscar() {
		Controlador.getInstance().accion(Evento.BUSCAR_INGREDIENTE_VISTA, null);
	}
	@Override
	public void actualizar(Evento e, Object datos) {
	}
}
