package presentacion.facturas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModificarFactura extends JFrame{
	public ModificarFactura() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Modificar facturas");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);

		JPanel panel1 = new JPanel(new FlowLayout());
		JLabel ID = new JLabel("ID_factura: ");
		JTextField text1 = new JTextField();
		
		panel1.add(ID);
		panel1.add(text1);
		
		mainPanel.add(panel1, BorderLayout.CENTER);
	
		
		JPanel panel3 = new JPanel(new FlowLayout());
		
		
		JButton modificar = new JButton("Modificar");
		modificar.addActionListener((e) -> modificar());
		JButton cancelar = new JButton("Cancelar");
	    cancelar.addActionListener((e) -> cancelar());
	    panel3.add(modificar);
	    panel3.add(cancelar);
		
		mainPanel.add(panel3, BorderLayout.CENTER);
	}
	
	private void modificar() {
		
	}
	
	private void cancelar() {
		
	}

}
