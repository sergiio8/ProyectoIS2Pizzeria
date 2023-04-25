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

public class BuscarFactura extends JDialog implements IGUI{
	public BuscarFactura(JFrame parent) {
		super(parent, true);
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

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case BUSCAR_FACTURA_VISTA:
			setVisible(true);
			break;
		case BUSCAR_FACTURA_VISTA_OK:
			
			break;
		case BUSCAR_FACTURA_VISTA_WR:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
		
	}
	

}
