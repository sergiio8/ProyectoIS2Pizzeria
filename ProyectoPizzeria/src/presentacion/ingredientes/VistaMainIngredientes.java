package presentacion.ingredientes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentacion.controlador.Controlador;

public class VistaMainIngredientes extends JDialog{
	
	private Controlador ctrl;
	
	public VistaMainIngredientes(Controlador ctrl) {
		this.ctrl = ctrl;
		initGUI();
	}
	private void initGUI() {
		setTitle("Ingredientes");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		JLabel titulo = new JLabel("Seleccione una opción");
		mainPanel.add(titulo, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new FlowLayout());
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
		
		JButton botonCancelar = new JButton("CANCELAR");
		botonCancelar.addActionListener((e) -> cancelar());
		mainPanel.add(botonCancelar, BorderLayout.SOUTH);
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	void listar() {
		VistaListar vl = new VistaListar(ctrl);
	}
	void eliminar() {
		VistaEliminar vl = new VistaEliminar(ctrl);
	}
	void modificar() {
	}
	void cancelar() {
	}
	void crear() {
	}
}
