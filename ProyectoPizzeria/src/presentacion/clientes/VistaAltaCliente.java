package presentacion.clientes;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.clientes.TCliente;
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
	private JButton ok;
	private JButton cancel;
	
	
	public VistaAltaCliente(Frame parent) {
		super(parent, true);
		this.setTitle("REGISTRO");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		
		JPanel p1 = new JPanel();
		p1.setAlignmentX(CENTER_ALIGNMENT);
		
		lNombre = new JLabel();
		lNombre.setText("Nombre: ");
		p1.add(lNombre);
		
		tNombre = new JTextField(15);
		p1.add(tNombre);
		mainPanel.add(p1);
		
		JPanel p2 = new JPanel();
		p2.setAlignmentX(CENTER_ALIGNMENT);
		
		lAp = new JLabel();
		lAp.setText("Apellidos: ");
		p2.add(lAp);
		
		tAp = new JTextField(15);
		p2.add(tAp);
		mainPanel.add(p2);
		
		
		JPanel p3 = new JPanel();
		p3.setAlignmentX(CENTER_ALIGNMENT);
		
		lId = new JLabel();
		lId.setText("Id (NIF): ");
		p3.add(lId);
		
		tId = new JTextField(15);
		p3.add(tId);
		mainPanel.add(p3);
		
		JPanel buttonp1 = new JPanel();
		buttonp1.setAlignmentX(CENTER_ALIGNMENT);
		
		ok = new JButton();
		ok.setText("OK");
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tId.getText().isEmpty() || tNombre.getText().isEmpty() || tAp.getText().isEmpty()) {
					JOptionPane.showMessageDialog(VistaAltaCliente.this, "Es necesario rellenar todos los datos", "Falta de datos", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Controlador.getInstance().accion(Evento.REGISTRO_DE_CLIENTE, new TCliente(tId.getText().toString(),tNombre.getText().toString() , tAp.getText().toString() ));
				}
			}
			
		});
		buttonp1.add(ok);
		
		cancel = new JButton();
		cancel.setText("Cancelar");
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					VistaAltaCliente.this.setVisible(false);
			}
			
		});
		
		buttonp1.add(cancel);
		mainPanel.add(buttonp1);
		
		add(mainPanel);
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	
	
	
	
	
	
	
	@Override
	public void actualizar(Evento e, Object datos) {
		// TODO Auto-generated method stub
		switch(e) {
		case VISTA_ALTA_CLIENTE:
			this.setVisible(true);
			break;
		case CLIENTE_YA_REGISTRADO:
			JOptionPane.showMessageDialog(this, "El cliente con id" + datos.toString() + "ya ha sido registrado", "Cliente ya registrado", JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			break;
		case CLIENTE_REGISTRADO:
			JOptionPane.showMessageDialog(this, "El cliente con id" + datos.toString() + "ha sido registrado con éxito", "Cliente registrado", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
		}
		
		
	}

}