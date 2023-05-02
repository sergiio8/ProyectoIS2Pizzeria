package presentacion.clientes;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import negocio.clientes.TCliente;
import negocio.clientes.TDatosAltaCliente;
import negocio.mesas.TReserva;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;

public class VistaAltaCliente extends JDialog implements IGUI{
	
	private JLabel lNombre;
	private JTextField tNombre;
	private JLabel lAp;
	private JTextField tAp;
	private JLabel lId;
	private JTextField tId;
	private JLabel idMesaLabel;
	private JTextField idMesaField;
	private JLabel idClienteLabel;
	private JLabel fechaLabel;
	private JSpinner fechaSpinner;
	private JLabel horaLabel;
	private JSpinner horaSpinner;
	private JButton ok;
	private JButton cancel;
	
	
	public VistaAltaCliente(Frame parent) {
		super(parent, true);
		
		setTitle("Alta Cliente");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Box contenedor = Box.createVerticalBox();
		
		JPanel tp1 = new JPanel();
		tp1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titlel1 = new JLabel("Datos cliente:");
		titlel1.setFont(new Font("Serif", Font.BOLD, 15));
		tp1.add(titlel1);
		contenedor.add(tp1);
		
		
		JPanel idPanel = new JPanel();
		
		this.lId = new JLabel("id: ");
		idPanel.add(lId);
		
		this.tId = new JTextField(15);
		idPanel.add(tId);
		
		contenedor.add(idPanel);
		
		
		
		JPanel nombrePanel = new JPanel();
		
		lNombre = new JLabel("Nombre: ");
		nombrePanel.add(lNombre);
		
		tNombre = new JTextField(15);
		nombrePanel.add(tNombre);
		
		contenedor.add(nombrePanel);
		
		
		
		JPanel apellidoPanel = new JPanel();
		
		lAp = new JLabel("Apellidos: ");
		apellidoPanel.add(lAp);
		
		tAp = new JTextField(15);
		apellidoPanel.add(tAp);

		contenedor.add(apellidoPanel);
		
		
		JPanel tp2 = new JPanel();
		tp2.setAlignmentX(CENTER_ALIGNMENT);
		JLabel titlel2 = new JLabel("Datos reserva:");
		titlel2.setFont(new Font("Serif", Font.BOLD, 15));
		tp2.add(titlel2);
		contenedor.add(tp2);
		
		
		JPanel idMesaPanel = new JPanel();
		
		this.idMesaLabel = new JLabel("idMesa: ");
		idMesaPanel.add(idMesaLabel);
		
		this.idMesaField = new JTextField(10);
		idMesaPanel.add(idMesaField);
		contenedor.add(idMesaPanel);
		
		
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
		
		
		
		JPanel buttonp1 = new JPanel();
		buttonp1.setAlignmentX(CENTER_ALIGNMENT);
		
		ok = new JButton();
		ok.setText("OK");
		
		ok.addActionListener((e)->{
			// TODO Auto-generated method stub
			Date fecha;
			if(tId.getText().isEmpty() || tNombre.getText().isEmpty() || tAp.getText().isEmpty()) {
				JOptionPane.showMessageDialog(VistaAltaCliente.this, "ERROR: Es necesario rellenar todos los datos", "ERROR: Es necesario rellenar todos los datos", JOptionPane.ERROR_MESSAGE);
			}
			else {
				fecha = (Date)this.fechaSpinner.getValue();
				Calendar cal = Calendar.getInstance();
				cal.setTime(fecha);
				cal.set(Calendar.HOUR_OF_DAY, (int) this.horaSpinner.getValue());
				Date date = cal.getTime();
				Controlador.getInstance().accion(Evento.ALTA_CLIENTE, new TDatosAltaCliente(new TCliente(tId.getText().toString(),tNombre.getText().toString() , tAp.getText().toString() ), new TReserva(Integer.parseInt(idMesaField.getText().toString()), this.tId.getText().toString() , date)));
			}
			
		});
		
		this.cancel = new JButton("Cancelar");
		this.cancel.addActionListener((e)->{
			this.setVisible(false);
		});
		buttonp1.add(ok);
		buttonp1.add(cancel);
		
		contenedor.add(buttonp1);
		
		mainPanel.add(contenedor, BorderLayout.CENTER);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	
	
	
	
	
	@Override
	public void actualizar(Evento e, Object datos) {
		// TODO Auto-generated method stub
		switch(e) {
		case VISTA_ALTA_CLIENTE:
			this.tAp.setText(null);
			this.tId.setText(null);
			this.tNombre.setText(null);
			this.idMesaField.setText(null);
			this.setVisible(true);
			break;
		case ALTA_CLIENTE_OK:
			TDatosAltaCliente d = (TDatosAltaCliente)datos;
			JOptionPane.showMessageDialog(this, "El cliente con id " + d.getCliente().getId()+ " y la reserva " + d.getReserva().getId()+ " han sido registrado", "Cliente y reserva registrados con éxito", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			break;
		case ALTA_CLIENTE_KO:
			JOptionPane.showMessageDialog(this, "ERROR: no se ha podido registrar cliente con id " + datos.toString(), "ERROR: Cliente no registrado", JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			break;
		/*case ALTA_RESERVA_OK:
			JOptionPane.showMessageDialog(this, "Reserva anadida con id: " + datos.toString(), "Reserva anadida con id: " + datos.toString(), JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			break;*/
		case ALTA_RESERVA_KO:
			JOptionPane.showMessageDialog(this, "ERROR: " + datos.toString(), "ERROR: " + datos.toString(), JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		}
		
	}
		
		

}