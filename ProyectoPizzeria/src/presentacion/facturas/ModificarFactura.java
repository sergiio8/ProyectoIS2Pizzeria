package presentacion.facturas;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.producto.DAOPlato;
import negocio.facturas.TDatosVenta;
import negocio.facturas.TLineaFactura;
import negocio.producto.TPlato;

import javax.swing.JSpinner;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;
import presentacion.mesas.VistaAnadirMesa;

public class ModificarFactura extends JDialog implements IGUI{
	
	JTextField text1;
	JTextField text2;
	JTextField text3;
	JSpinner cantidad;
	
	public ModificarFactura(Frame parent) {
		super(parent, true);
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Modificar facturas");
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
		JLabel ID_Lineafact = new JLabel("ID_linea_factura: ");
		text2 = new JTextField(10);
		
		panel2.add(ID_Lineafact);
		panel2.add(text2);
		
		contenedor.add(panel2);
	
		
		JPanel panel3 = new JPanel(new FlowLayout());
		JLabel ID_producto = new JLabel("ID_producto: ");
		text3 = new JTextField(10);
		
		panel3.add(ID_producto);
		panel3.add(text3);
		
		contenedor.add(panel3);
	
		
		JPanel panel4 = new JPanel(new FlowLayout());
		JLabel cant = new JLabel("Cantidad: ");
		cantidad = new JSpinner();
		cantidad.setPreferredSize(new Dimension(100, 25));
		cantidad.setMinimumSize(new Dimension(100, 25));
		cantidad.setMaximumSize(new Dimension(100, 25));
		
		panel4.add(cant);
		panel4.add(cantidad);
		
		contenedor.add(panel4);
	
		
		JPanel panel5 = new JPanel(new FlowLayout());
		
		
		JButton modificar = new JButton("Modificar");
		modificar.addActionListener((e) -> modificar());
		JButton cancelar = new JButton("Cancelar");
	    cancelar.addActionListener((e) -> cancelar());
	    panel5.add(modificar);
	    panel5.add(cancelar);
		
		contenedor.add(panel5);
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		
		setResizable(false);

	}
	
	private void modificar() {
		try {
			String ID_Factura = text1.getText();
			String ID_Linea = text2.getText();
			String ID_Producto = text3.getText();
			int Cantidad = Integer.parseInt(cantidad.getValue().toString());
			
			if (Cantidad <= 0) {
				throw new NumberFormatException();
			}
			
			DAOPlato daop = FactoriaAbstractaIntegracion.getInstace().crearDAOPlato();
			TPlato plato = daop.obtenPlato(ID_Producto);
			
			Controlador.getInstance().accion(Evento.MODIFICAR_FACTURA, new TLineaFactura(ID_Linea, ID_Factura, ID_Producto, Cantidad, plato.getPrecio()*Cantidad));
		}
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(ModificarFactura.this, "ERROR: la cantidad debe ser un entero positivo", "ERROR: la cantidad debe ser un entero positivo", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
	}
	
	private void cancelar() {
		setVisible(false);
		
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case MODIFICAR_FACTURA_VISTA:
			setVisible(true);
			break;
		case MODIFICAR_FACTURA_VISTA_OK:
			JOptionPane.showMessageDialog(this,"Factura con ID " + datos.toString() + " modificada correctamente" ,"Factura con ID " + datos.toString() + " modificada correctamente", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			initGUI();
			break;
		case MODIFICAR_FACTURA_VISTA_WR:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
		
	}

}
