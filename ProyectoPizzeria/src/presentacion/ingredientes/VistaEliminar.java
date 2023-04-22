package presentacion.ingredientes;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import presentacion.controlador.Controlador;

public class VistaEliminar extends JDialog{
	private Controlador ctrl;
	
	VistaEliminar(Controlador ctrl){
		this.ctrl = ctrl;
		initGUI();
	}
	private void initGUI() {
		setTitle("Eliminar ingrediente");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		//tabla para listar
		
		pack();
		setResizable(false);
		setVisible(true);
	}
}
