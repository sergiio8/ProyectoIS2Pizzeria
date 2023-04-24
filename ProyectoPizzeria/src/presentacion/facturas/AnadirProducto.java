package presentacion.facturas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AnadirProducto extends JFrame{
	

	private static final long serialVersionUID = 1L;

	public AnadirProducto() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Añadir producto");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);

		JPanel panel1 = new JPanel(new FlowLayout());
		JLabel ID = new JLabel("ID_producto: ");
		JTextField text1 = new JTextField();
		
		panel1.add(ID);
		panel1.add(text1);
		
		mainPanel.add(panel1, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel(new FlowLayout());
		JLabel cantidad = new JLabel("Cantidad: ");
		JTextField text2 = new JTextField();
		
		panel2.add(cantidad);
		panel2.add(text2);
		
		mainPanel.add(panel2, BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel(new FlowLayout());
		
		

		JButton anadir = new JButton("Añadir");
		anadir.addActionListener((e) -> anadir());
		JButton cancelar = new JButton("Cancelar");
	    cancelar.addActionListener((e) -> cancelar());
	    panel3.add(anadir);
	    panel3.add(cancelar);
		
		mainPanel.add(panel3, BorderLayout.CENTER);
	}
	
	private void anadir() {
		
		
	}
	
	private void cancelar() {
		
	}

}
