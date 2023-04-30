package presentacion.facturas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

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
	JSpinner fechaSpinner;
	
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
		
		JPanel panel4 = new JPanel();
		JLabel fecha = new JLabel("Fecha: ");
		panel4.add(fecha);
		Date today = new Date();
		SpinnerDateModel sdm = new SpinnerDateModel(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()), null, Calendar.DATE);
		this.fechaSpinner = new JSpinner(sdm);
		this.fechaSpinner.setEditor(new JSpinner.DateEditor(fechaSpinner, "dd.MM.yyyy"));
		panel4.add(fechaSpinner);
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
			Date value = (Date)fechaSpinner.getValue(); 
			fecha = new SimpleDateFormat("dd/MM/yyyy").format(value);
			if(ID_cliente.equals("") || ID_cliente == null) {
				throw new IllegalArgumentException();
			}
			if(ID_vendedor.equals("") || ID_vendedor == null) {
				throw new IllegalArgumentException();
			}
			if(ID_factura.equals("") || ID_factura == null) {
				throw new IllegalArgumentException();
			}
			if(fecha.equals("") || fecha == null) {
				throw new IllegalArgumentException();
			}
			Controlador.getInstance().accion(Evento.ALTA_FACTURA, new TDatosVenta(new ArrayList<TLineaFactura>(), ID_cliente, ID_vendedor, fecha));
			
		}
		catch(IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(CerrarVenta.this, "ERROR: " + iae.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(this,"Factura anadida correctamente con ID: " + text1.getText() ,"Factura anadida correctamente con ID: " + text1.getText(), JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			initGUI();
			break;
		case ALTA_FACTURA_VISTA_WR:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR:", JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
	}
}
