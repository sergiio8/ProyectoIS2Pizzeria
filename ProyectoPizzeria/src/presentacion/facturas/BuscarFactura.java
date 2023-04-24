package presentacion.facturas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BuscarFactura extends JFrame{
	public BuscarFactura() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Buscar factura");
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
		
		
		JButton buscar = new JButton("Buscar");
		buscar.addActionListener((e) -> buscar());
		JButton cancelar = new JButton("Cancelar");
	    cancelar.addActionListener((e) -> cancelar());
	    panel3.add(buscar);
	    panel3.add(cancelar);
		
		mainPanel.add(panel3, BorderLayout.CENTER);
	}
	
	private void buscar() {
		
		
	}
	
	private void cancelar() {
		
	}
	

}
