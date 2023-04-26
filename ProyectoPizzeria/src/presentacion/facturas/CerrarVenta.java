package presentacion.facturas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.facturas.TDatosVenta;
import negocio.facturas.TLineaFactura;
import negocio.mesas.TMesas;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;
import presentacion.mesas.VistaAnadirMesa;

public class CerrarVenta extends JDialog implements IGUI{
	
	JTextField text1;
	JTextField text2;
	JTextField text3;
	JTextField text4;
	
	public CerrarVenta(Frame parent) {
		super(parent, true);
		initGUI();

	}
	
	private void initGUI() {
		setTitle("Cerrar venta");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		JPanel panel1 = new JPanel(new FlowLayout());
		JLabel ID_factura = new JLabel("ID_factura: ");
		text1 = new JTextField(10);
		
		panel1.add(ID_factura);
		panel1.add(text1);
		
		contenedor.add(panel1);
		
		JPanel panel2 = new JPanel(new FlowLayout());
		JLabel ID_cliente = new JLabel("ID_cliente: ");
		text2 = new JTextField(10);
		
		panel2.add(ID_cliente);
		panel2.add(text2);
		
		contenedor.add(panel2);
		
		JPanel panel3 = new JPanel(new FlowLayout());
		JLabel ID_vendedor = new JLabel("ID_vendedor: ");
		text3 = new JTextField(10);
		
		panel3.add(ID_vendedor);
		panel3.add(text3);
		
		contenedor.add(panel3);
		
		JPanel panel4 = new JPanel(new FlowLayout());
		JLabel fecha = new JLabel("Fecha: ");
		text4 = new JTextField(10);
		
		panel4.add(fecha);
		panel4.add(text4);
		
		contenedor.add(panel4);
		
		JPanel panel5 = new JPanel(new FlowLayout());
		

		JButton cerrar = new JButton("Cerrar venta");
		cerrar.addActionListener((e) -> cerrar());
		JButton cancelar = new JButton("Cancelar");
	    cancelar.addActionListener((e) -> cancelar());
	    panel5.add(cerrar);
	    panel5.add(cancelar);
		
		contenedor.add(panel5);
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		
		setResizable(false);
	}
	
	private void cerrar() {
		String ID_factura;
		String ID_cliente;
		String ID_vendedor;
		String fecha;
		try {
			ID_factura = text1.getText();
			ID_cliente = text2.getText();
			ID_vendedor = text3.getText();
			fecha = text4.getText();
			if(ID_cliente == null || ID_vendedor == null) {
				throw new IllegalArgumentException();
			}
			Controlador.getInstance().accion(Evento.ALTA_FACTURA, new TDatosVenta(new ArrayList<TLineaFactura>(), ID_factura, ID_cliente, ID_vendedor, fecha));
			
		}
		catch(IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(CerrarVenta.this, "ERROR: rellene todos los campos relativos a los IDs", "ERROR: rellene todos los campos relativos a los IDs", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cancelar() {
		setVisible(false);
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
			initGUI();
			break;
		case ALTA_FACTURA_VISTA_WR:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
		
	}

}
