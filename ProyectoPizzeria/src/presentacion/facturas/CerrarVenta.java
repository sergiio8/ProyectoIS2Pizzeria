package presentacion.facturas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.Evento;
import presentacion.IGUI;

public class CerrarVenta extends JDialog implements IGUI{
	public CerrarVenta(JFrame parent) {
		super(parent, true);
		initGUI();
		//cambio
	}
	
	private void initGUI() {
		setTitle("Cerrar venta");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		JPanel panel1 = new JPanel(new FlowLayout());
		JLabel ID = new JLabel("ID_cliente: ");
		JTextField text1 = new JTextField();
		
		panel1.add(ID);
		panel1.add(text1);
		
		mainPanel.add(panel1, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel(new FlowLayout());
		JLabel ID_vendedor = new JLabel("ID_vendedor: ");
		JTextField text2 = new JTextField();
		
		panel2.add(ID_vendedor);
		panel2.add(text2);
		
		mainPanel.add(panel2, BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel(new FlowLayout());
		
		

		JButton cerrar = new JButton("Cerrar venta");
		cerrar.addActionListener((e) -> cerrar());
		JButton cancelar = new JButton("Cancelar");
	    cancelar.addActionListener((e) -> cancelar());
	    panel3.add(cerrar);
	    panel3.add(cancelar);
		
		mainPanel.add(panel3, BorderLayout.CENTER);
	}
	
	private void cerrar() {
		
		
	}
	
	private void cancelar() {
		
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case ALTA_FACTURA_VISTA:
			setVisible(true);
			break;
		case ALTA_FACTURA_VISTA_OK:
			JOptionPane.showMessageDialog(this,"Factura anadida correctamente con ID: " + datos.toString() ,"Factura anadida correctamente con ID: " + datos.toString(), JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case ALTA_FACTURA_VISTA_WR:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
		
	}

}
