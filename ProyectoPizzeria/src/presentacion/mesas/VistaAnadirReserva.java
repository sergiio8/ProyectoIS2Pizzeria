package presentacion.mesas;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DateFormatter;

import negocio.mesas.TMesas;
import negocio.mesas.TReserva;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaAnadirReserva extends JDialog implements IGUI{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel idMesaLabel;
	private JTextField idMesaField;
	private JTextField idClienteField;
	private JLabel idClienteLabel;
	private JLabel fechaLabel;
	private JSpinner fechaSpinner;
	private JLabel horaLabel;
	private JSpinner horaSpinner;
	private JButton okButton;
	private JButton cancelButton;
	
	
	public VistaAnadirReserva(Frame parent) {
		super(parent, true);
		
		setTitle("Anadir Reserva");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		JPanel idPanel = new JPanel();
		JPanel idPanel2 = new JPanel();
		
		this.idMesaLabel = new JLabel("idMesa: ");
		idPanel.add(idMesaLabel);
		
		this.idMesaField = new JTextField(10);
		idPanel.add(idMesaField);
		contenedor.add(idPanel);
		
		this.idClienteLabel = new JLabel("idClientes: ");
		idPanel2.add(idClienteLabel);
		
		this.idClienteField = new JTextField(10);
		idPanel2.add(idClienteField);
		
		
		contenedor.add(idPanel2);
		
		

		
		
		//Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
		JPanel fechaPanel = new JPanel();
		this.fechaLabel = new JLabel("Dia:");
		fechaPanel.add(fechaLabel);
		SpinnerDateModel sdm = new SpinnerDateModel(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()), null, Calendar.DATE);
		this.fechaSpinner = new JSpinner(sdm);
		this.fechaSpinner.setEditor(new JSpinner.DateEditor(fechaSpinner, "dd.MM.yyyy"));
		fechaPanel.add(fechaSpinner);
		contenedor.add(fechaPanel);
		
		JPanel horaPanel = new JPanel();
		this.horaLabel = new JLabel("Hora:");
		horaPanel.add(horaLabel);
		SpinnerNumberModel snm1 = new SpinnerNumberModel(19, 19, 23, 1);
		this.horaSpinner = new JSpinner(snm1);
		
		horaPanel.add(horaSpinner);
		contenedor.add(horaPanel);
		
		

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		this.okButton = new JButton("OK");
		this.okButton.addActionListener((e) ->{
			int idMesa;
			String idCliente;
			Date fecha;
			try {
				idMesa = Integer.parseInt(idMesaField.getText());
				idCliente = this.idClienteField.getText();
				if(idMesa < 1) {
					throw new NumberFormatException();
				}
				if(idCliente.isEmpty()) {
					throw new IllegalArgumentException();
				}
				System.out.println(idCliente);
				fecha = (Date)this.fechaSpinner.getValue();
				Calendar cal = Calendar.getInstance();
				cal.setTime(fecha);
				cal.set(Calendar.HOUR_OF_DAY, (int) this.horaSpinner.getValue());
				Date date = cal.getTime();
				Controlador.getInstance().accion(Evento.ALTA_RESERVA, new TReserva(idMesa,idCliente, date ));
				
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(VistaAnadirReserva.this, "ERROR: El numero de mesa debe ser un entero positivo", "ERROR: El numero de mesa debe ser un entero positivo", JOptionPane.ERROR_MESSAGE);
			}
			catch(IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(VistaAnadirReserva.this, "ERROR: Rellene los campos", "ERROR: Rellene los campos", JOptionPane.ERROR_MESSAGE);
			}
			
			
			
		});
		
		
		this.cancelButton = new JButton("Cancelar");
		this.cancelButton.addActionListener((e)->{
			this.setVisible(false);
		});
		
		
		
		buttonsPanel.add(this.okButton);
		buttonsPanel.add(this.cancelButton);
		
		
		contenedor.add(buttonsPanel);
		
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		//setPreferredSize(new Dimension(320, 500));
		pack();
		setLocationRelativeTo(parent);
		setResizable(false);
		
	}
	

	@Override
	public void actualizar(Evento e, Object datos) {
		switch(e) {
		case ALTA_RESERVA_VISTA:
			setVisible(true);
			break;
		case ALTA_RESERVA_OK:
			JOptionPane.showMessageDialog(this, "Reserva anadida con id: " + datos.toString(), "Reserva anadida con id: " + datos.toString(), JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case ALTA_RESERVA_KO:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
	}

}
