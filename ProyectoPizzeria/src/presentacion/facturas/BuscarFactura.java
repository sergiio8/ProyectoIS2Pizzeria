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
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;
import negocio.facturas.TFactura;

public class BuscarFactura extends JDialog implements IGUI{
	JTextField text1;
	JLabel ID;
	JButton buscar;
	JButton cancelar;
	JPanel panel3;
	JPanel data;
	
	public BuscarFactura(Frame parent) {
		super(parent, true);
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Buscar factura");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		
		data = new JPanel();
		data.setLayout(new BorderLayout());
		
		JPanel panel1 = new JPanel(new FlowLayout());
		ID = new JLabel("ID_factura: ");
		text1 = new JTextField(10);
		
		panel1.add(ID);
		panel1.add(text1);
		
		data.add(panel1);
		
		contenedor.add(data);
	
		
		panel3 = new JPanel(new FlowLayout());
		
		
		buscar = new JButton("Buscar");
		buscar.addActionListener((e) -> buscar());
		cancelar = new JButton("Cancelar");
	    cancelar.addActionListener((e) -> cancelar());
	    panel3.add(buscar);
	    panel3.add(cancelar);
		
		contenedor.add(panel3);
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		
		setResizable(false);
	}
	
	private void buscar() {
		String ID_factura = null;
		try {
			ID_factura = text1.getText();
			if (ID_factura == null) {
				throw new IllegalArgumentException();
			}
			Controlador.getInstance().accion(Evento.BUSCAR_FACTURA, ID_factura);
		}
		catch(IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(BuscarFactura.this, "ERROR: rellene el campo indicando el ID de la factura", "ERROR: rellene el campo indicando el ID de la factura", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	private void cancelar() {
		setVisible(false);
		
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case BUSCAR_FACTURA_VISTA:
			setVisible(true);
			break;
		case BUSCAR_FACTURA_VISTA_OK:
			JOptionPane.showMessageDialog(this,"Factura con ID: " + datos.toString() + " encontrada con exito" ,"Factura con ID: " + datos.toString() + " encontrada con exito" , JOptionPane.INFORMATION_MESSAGE);
			TFactura tf = (TFactura) datos;
			this.ID = new JLabel("ID: " + tf.getId());
			text1.setEnabled(false);
			panel3.removeAll();
			JLabel precio = new JLabel("precio:" + tf.getPrecio_total());
			JLabel id_cliente = new JLabel("id_cliente" + tf.getIdCliente());
			JLabel id_vendedor = new JLabel("id_vendedor" + tf.getIdVendedor());
			String productos = "";
			for (int i = 0; i < tf.getProductos().size(); ++i) {
				productos += tf.getProductos().get(i).getIdProducto() + ": " + tf.getProductos().get(i).getCantidad() + " unidades" + '\n';
			}
			JLabel productos_label = new JLabel("productos:" + productos);
			JLabel fecha = new JLabel("fecha: " + tf.getFecha());
			
			data.add(precio);
			data.add(id_cliente);
			data.add(id_vendedor);
			data.add(productos_label);
			data.add(fecha);
				
		
			JButton confirmButton = new JButton("Confirmar");
			confirmButton.addActionListener((event)->{
				setVisible(false);
			});
			this.panel3.add(confirmButton);
			revalidate();
			repaint();
			pack();
			setVisible(false);
			
			break;
		case BUSCAR_FACTURA_VISTA_WR:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
		
	}
	

}
