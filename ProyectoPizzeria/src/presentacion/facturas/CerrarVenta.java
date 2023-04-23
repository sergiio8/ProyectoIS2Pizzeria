package presentacion.facturas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CerrarVenta extends JFrame{
	public CerrarVenta() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Cerrar venta");
		setLayout(new BorderLayout());
		JPanel panel1 = new JPanel(new FlowLayout());
		JLabel ID = new JLabel("ID_cliente: ");
		JTextField text1 = new JTextField();
		
		panel1.add(ID);
		panel1.add(text1);
		
		add(panel1, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel(new FlowLayout());
		JLabel ID_vendedor = new JLabel("ID_vendedor: ");
		JTextField text2 = new JTextField();
		
		panel2.add(ID_vendedor);
		panel2.add(text2);
		
		add(panel2, BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel(new FlowLayout());
		
		

		JButton cerrar = new JButton("Cerrar venta");
		cerrar.addActionListener((e) -> cerrar());
		JButton cancelar = new JButton("Cancelar");
	    cancelar.addActionListener((e) -> cancelar());
	    panel3.add(cerrar);
	    panel3.add(cancelar);
		
		add(panel3, BorderLayout.CENTER);
	}
	
	private void cerrar() {
		
		
	}
	
	private void cancelar() {
		
	}

}
