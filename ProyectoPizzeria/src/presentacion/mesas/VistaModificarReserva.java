package presentacion.mesas;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import negocio.mesas.TReserva;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaModificarReserva extends JDialog implements IGUI{

	private static final long serialVersionUID = 1L;
	
	private JLabel idLabel;
	private JTextField idField;
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
	
	
	public VistaModificarReserva(Frame parent) {
		super(parent, true);
		
		setTitle("Modificar Reserva");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		JPanel idPanel0 = new JPanel();
		JPanel idPanel = new JPanel();
		JPanel idPanel2 = new JPanel();
		
		this.idLabel = new JLabel("id:");
		this.idField = new JTextField(10);
		idPanel0.add(idLabel);
		idPanel0.add(idField);
		contenedor.add(idPanel0);
		
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
		SpinnerDateModel sdm = new SpinnerDateModel(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()), null, Calendar.HOUR);
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
			int id;
			String idCliente;
			Date fecha;
			try {
				id = Integer.parseInt(this.idField.getText());
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
				Controlador.getInstance().accion(Evento.MODIFICAR_RESERVA, new TReserva(idMesa,idCliente, date, id ));
				
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(VistaModificarReserva.this, "ERROR: El numero de mesa debe ser un entero positivo", "ERROR: El numero de mesa debe ser un entero positivo", JOptionPane.ERROR_MESSAGE);
			}
			catch(IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(VistaModificarReserva.this, "ERROR: Rellene los campos", "ERROR: Rellene los campos", JOptionPane.ERROR_MESSAGE);
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
		case MODIFICAR_RESERVA_VISTA:
			setVisible(true);
			break;
		case MODIFICAR_RESERVA_OK:
			JOptionPane.showMessageDialog(this, "Reserva modificada correctamente", "Reserva modificada correctamente", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;
		case MODIFICAR_RESERVA_KO:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
	}

}
