package presentacion.facturas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.producto.DAOPlato;
import negocio.facturas.TLineaFactura;
import negocio.producto.TPlato;

import javax.swing.JSpinner;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class AnadirProducto extends JDialog implements IGUI{
	JTextField text1;
	JTextField text2;
	JTextField text3;
	JSpinner cant;
	Box productos_panel;
	

	private static final long serialVersionUID = 1L;

	public AnadirProducto(Frame parent) {
		super(parent, true);
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Añadir producto a factura");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		productos_panel = Box.createVerticalBox();
		JLabel titulo = new JLabel("Productos añadidos hasta el momento:");
		productos_panel.add(titulo);
		contenedor.add(productos_panel);
		
		
		
		JPanel panel1 = new JPanel(new FlowLayout());
		JLabel ID_factura = new JLabel("ID_factura: ");
		text1 = new JTextField(10);
		
		panel1.add(ID_factura);
		panel1.add(text1);
		
		contenedor.add(panel1);
		
		
		
		JPanel panel5 = new JPanel(new FlowLayout());
		JLabel ID_linea = new JLabel("ID_linea_factura: ");
		text3 = new JTextField(10);
		
		panel5.add(ID_linea);
		panel5.add(text3);
		
		contenedor.add(panel5);
		
		
		
		JPanel panel2 = new JPanel(new FlowLayout());
		JLabel ID = new JLabel("ID_producto: ");
		text2 = new JTextField(10);
		
		panel2.add(ID);
		panel2.add(text2);
		
		contenedor.add(panel2);
		
		
		JPanel panel3 = new JPanel(new FlowLayout());
		JLabel cantidad = new JLabel("Cantidad: ");
		cant = new JSpinner();
		cant.setPreferredSize(new Dimension(100, 25));
		cant.setMinimumSize(new Dimension(100, 25));
		cant.setMaximumSize(new Dimension(100, 25));
		
		panel3.add(cantidad);
		panel3.add(cant);
		
		contenedor.add(panel3);
		
		JPanel panel4 = new JPanel(new FlowLayout());
		
		JButton anadir = new JButton("Añadir");
		anadir.addActionListener((e) -> anadir());
		JButton cancelar = new JButton("Cancelar");
	    cancelar.addActionListener((e) -> cancelar());
	    panel4.add(anadir);
	    panel4.add(cancelar);
		
		contenedor.add(panel4);
		
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		
		setResizable(false);
		
	}
	
	private void anadir() {
		String ID_factura;
		String ID_linea;
		String ID_producto;
		int cantidad;
		try {
			ID_factura = text1.getText();
			ID_producto = text2.getText();
			ID_linea = text3.getText();
			cantidad = Integer.parseInt(cant.getValue().toString());
			if (ID_factura == null || ID_producto == null || ID_linea == null) {
				throw new IllegalArgumentException();
			}
			else if (cantidad <= 0) {
				throw new NumberFormatException();
			}
			Controlador.getInstance().accion(Evento.ANADIR_PRODUCTO, new TLineaFactura(ID_linea, ID_factura, ID_producto, cantidad));
		}
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(AnadirProducto.this, "ERROR: la cantidad debe ser un entero positivo", "ERROR: la cantidad debe ser un entero positivo", JOptionPane.ERROR_MESSAGE);
		}
		catch(IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(AnadirProducto.this, "ERROR: relllene todos los campos relativos a los IDs", "ERROR: relllene todos los campos relativos a los IDs", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cancelar() {
		setVisible(false);
		
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case ANADIR_PRODUCTO_VISTA:
			setVisible(true);
			break;
		case ANADIR_PRODUCTO_VISTA_OK:
			JOptionPane.showMessageDialog(this,"Producto anadido correctamente con ID: " + datos.toString() ,"Producto anadido correctamente con ID: " + datos.toString(), JOptionPane.INFORMATION_MESSAGE);
			productos_panel.add(new JLabel("ID: " + datos.toString() + ", cantidad: " + Integer.parseInt(cant.getValue().toString())));
			text2.setText(null);
			text3.setText(null);
			setVisible(false);
			break;
		case ANADIR_PRODUCTO_VISTA_WR:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
		
	}

}
