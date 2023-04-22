package presentacion.ingredientes;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import presentacion.controlador.Controlador;

public class VistaListar extends JDialog{
	
	private Controlador ctrl;
	
	VistaListar(Controlador ctrl){
		this.ctrl = ctrl;
		initGUI();
	}
	private void initGUI() {
		setTitle("Listar Ingredientes");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		//tabla para listar
		
		pack();
		setResizable(false);
		setVisible(true);
	}
}
